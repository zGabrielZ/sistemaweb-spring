package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ENDERECO_PESSOA")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnderecoPessoa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ENDERECO_PESSOA_SEQ", sequenceName = "SEQ_ENDERECO_PESSOA", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "ENDERECO_PESSOA_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "numero",nullable = false)
	private String numero;

}
