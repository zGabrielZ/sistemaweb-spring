package com.gabrielferreira.projeto.modelo.dto;

import java.io.Serializable;

public class QuantidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long y;
	private String label;
	
	public QuantidadeDTO(Long y, String label) {
		this.y = y;
		this.label = label;
	}

	public Long getY() {
		return y;
	}

	public void setY(Long y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
}