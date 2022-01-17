package com.gabrielferreira.projeto.modelo.to;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import com.gabrielferreira.projeto.modelo.Disciplina;

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
public class DisciplinaTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Size(min=5, max = 150, message="O campo nome deve ter no mínimo 5 até 150 caracteres")
	private String nome;
	
	public DisciplinaTo(Disciplina disciplina) {
		this.id = disciplina.getId();
		this.nome = disciplina.getNome();
	}
	
	public Disciplina getDisciplina(DisciplinaTo disciplinaTo) {
		Disciplina disciplina = new Disciplina();
		disciplina.setId(disciplinaTo.getId());
		disciplina.setNome(disciplinaTo.getNome());
		return disciplina;
	}
}
