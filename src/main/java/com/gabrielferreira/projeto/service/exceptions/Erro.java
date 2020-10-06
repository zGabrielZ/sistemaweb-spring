package com.gabrielferreira.projeto.service.exceptions;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Erro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private OffsetDateTime localDateTime;
	private String titulo;
	private List<Campo> campos = new ArrayList<Erro.Campo>();
	
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Campo implements Serializable{

		private static final long serialVersionUID = 1L;
		private String nome;
		private String mensagem;
	}

}
