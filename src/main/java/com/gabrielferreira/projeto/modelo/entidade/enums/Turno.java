package com.gabrielferreira.projeto.modelo.entidade.enums;

import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

public enum Turno {

	Manha(1,"Manhã"),
	Tarde(2,"Tarde"),
	Noite(3,"Noite");
	
	private int codigo;
	private String descricao;
	
	Turno(int codigo,String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Turno converterParaEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Turno turno : Turno.values()) {
			if(codigo.equals(turno.getCodigo())) {
				return turno;
			}
		}
		
		throw new RegraDeNegocioException("Turno não encontrado");
	}
}
