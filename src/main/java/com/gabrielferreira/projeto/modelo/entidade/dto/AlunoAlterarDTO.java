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
public class AlunoAlterarDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String nomeCompleto;
		
	@NotNull(message = "Tem que escolher o sexo")
	private Integer sexo;
	
	@NotBlank(message = "Campo do ra não pode ser vazio")
	@Size(max = 10,message = "Não pode passa de 10 caracteres")
	private String ra;
	
	@Valid
	@NotNull(message = "Curso não pode ser nulo")
	private Long curso;
}
