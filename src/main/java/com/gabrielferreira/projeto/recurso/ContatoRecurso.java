package com.gabrielferreira.projeto.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Contato;
import com.gabrielferreira.projeto.service.ContatoService;

@Controller
public class ContatoRecurso {

	@Autowired
	private ContatoService contatoService;
	
	@RequestMapping(method = RequestMethod.GET,value = "**/listadecontatos")
	public ModelAndView consultarTodos() {
		ModelAndView modelAndView = new ModelAndView("listagem/listadecontatos");
		Iterable<Contato> contatos = contatoService.consultarTodos();
		modelAndView.addObject("contatos",contatos);
		return modelAndView;
	}
}
