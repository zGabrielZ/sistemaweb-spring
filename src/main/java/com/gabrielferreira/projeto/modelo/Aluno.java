package com.gabrielferreira.projeto.modelo;
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
public class Aluno extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	private String ra;
	
}
