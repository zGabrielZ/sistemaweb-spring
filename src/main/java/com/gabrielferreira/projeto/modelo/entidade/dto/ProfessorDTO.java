package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCompleto;
	private String cpf;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	private Double salario;

}
