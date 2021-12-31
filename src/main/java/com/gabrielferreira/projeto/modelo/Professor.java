package com.gabrielferreira.projeto.modelo;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal salario;

}
