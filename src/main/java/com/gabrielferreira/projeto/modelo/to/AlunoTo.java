package com.gabrielferreira.projeto.modelo.to;


import javax.validation.constraints.Size;

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
public class AlunoTo extends PessoaTo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "RA não pode ser vazio")
	@Size(min = 10, message = "O campo RA deve ter no maximo 10 caracteres")
	private String ra;
}
