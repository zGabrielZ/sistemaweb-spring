package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;


import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nomeCompleto;
	private String cpf;
	private Sexo sexo;
	private String ra;
}
