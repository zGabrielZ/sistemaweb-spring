package com.gabrielferreira.projeto.modelo.to;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabrielferreira.projeto.modelo.Pessoa;

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
public class PessoaTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Size(min=5, max = 150, message="O campo nome deve ter no mínimo 5 até 150 caracteres")
	private String nome;
	
	@NotBlank(message = "Sobrenome não pode ser vazio")
	@Size(min=5, max = 150, message="O campo sobrenome deve ter no mínimo 5 até 150 caracteres")
	private String sobrenome;
	
	@NotBlank(message = "CPF não pode ser vazio")
	@CPF(message = "CPF Inválido")
	private String cpf;
	
	@NotNull(message = "Data de nascimento não pode ser vazio")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@NotNull(message = "Sexo não pode ser vazio")
	private Integer sexo;
	
	@NotNull(message = "Curso não pode ser vazio")
	private Integer idCurso;
	
	@NotBlank(message = "CEP não pode ser vazio")
	private String cep;
	
	@NotBlank(message = "Logradouro não pode ser vazio")
	private String logradouro;
	
	private String complemento;
	
	@NotBlank(message = "Bairro não pode ser vazio")
	private String bairro;
	
	@NotBlank(message = "Número não pode ser vazio")
	private String numero;
	
	@NotNull(message = "Cidade não pode ser vazio")
	private Integer idCidade;
	
	@NotNull(message = "Estado não pode ser vazio")
	private Integer idEstado;
	
	public PessoaTo(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.sobrenome = pessoa.getSobrenome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.sexo = pessoa.getSexo().getId();
		this.idCurso = pessoa.getCurso().getId();
		this.cep = pessoa.getEndereco().getCep();
		this.logradouro = pessoa.getEndereco().getLogradouro();
		this.complemento = pessoa.getEndereco().getComplemento();
		this.bairro = pessoa.getEndereco().getBairro();
		this.numero = pessoa.getEndereco().getNumero().getNumero();
		this.idCidade = pessoa.getEndereco().getCidade().getId();
		this.idEstado = pessoa.getEndereco().getCidade().getEstado().getId();
	}
	
}
