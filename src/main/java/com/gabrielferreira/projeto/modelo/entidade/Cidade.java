package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_cidade")
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Cidade não pode ser vazio")
	@Column(name="nome_cidade",nullable = false,length = 80)
	private String nomeCidade;
	
	@Valid
	@NotNull(message = "Tem que selecionar o Estado da pessoa")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public Cidade() {}

	public Cidade(Integer id, String nomeCidade,Estado estado) {
		this.id = id;
		this.nomeCidade = nomeCidade;
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
