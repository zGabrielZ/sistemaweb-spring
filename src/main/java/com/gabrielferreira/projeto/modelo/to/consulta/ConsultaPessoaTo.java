package com.gabrielferreira.projeto.modelo.to.consulta;

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
public class ConsultaPessoaTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String sobrenome;
	private String cpf;
	private LocalDate dataNascimento;
	private String sexo;
	
	public ConsultaPessoaTo(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.sobrenome = pessoa.getSobrenome();
		this.sexo = pessoa.getSexo().getDescricao();
	}
}
