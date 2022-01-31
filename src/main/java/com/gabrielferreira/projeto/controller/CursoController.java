package com.gabrielferreira.projeto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabrielferreira.projeto.api.exception.ErroPadrao;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.Curso;
import com.gabrielferreira.projeto.modelo.to.CursoTo;
import com.gabrielferreira.projeto.modelo.to.consulta.ConsultaPessoaTo;
import com.gabrielferreira.projeto.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {

	private static String PAG_CADASTRO_CURSO = "curso/cadastro-curso";
	private static String PAG_CONSULTA_CURSO = "curso/consulta-curso";
	private static String PAG_CADASTRADAS_PESSOAS = "curso/pessoas-cadastradas";
	
	@Autowired
	private CursoService cursoService;

	@GetMapping("/cadastro")
	public ModelAndView paginaCadastroCurso() {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_CURSO);
		modelAndView.addObject("curso",new CursoTo());
		return modelAndView;
	}
	
	@GetMapping("/consulta")
	public ModelAndView consultaCursos() {
		ModelAndView modelAndView = new ModelAndView(PAG_CONSULTA_CURSO);
		List<CursoTo> cursoTos = cursoService.getCursos();
		modelAndView.addObject("cursos",cursoTos);
		if(cursoTos.isEmpty()) {
			modelAndView.addObject("msgCursos","Nenhum registro encontrado.");
		}
		return modelAndView;
	}
	
	@GetMapping("/editar-curso/{idCurso}")
	public ModelAndView editarCurso(@PathVariable Integer idCurso) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_CURSO);
		Curso curso = cursoService.getCurso(idCurso);
		CursoTo cursoTo = new CursoTo(curso);
		modelAndView.addObject("curso",cursoTo);
		return modelAndView;
	}
	
	@GetMapping("/deletar-curso/{idCurso}")
	public ModelAndView deletarCurso(@PathVariable Integer idCurso, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/curso/consulta");
		cursoService.deletar(idCurso);
		redirectAttributes.addFlashAttribute("msgCursoDelete","Curso deletado com sucesso.");
		return modelAndView;
	}
	
	@GetMapping("/pessoas-cadastradas/{idCurso}")
	public ModelAndView consultaPessoaPorCurso(@PathVariable Integer idCurso) {
		Curso curso = cursoService.getCurso(idCurso);
		
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRADAS_PESSOAS);
		List<ConsultaPessoaTo> pessoaTos = cursoService.getPessoasPorCurso(curso.getId());
		modelAndView.addObject("pessoas",pessoaTos);
		if(pessoaTos.isEmpty()) {
			modelAndView.addObject("msgPessoas","Nenhum registro encontrado.");
		}
		return modelAndView;
	}
	
	@PostMapping("/salvar-curso")
	public ModelAndView cadastrarCurso(@Valid CursoTo cursoTo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:cadastro");
		
		if(bindingResult.hasErrors()) {
			return verificarValidacaoCurso(cursoTo, bindingResult);
		}
	
		try {
			
			Integer idCurso = cursoTo.getId();
			cursoService.inserir(cursoTo);
			
			if(idCurso == null) {
				redirectAttributes.addFlashAttribute("sucesso",cursoTo.getNome() + " cadastrada com sucesso !");
			} else {
				redirectAttributes.addFlashAttribute("sucesso",cursoTo.getNome() + " atualizada com sucesso !");
			}
		
		} catch (RegraException e) {
			redirectAttributes.addFlashAttribute("erro",e.getMessage());
		}
		
		return modelAndView;
	}
	
	private ModelAndView verificarValidacaoCurso(CursoTo cursoTo, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_CURSO);
		modelAndView.addObject("curso", new CursoTo());
		List<ErroPadrao> erroPadraos = new ArrayList<>();
		for(ObjectError objectError :bindingResult.getAllErrors()) {
			erroPadraos.add(new ErroPadrao(objectError.getDefaultMessage()));
		}
		modelAndView.addObject("errosValidacao",erroPadraos);
		return modelAndView;
	}
	
	
	
}
