package com.gabrielferreira.projeto.modelo.dto;

import java.io.Serializable;

import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.sun.istack.NotNull;

public class PessoaDisciplinaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Pessoa pessoa;
	
	@NotNull()
	private Disciplina disciplina;
	
	public PessoaDisciplinaDTO() {}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
}
