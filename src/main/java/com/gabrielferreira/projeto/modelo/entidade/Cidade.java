package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_cidade")
@NoArgsConstructor
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cidade;

	private Integer estado;
	
	public Cidade(Long id, String cidade, Estado estado) {
		this.id = id;
		this.cidade = cidade;
		this.estado = (estado == null)?null:estado.getCodigo();
	}
	
	public Estado getEstado() {
		return Estado.converterParaEnum(estado);
	}

	public void setEstado(Estado estado) {
		this.estado = estado.getCodigo();
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
