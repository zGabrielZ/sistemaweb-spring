package com.gabrielferreira.projeto.modelo.to.consulta;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class ConsultaEnderecoTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String numero;
	
	private Integer idEstado;
	
	@JsonIgnore
	private String uf;
}
