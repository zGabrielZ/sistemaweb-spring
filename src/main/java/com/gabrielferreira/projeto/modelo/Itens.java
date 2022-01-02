package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;

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
@Table(name = "ITENS_PESSOA_DISCIPLINA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Itens implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ITENS_PESSOA_DISCIPLINA_SEQ", sequenceName = "SEQ_ITENS_PESSOA_DISCIPLINA", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "ITENS_PESSOA_DISCIPLINA_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@JoinColumn(name = "pessoa_id",nullable = false,foreignKey = @ForeignKey(name="pessoa_fk"))
	@ManyToOne
	private Pessoa pessoa;
	
	private Disciplina disciplina;

}
