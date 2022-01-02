package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CIDADE")
@Getter
@Setter
@ToString(exclude = {"estado"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "CIDADE_SEQ", sequenceName = "SEQ_CIDADE", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "CIDADE_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;

	@Column(name = "nome",nullable = false)
	private String nome;
	
	@JoinColumn(name = "estado_id",nullable = false,foreignKey = @ForeignKey(name="estado_fk") )
	@ManyToOne
	private Estado estado;
	
}
