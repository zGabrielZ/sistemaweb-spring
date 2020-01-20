package com.gabrielferreira.projeto.recurso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Professor;


@Controller
public class IndexRecurso {

	@RequestMapping(method = RequestMethod.GET,value = "/index")
	public String inicio() {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/cadastroaluno")
	public ModelAndView cadastroAluno() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/cadastroprofessor")
	public ModelAndView cadastroProfessor() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listadecontatos")
	public String listadecontato() {
		return "listagem/listadecontatos";
	}
}
