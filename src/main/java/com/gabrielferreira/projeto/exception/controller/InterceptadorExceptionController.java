package com.gabrielferreira.projeto.exception.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gabrielferreira.projeto.api.exception.Campo;
import com.gabrielferreira.projeto.api.exception.ErroPadrao;
import com.gabrielferreira.projeto.exception.CepException;
import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.exception.PessoaException;

@ControllerAdvice
public class InterceptadorExceptionController extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> campos = new ArrayList<Campo>();
		for(ObjectError obj : ex.getBindingResult().getAllErrors()) {
			String nomeCampo = ((FieldError) obj).getField();
			String mensagem = obj.getDefaultMessage();
			Campo campo = new Campo(nomeCampo, mensagem);
			campos.add(campo);
		}
		
		ErroPadrao erroPadrao = new ErroPadrao("Campos inválidos, faça o preenchimento correto",status.value(),new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), campos);
		
		return super.handleExceptionInternal(ex, erroPadrao, headers, status, request);
	}

	@ExceptionHandler(EntidadeException.class)
	public ModelAndView entidadeNaoEncontrada(EntidadeException e){
		
		ModelAndView modelAndView = new ModelAndView("pagina-erro");
		
		ErroPadrao erro = new ErroPadrao(e.getMessage(), HttpStatus.NOT_FOUND.value(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()),null);
		modelAndView.addObject("erro",erro);
		
		return modelAndView;
	}
	
	@ExceptionHandler(CepException.class)
	public ResponseEntity<ErroPadrao> cepException(CepException e, HttpServletRequest httpServletRequest){
		ErroPadrao erro = new ErroPadrao(e.getMessage(), HttpStatus.BAD_REQUEST.value(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()),null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(PessoaException.class)
	public ResponseEntity<ErroPadrao> pessoaException(PessoaException e, HttpServletRequest httpServletRequest){
		ErroPadrao erro = new ErroPadrao(e.getMessage(), HttpStatus.BAD_REQUEST.value(), new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()),null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
