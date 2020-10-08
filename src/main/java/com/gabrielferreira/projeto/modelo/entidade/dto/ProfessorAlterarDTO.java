package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
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
public class ProfessorAlterarDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String nomeCompleto;
		
	@NotNull(message = "Tem que escolher o sexo")
	private Integer sexo;
		
	@NotNull(message = "Campo da quantidade de horas não pode ser vazio")
	private Integer qtdHoras;
}
