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
public class EnderecoInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo do logradouro não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String logradouro;
	
	@NotBlank(message = "Campo do número não pode ser vazio")
	@Size(max = 20,message = "Não pode passa de 20 caracteres")
	private String numero;
	
	@NotBlank(message = "Campo do bairro não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String bairro;
	
	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 10,message = "Não pode passa de 10 caracteres")
	private String cep;
	
	@Valid
	@NotNull(message = "Endereço do aluno não pode ser vazio")
	private CidadeInserirDTO cidade;
	

}
