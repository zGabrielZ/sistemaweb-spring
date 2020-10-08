package com.gabrielferreira.projeto.modelo.entidade.enums;

import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

public enum TipoTelefone {

	Residencial(1,"Residencial"),
	Comercial(2,"Comercial"),
	Celular(3,"Celular");
	
	private int codigo;
	private String descricao;
	
	TipoTelefone(int codigo,String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoTelefone converterParaEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(TipoTelefone tipo : TipoTelefone.values()) {
			if(codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		
		throw new RegraDeNegocioException("Tipo de telefone não encontrado");
	}
}
