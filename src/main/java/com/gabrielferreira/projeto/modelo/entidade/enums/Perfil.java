package com.gabrielferreira.projeto.modelo.entidade.enums;

import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

public enum Perfil {

	ADMIN(1,"ROLE_ADMIN"),
	ALUNO(2,"ROLE_ALUNO"),
	PROFESSOR(3,"ROLE_PROFESSOR");
	
	private int codigo;
	private String descricao;
	
	Perfil(int codigo,String descricao){
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
	
	public static Perfil converterParaEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Perfil perfil : Perfil.values()) {
			if(codigo.equals(perfil.getCodigo())) {
				return perfil;
			}
		}
		
		throw new RegraDeNegocioException("Perfil não encontrado");
	}
}
