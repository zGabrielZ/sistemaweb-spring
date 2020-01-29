package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.modelo.dto.CursoDTO;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.service.CursoService;

@Controller
public class CursoRecurso {

	@Autowired
	private CursoService cursoService;
	
	@RequestMapping(method = RequestMethod.POST,value = "**/salvarcurso")
	public ModelAndView salvar(@Valid Curso curso,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrocurso");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<CursoDTO> cursoDtos = ((Collection<Curso>) cursos).
					stream().map(x -> new CursoDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("cursos",cursoDtos);
			modeAndView.addObject("cursoobj",new Curso());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modeAndView.addObject("msgdeerro",msg);
			return modeAndView;
		}
		
		if(curso.getId() == null) {
			if(cursoService.buscarNomeCurso(curso)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastrocurso");
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<CursoDTO> cursoDTOs = ((Collection<Curso>) cursos)
						.stream().map(x -> new CursoDTO(x)).
							collect(Collectors.toList());
				modeAndView.addObject("msgdeerro","Nome já existe no banco !!");
				modeAndView.addObject("cursos",cursoDTOs);
				modeAndView.addObject("cursoobj",new Curso());
				return modeAndView;
			}
			
			cursoService.inserir(curso);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrocurso");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<CursoDTO> cursoDTOs = ((Collection<Curso>) cursos)
					.stream().map(x -> new CursoDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("msg","Cadastrado com sucesso !!");
			modeAndView.addObject("cursos",cursoDTOs);
			modeAndView.addObject("cursoobj",new Curso());
			return modeAndView;
			
		}
		else {
			if(cursoService.buscarNomeAtualizado(curso)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastrocurso");
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<CursoDTO> cursoDTOs = ((Collection<Curso>) cursos)
						.stream().map(x -> new CursoDTO(x)).
							collect(Collectors.toList());
				modeAndView.addObject("msgdeerro","Nome que informou já existe cadastrado !!");
				modeAndView.addObject("cursos",cursoDTOs);
				modeAndView.addObject("cursoobj",new Curso());
				return modeAndView;
			}
			
			cursoService.inserir(curso);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrocurso");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<CursoDTO> cursoDTOs = ((Collection<Curso>) cursos)
					.stream().map(x -> new CursoDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("msg","Atualizado com sucesso !!");
			modeAndView.addObject("cursos",cursoDTOs);
			modeAndView.addObject("cursoobj",new Curso());
			return modeAndView;
		}

	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listacurso")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocurso");
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<CursoDTO> cursoDTOs = ((Collection<Curso>) cursos)
				.stream().map(x -> new CursoDTO(x)).
					collect(Collectors.toList());
		modelAndView.addObject("cursos",cursoDTOs);
		modelAndView.addObject("cursoobj",new Curso());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/editarcurso/{idcurso}")
	public ModelAndView editar(@PathVariable("idcurso") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocurso");
		Curso curso = cursoService.consultarPorId(id);
		modelAndView.addObject("cursoobj",curso);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarcurso/{idcurso}")
	public ModelAndView deletar(@PathVariable("idcurso") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocurso");
		cursoService.deletar(id);
		Iterable<Curso> cursos = cursoService.consultarTodos();
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("cursoobj",new Curso());
		modelAndView.addObject("msg","Curso removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedocurso")
	public ModelAndView procurarNome(@RequestParam("procurarnomecurso") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocurso");
		modelAndView.addObject("cursos",cursoService.buscarNome(nome));
		modelAndView.addObject("cursoobj",new Curso());
		return modelAndView;
	}
	
}
