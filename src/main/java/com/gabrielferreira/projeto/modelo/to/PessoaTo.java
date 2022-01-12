package com.gabrielferreira.projeto.modelo.to;

import java.io.Serializable;
import java.time.LocalDate;

import com.gabrielferreira.projeto.modelo.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PessoaTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private String sexo;
	
	public PessoaTo(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.sobrenome = pessoa.getSobrenome();
		this.dataNascimento = pessoa.getDataNascimento();
		this.sexo = pessoa.getSexo().getDescricao();
	}
}
