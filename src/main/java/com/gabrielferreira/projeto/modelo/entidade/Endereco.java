package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_endereco")
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Logradouro não pode ser vazio")
	@Column(name="logradouro",nullable = false,length = 150)
	private String logradouro;
	
	@NotEmpty(message = "Número da casa ou apartamento não pode ser vazio")
	@Column(name="numero",nullable = false,length = 80)
	private String numero;
	
	@NotEmpty(message = "Bairro não pode ser vazio")
	@Column(name="bairro",nullable = false,length = 150)
	private String bairro;
	
	@NotEmpty(message = "Cep não pode ser vazio")
	@Column(name="cep",nullable = false)
	private String cep;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@OneToOne(mappedBy = "endereco")
	private Pessoa pessoa;
	
	public Endereco() {}

	public Endereco(Integer id, String logradouro, String numero, String bairro, String cep,
			Cidade cidade) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
