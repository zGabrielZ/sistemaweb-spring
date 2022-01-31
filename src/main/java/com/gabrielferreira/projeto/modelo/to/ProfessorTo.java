package com.gabrielferreira.projeto.modelo.to;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfessorTo extends PessoaTo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Salário não pode ser vazio")
	private String salario;
}
