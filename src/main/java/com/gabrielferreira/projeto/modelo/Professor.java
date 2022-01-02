package com.gabrielferreira.projeto.modelo;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PROFESSOR")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "salario",nullable = false)
	private BigDecimal salario;

}
