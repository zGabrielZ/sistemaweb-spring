package com.gabrielferreira.projeto.modelo.dto;

import java.io.Serializable;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;

public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private String sexo;
	
	private String curso;
	
	private String escola;
	
	private Integer numeroDamatricula;
	
	private String ra;
	
	public AlunoDTO(Aluno aluno) {
		id = aluno.getId();
		nome = aluno.getNome();
		sobrenome = aluno.getSobrenome();
		cpf = aluno.getCpf();
		sexo = aluno.getSexo().getNome();
		curso = aluno.getCurso().getNome();
		escola = aluno.getEscola().getNome();
		numeroDamatricula = aluno.getNumeroDamatricula();
		ra = aluno.getRa();
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

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public Integer getNumeroDamatricula() {
		return numeroDamatricula;
	}

	public void setNumeroDamatricula(Integer numeroDamatricula) {
		this.numeroDamatricula = numeroDamatricula;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}
	
	

}
