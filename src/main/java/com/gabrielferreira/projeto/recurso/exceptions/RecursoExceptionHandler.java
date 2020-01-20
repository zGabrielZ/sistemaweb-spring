package com.gabrielferreira.projeto.recurso.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@ControllerAdvice
public class RecursoExceptionHandler {

	@ExceptionHandler(RecursoNotFoundException.class)
	public ResponseEntity<ErroPadrao> recursoNotFound(RecursoNotFoundException e,
			HttpServletRequest req){
		String erro = "Recurso não achado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), erro,
				e.getMessage(),req.getRequestURI());
		return ResponseEntity.status(status).body(erroPadrao);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErroPadrao> database(DatabaseException e,
			HttpServletRequest req){
		String erro = "Database erro";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), erro,
				e.getMessage(),req.getRequestURI());
		return ResponseEntity.status(status).body(erroPadrao);
	}
	
	@ExceptionHandler(EntidadeNotFoundException.class)
	public ResponseEntity<ErroPadrao> entidadeNotFound(EntidadeNotFoundException e,
			HttpServletRequest req){
		String erro = "Entidade não achado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao erroPadrao = new ErroPadrao(Instant.now(), status.value(), erro,
				e.getMessage(),req.getRequestURI());
		return ResponseEntity.status(status).body(erroPadrao);
	}
	
}
