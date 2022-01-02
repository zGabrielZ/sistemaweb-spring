package com.gabrielferreira.projeto.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ENDERECO")
@Getter
@Setter
@ToString(exclude = {"cidade","pessoa"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "SEQ_ENDERECO", allocationSize = 1, initialValue = 1 )
	@GeneratedValue(generator = "ENDERECO_SEQ",strategy = GenerationType.SEQUENCE)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "cep",nullable = false)
	private String cep;
	
	@Column(name = "logradouro",nullable = false)
	private String logradouro;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "cidade",nullable = false)
	private String bairro;
	
	@JoinColumn(name = "endereco_pessoa_id",nullable = false,foreignKey = @ForeignKey(name="endereco_pessoa_fk") )
	@OneToOne(cascade = CascadeType.ALL)
	private EnderecoPessoa numero;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "endereco")
	private List<Pessoa> pessoa = new ArrayList<Pessoa>();
	
	@JoinColumn(name = "cidade_id",nullable = false,foreignKey = @ForeignKey(name="cidade_fk"))
	@ManyToOne
	private Cidade cidade;	
	
}
