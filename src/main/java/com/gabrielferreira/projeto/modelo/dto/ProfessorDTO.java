package com.gabrielferreira.projeto.modelo.dto;

import java.io.Serializable;

import com.gabrielferreira.projeto.modelo.entidade.Professor;

public class ProfessorDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private String sexo;
	
	private String curso;
	
	private String escola;
	
	private String salario;
	
	public ProfessorDTO(Professor professor) {
		id = professor.getId();
		nome = professor.getNome();
		sobrenome = professor.getSobrenome();
		cpf = professor.getCpf();
		sexo = professor.getSexo().getNome();
		curso = professor.getCurso().getNome();
		escola = professor.getEscola().getNome();
		salario = professor.getSalario();
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

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}
	
}
