package com.gabrielferreira.projeto.service.exceptions;


public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseException(String msg) {
		super("Não pode excluir, pois tem entidades relacionadas");
	}
	

}
