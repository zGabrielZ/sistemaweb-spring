package com.gabrielferreira.projeto.modelo.entidade.dto;

import java.io.Serializable;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorInserirDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final String MY_TIME_ZONE="GMT-3";
	
	@NotBlank(message = "Campo do nome não pode ser vazio")
	@Size(max = 150,message = "Não pode passa de 150 caracteres")
	private String nomeCompleto;
	
	@CPF(message = "Cpf inválido")
	@NotBlank(message = "Campo do cpf não pode ser vazio")
	private String cpf;
	
	@NotNull(message = "Tem que escolher o sexo")
	private Integer sexo;
	
	@JsonFormat(pattern = "dd/MM/yyyy",timezone =MY_TIME_ZONE)
	@NotNull(message = "Campo do ano admissão não pode ser vazio ou digitado incorretamente")
	private Date anoAdmissao;
	
	@NotNull(message = "Campo de horas não pode ser vazio")
	private Integer qtdHoras;
	
	@Valid
	@NotNull(message = "Graduação do professor não pode ser vazio")
	private GraduacaoInserirDTO graduacao;
			
	@Valid
	@NotNull(message = "Endereço do aluno não pode ser vazio")
	private EnderecoInserirDTO endereco;
}
