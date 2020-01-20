package com.gabrielferreira.projeto.service.exceptions;

public class EntidadeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	 public EntidadeNotFoundException(Object msg) {
		 super("Entidade não achada. Id "+msg);
	 }

}
