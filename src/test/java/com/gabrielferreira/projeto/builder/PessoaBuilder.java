package com.gabrielferreira.projeto.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.gabrielferreira.projeto.modelo.Aluno;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.Professor;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

public class PessoaBuilder {

	private Pessoa pessoa;
	
	private PessoaBuilder() {}
	
	public static PessoaBuilder umAluno(String ra) {
		PessoaBuilder builder = new PessoaBuilder();
		builder.pessoa = new Aluno(ra);
		return builder;
	}
	
	public static PessoaBuilder umProfessor(BigDecimal salario) {
		PessoaBuilder builder = new PessoaBuilder();
		builder.pessoa = new Professor(salario);
		return builder;
	}
	
	public PessoaBuilder comId(Integer id) {
		pessoa.setId(id);
		return this;
	}
	
	public PessoaBuilder comNome(String nome) {
		pessoa.setNome(nome);
		return this;
	}
	
	public PessoaBuilder comSobrenome(String sobrenome) {
		pessoa.setSobrenome(sobrenome);
		return this;
	}
	
	public PessoaBuilder comCpf(String cpf) {
		pessoa.setCpf(cpf);
		return this;
	}
	
	public PessoaBuilder comDataNascimento(LocalDate data) {
		pessoa.setDataNascimento(data);
		return this;
	}
	
	public PessoaBuilder comSexo(Sexo sexo) {
		pessoa.setSexo(sexo);
		return this;
	}
	
	public Pessoa constroi() {
		return pessoa;
	}
}
