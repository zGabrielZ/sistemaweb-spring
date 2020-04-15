package com.gabrielferreira.projeto.modelo.entidade.enums;

public enum TipoTelefone {

	Residencial("Residencial"),
	Comercial("Comercial"),
	Celular("Celular");
	
	private String descricao;
	
	TipoTelefone(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
