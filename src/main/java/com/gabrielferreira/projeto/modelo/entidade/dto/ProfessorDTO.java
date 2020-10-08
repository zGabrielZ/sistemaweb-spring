package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final String MY_TIME_ZONE="GMT-3";
	
	private Long id;
	private String nomeCompleto;
	private String cpf;
	
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;

	@JsonFormat(pattern = "dd/MM/yyyy",timezone =MY_TIME_ZONE)
	private Date anoAdmissao;
	
	private Integer qtdHoras;
	
	private GraduacaoDTO graduacao;
	
}
