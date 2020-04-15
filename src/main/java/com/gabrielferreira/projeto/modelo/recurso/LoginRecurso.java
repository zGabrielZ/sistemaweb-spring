package com.gabrielferreira.projeto.modelo.recurso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginRecurso {

	@RequestMapping(method = RequestMethod.GET,value = "/entrar")
	public String login() {
		return "entrar";
	}
}
