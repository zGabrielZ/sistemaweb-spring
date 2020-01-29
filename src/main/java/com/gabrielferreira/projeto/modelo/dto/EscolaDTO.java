package com.gabrielferreira.projeto.modelo.dto;

import java.io.Serializable;
import com.gabrielferreira.projeto.modelo.entidade.Escola;

public class EscolaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	public EscolaDTO(Escola escola) {
		id = escola.getId();
		nome = escola.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
