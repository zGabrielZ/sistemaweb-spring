package com.gabrielferreira.projeto.modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ALUNO")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ra",nullable = false)
	private String ra;
	
}
