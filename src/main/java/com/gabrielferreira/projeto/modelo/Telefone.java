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
@Table(name = "TELEFONE")
@Getter
@Setter
@ToString(exclude = {"pessoa","tipoTelefone"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "TELEFONE_SEQ", sequenceName = "SEQ_TELEFONE", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "TELEFONE_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "numero",nullable = false)
	private String numero;
	
	@JoinColumn(name = "pessoa_id",nullable = false,foreignKey = @ForeignKey(name="pessoa_fk"))
	@ManyToOne
	private Pessoa pessoa;
	
	@JoinColumn(name = "tipo_telefone_id",nullable = false,foreignKey = @ForeignKey(name="tipo_telefone_fk"))
	@ManyToOne
	private TipoTelefone  tipoTelefone;
	
}
