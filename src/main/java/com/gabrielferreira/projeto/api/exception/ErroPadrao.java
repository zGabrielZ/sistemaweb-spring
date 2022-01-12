package com.gabrielferreira.projeto.api.exception;

import java.io.Serializable;

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
public class ErroPadrao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensagem;
	private Integer status;
	private String data;
	
	public ErroPadrao(String mensagem) {
		this.mensagem = mensagem;
	}

}
