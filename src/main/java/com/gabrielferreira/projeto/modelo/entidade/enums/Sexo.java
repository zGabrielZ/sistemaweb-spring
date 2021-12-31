package com.gabrielferreira.projeto.modelo.entidade.enums;

import lombok.Getter;
import lombok.Setter;

public enum Sexo {

	MASCULINO(1,"Masculino"),
	FEMININO(2,"Feminino");
	
	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String descricao;

	private Sexo(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	
}
