package com.gabrielferreira.projeto.modelo.entidade.enums;

import lombok.Getter;
import lombok.Setter;

public enum TipoPessoa {

	ALUNO(1,"Aluno"),
	PROFESSOR(2,"Professor");
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String descricao;

	private TipoPessoa(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
}
