package com.gabrielferreira.projeto.modelo.entidade;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gabrielferreira.projeto.modelo.entidade.enums.Perfil;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tab_usuario")
@JsonTypeName("usuario") 
public class Usuario extends Pessoa {

	private static final long serialVersionUID = 1L;
			
	public Usuario() {
		addPerfil(Perfil.ADMIN);
	}

	public Usuario(Long id, String nomeCompleto, String cpf, Sexo sexo,String email,String senha) {
		super(id, nomeCompleto, cpf, sexo,email,senha);
		addPerfil(Perfil.ADMIN);
	}
	
	
	
}
