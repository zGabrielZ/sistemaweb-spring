package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurmaInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String nomeTurma;
	
	@NotBlank(message = "Campo do número da turma não pode ser vazio")
	@Size(max = 10,message = "Não pode passa de 10 caracteres")
	private String numeroTurma;
	
	@NotNull(message = "Tem que escolher o turno")
	private Integer turno;
	
	@NotNull(message = "Tem que escolher a quantidade de vagas")
	private Integer vagas;
	
	@Valid
	@NotNull(message = "Professor não pode ser nulo")
	private Long professor;
}
