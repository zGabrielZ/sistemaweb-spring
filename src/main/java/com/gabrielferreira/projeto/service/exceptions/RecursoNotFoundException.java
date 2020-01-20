package com.gabrielferreira.projeto.service.exceptions;

public class RecursoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RecursoNotFoundException(Object msg) {
		super("Recurso não achado. Id "+msg);
	}

}
