package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "DISCIPLINA")
@Getter
@Setter
@ToString(exclude = {"itens"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "DISCIPLINA_SEQ", sequenceName = "SEQ_DISCIPLINA", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "DISCIPLINA_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "disciplina")
	private List<Itens> itens = new ArrayList<Itens>();

}
