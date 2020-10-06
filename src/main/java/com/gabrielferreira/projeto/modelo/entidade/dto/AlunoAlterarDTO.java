package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

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
	
	@Email(message = "Email inválido")
	@NotBlank(message = "Campo do email não pode ser vazio")
	private String email;
	
	@NotBlank(message = "Campo da senha não pode ser vazia")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String senha;
	
	@NotNull(message = "Tem que escolher o sexo")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotBlank(message = "Campo do ra não pode ser vazio")
	@Size(max = 10,message = "Não pode passa de 10 caracteres")
	private String ra;
}
