package com.gabrielferreira.projeto.recurso.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErroPadrao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String erro;
	private String msg;
	private String caminho;
	
	public ErroPadrao() {}

	public ErroPadrao(Instant timestamp, Integer status, String erro, String msg, String caminho) {
		this.timestamp = timestamp;
		this.status = status;
		this.erro = erro;
		this.msg = msg;
		this.caminho = caminho;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	
	
	
}

