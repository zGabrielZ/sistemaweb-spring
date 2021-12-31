package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"endereco","curso","telefones","itens"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EqualsAndHashCode.Include
	private Integer id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private Sexo sexo;

	private Endereco endereco;
	
	private Curso curso;
	
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	private List<Itens> itens = new ArrayList<Itens>();
	
}
