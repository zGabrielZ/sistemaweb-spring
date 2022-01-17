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
import com.gabrielferreira.projeto.modelo.Disciplina;
import com.gabrielferreira.projeto.modelo.to.DisciplinaTo;
import com.gabrielferreira.projeto.service.DisciplinaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

	private static String PAG_CADASTRO_DISCIPLINA = "disciplina/cadastro-disciplina";
	private static String PAG_CONSULTA_DISCIPLINA = "disciplina/consulta-disciplina";
	
	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping("/cadastro")
	public ModelAndView paginaCadastroDisciplina() {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_DISCIPLINA);
		modelAndView.addObject("disciplina",new DisciplinaTo());
		return modelAndView;
	}
	
	@GetMapping("/consulta")
	public ModelAndView consultaDisciplinas() {
		ModelAndView modelAndView = new ModelAndView(PAG_CONSULTA_DISCIPLINA);
		List<DisciplinaTo> disciplinaTos = disciplinaService.getDisciplinas();
		modelAndView.addObject("disciplinas",disciplinaTos);
		if(disciplinaTos.isEmpty()) {
			modelAndView.addObject("msgDisciplinas","Nenhum registro encontrado.");
		}
		return modelAndView;
	}
	
	@GetMapping("/editar-disciplina/{idDisciplina}")
	public ModelAndView editarCurso(@PathVariable Integer idDisciplina) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_DISCIPLINA);
		Disciplina disciplina = disciplinaService.getDisciplina(idDisciplina);
		DisciplinaTo disciplinaTo = new DisciplinaTo(disciplina);
		modelAndView.addObject("disciplina",disciplinaTo);
		return modelAndView;
	}
	
	@GetMapping("/deletar-disciplina/{idDisciplina}")
	public ModelAndView deletarCurso(@PathVariable Integer idDisciplina, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/disciplina/consulta");
		disciplinaService.deletar(idDisciplina);
		redirectAttributes.addFlashAttribute("msgDisciplinasDelete","Disciplina deletado com sucesso.");
		return modelAndView;
	}
	
	@PostMapping("/salvar-disciplina")
	public ModelAndView cadastrarDisciplina(@Valid DisciplinaTo disciplinaTo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:cadastro");
		
		if(bindingResult.hasErrors()) {
			return verificarValidacaoDisciplina(disciplinaTo, bindingResult);
		}
	
		try {
			
			Integer idDisciplina = disciplinaTo.getId();
			disciplinaService.inserir(disciplinaTo);
			
			if(idDisciplina == null) {
				redirectAttributes.addFlashAttribute("sucesso",disciplinaTo.getNome() + " cadastrada com sucesso !");
			} else {
				redirectAttributes.addFlashAttribute("sucesso",disciplinaTo.getNome() + " atualizada com sucesso !");
			}
		
		} catch (RegraException e) {
			redirectAttributes.addFlashAttribute("erro",e.getMessage());
		}
		
		return modelAndView;
	}
	
	private ModelAndView verificarValidacaoDisciplina(DisciplinaTo disciplinaTo, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_DISCIPLINA);
		modelAndView.addObject("disciplina", disciplinaTo);
		List<ErroPadrao> erroPadraos = new ArrayList<>();
		for(ObjectError objectError :bindingResult.getAllErrors()) {
			erroPadraos.add(new ErroPadrao(objectError.getDefaultMessage()));
		}
		modelAndView.addObject("errosValidacao",erroPadraos);
		return modelAndView;
	}
	
	
	
}
