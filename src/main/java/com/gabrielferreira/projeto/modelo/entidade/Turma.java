package com.gabrielferreira.projeto.modelo.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.gabrielferreira.projeto.modelo.entidade.enums.Turno;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tab_turma")
public class Turma implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeTurma;
	private String numeroTurma;
	private Integer vagas;
	private Integer turno;
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	public Turma(Long id, String nomeTurma, String numeroTurma, Integer vagas, Turno turno) {
		this.id = id;
		this.nomeTurma = nomeTurma;
		this.numeroTurma = numeroTurma;
		this.vagas = vagas;
		this.turno = (turno == null)?null:turno.getCodigo();
	}
	
	public Turno getTurno() {
		return Turno.converterParaEnum(turno);
	}

	public void setTurno(Turno turno) {
		this.turno = turno.getCodigo();
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
		Turma other = (Turma) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
