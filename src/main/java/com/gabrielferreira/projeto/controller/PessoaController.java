package com.gabrielferreira.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.service.EstadoService;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	private static String PAG_CAD_PESSOA = "pessoa/cadastro-pessoa";
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/cadastro")
	public ModelAndView paginaInicialCadastroPessoa() {
		ModelAndView modelAndView = new ModelAndView(PAG_CAD_PESSOA);
		modelAndView.addObject("estados", estadoService.getEstados());
		return modelAndView;
	}
}
