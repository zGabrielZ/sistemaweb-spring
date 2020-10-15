package com.gabrielferreira.projeto.modelo.entidade;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Perfil;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_professor")
@JsonTypeName("professor") 
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "graduacao_id")
	private Graduacao graduacao;
	
	private Date anoAdmissao;
	
	private Integer qtdHoras;
	
	@OneToMany(mappedBy = "professor")
	private List<Turma> turmas = new ArrayList<Turma>();
	
	public Professor() {
		addPerfil(Perfil.PROFESSOR);
	}
	
	public Professor(Long id, String nomeCompleto, String cpf, Sexo sexo,Date anoAdmissao,Integer qtdHoras,String email,String senha) {
		super(id, nomeCompleto, cpf, sexo,email,senha);
		this.anoAdmissao = anoAdmissao;
		this.qtdHoras = qtdHoras;
		addPerfil(Perfil.PROFESSOR);
	}
		
}
