package com.gabrielferreira.projeto.modelo.entidade.enums;

public enum Sexo {

	Masculino("Masculino"),
	Feminino("Feminino");
	
	private String descricao;
	
	Sexo(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
