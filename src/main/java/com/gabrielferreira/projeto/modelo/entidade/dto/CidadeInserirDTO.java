package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeInserirDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo do nome da cidade não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String cidade;
	
	@NotNull(message = "Tem que escolher o estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;
}
