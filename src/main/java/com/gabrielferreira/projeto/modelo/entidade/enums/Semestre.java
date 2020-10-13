package com.gabrielferreira.projeto.modelo.entidade.enums;

import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

public enum Semestre {

	PrimeiroSemestre(1,"Primeiro Semestre"),
	SegundoSemestre(2,"Segundo Semestre"),
	TerceiroSemestre(3,"Terceiro Semestre"),
	QuartoSemestre(4,"Quarto Semestre"),
	QuintoSemestre(5,"Quinto Semestre"),
	SextoSemestre(6,"Sexto Semestre"),
	SetimoSemestre(7,"Sétimo Semestre"),
	OitavoSemestre(8,"Oitavo Semestre"),
	NonoSemestre(9,"Nono Semestre"),
	DecimoSemestre(10,"Décimo Semestre"),
	DecimoPrimeiroSemestre(11,"Décimo Primeiro Semestre"),
	DecimoSegundoSemestre(12,"Décimo Segundo Semestre");
	
	private int codigo;
	private String descricao;
	
	Semestre(int codigo,String descricao){
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
	
	public static Semestre converterParaEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Semestre semestre : Semestre.values()) {
			if(codigo.equals(semestre.getCodigo())) {
				return semestre;
			}
		}
		
		throw new RegraDeNegocioException("Semestre não encontrado");
	}
}
