package com.gabrielferreira.projeto.recurso.exceptions;

import java.io.Serializable;


public class ErroPadrao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String erro;
	private String msg;
	
	public ErroPadrao() {}

	public ErroPadrao(Integer status, String erro, String msg) {
		this.status = status;
		this.erro = erro;
		this.msg = msg;
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


	
	
	
	
}

