package com.gabrielferreira.projeto.modelo.entidade;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_professor")
@JsonTypeName("professor") 
@NoArgsConstructor
public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "graduacao_id")
	private Graduacao graduacao;
	
	private Date anoAdmissao;
	
	private Integer qtdHoras;
	
	public Professor(Long id, String nomeCompleto, String cpf, Sexo sexo,Date anoAdmissao,Integer qtdHoras) {
		super(id, nomeCompleto, cpf, sexo);
		this.anoAdmissao = anoAdmissao;
		this.qtdHoras = qtdHoras;
	}


	

}
