package com.gabrielferreira.projeto.exception.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.api.exception.ErroPadrao;
import com.gabrielferreira.projeto.exception.EntidadeException;

@ControllerAdvice
public class InterceptadorExceptionController {

	@ExceptionHandler(EntidadeException.class)
	public ModelAndView entidadeNaoEncontrada(EntidadeException e){
		
		ModelAndView modelAndView = new ModelAndView("pagina-erro");
		
		ErroPadrao erro = new ErroPadrao(e.getMessage(), HttpStatus.NOT_FOUND.value(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		modelAndView.addObject("erro",erro);
		
		return modelAndView;
	}
}
