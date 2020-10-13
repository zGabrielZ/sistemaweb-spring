package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gabrielferreira.projeto.modelo.entidade.enums.Semestre;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tab_aula")
public class Aula implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	private Integer semestre;
	
	public Aula(Long id, Disciplina disciplina, Aluno aluno, Semestre semestre) {
		this.id = id;
		this.disciplina = disciplina;
		this.aluno = aluno;
		this.semestre =  (semestre == null)?null:semestre.getCodigo();
	}
	
	public Semestre getSemestre() {
		return Semestre.converterParaEnum(semestre);
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre.getCodigo();
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
