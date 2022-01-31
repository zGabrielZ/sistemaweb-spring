package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(exclude = {"endereco","curso","telefones","itens"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PESSOA_SEQ", sequenceName = "SEQ_PESSOA", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "PESSOA_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "nome",nullable = false,length = 150)
	private String nome;
	
	@Column(name = "sobrenome",nullable = false,length = 150)
	private String sobrenome;
	
	@Column(name = "cpf",nullable = false,length = 14)
	private String cpf;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento",nullable = false)
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sexo",nullable = false)
	private Sexo sexo;

	@JoinColumn(name = "endereco_id",nullable = false,foreignKey = @ForeignKey(name="endereco_fk"))
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@JoinColumn(name = "curso_id",nullable = false,foreignKey = @ForeignKey(name="curso_fk"))
	@ManyToOne
	private Curso curso;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pessoa",cascade = CascadeType.REMOVE)
	private List<Telefone> telefones = new ArrayList<Telefone>();
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "pessoa")
	private List<Itens> itens = new ArrayList<Itens>();
	
}
