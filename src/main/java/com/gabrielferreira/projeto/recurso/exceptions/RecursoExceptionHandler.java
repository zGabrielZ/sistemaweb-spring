package com.gabrielferreira.projeto.recurso.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.service.exceptions.DatabaseException;

@ControllerAdvice
public class RecursoExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public ModelAndView database(DatabaseException e,
			HttpServletRequest req){
		ModelAndView modelAndView = new ModelAndView("error");
		String erro = "Database erro";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao erroPadrao = new ErroPadrao(status.value(), erro,"Não é possivel remover,"
				+ " pois tem entidades relacionadas");
		modelAndView.addObject("erro",erroPadrao);
		return modelAndView;
	}
	
	

	
}
