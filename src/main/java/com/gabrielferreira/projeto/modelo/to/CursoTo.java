package com.gabrielferreira.projeto.modelo.to;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.gabrielferreira.projeto.modelo.Curso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CursoTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Size(min=5, max = 150, message="O campo nome deve ter no mínimo 5 até 150 caracteres")
	private String nome;
	
	public CursoTo(Curso curso) {
		this.id = curso.getId();
		this.nome = curso.getNome();
	}
	
	public Curso getCurso(CursoTo cursoTo) {
		Curso curso = new Curso();
		curso.setId(cursoTo.getId());
		curso.setNome(cursoTo.getNome());
		return curso;
	}
}
