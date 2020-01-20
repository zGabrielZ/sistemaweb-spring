package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabrielferreira.projeto.modelo.entidade.Pessoa;

public interface PessoaRepositorio extends JpaRepository<Pessoa,Integer> {
	
	public Pessoa findPessoaByCpf(String cpf);

	@Query("select p from Pessoa p where p.cpf = :cpf and p.id <> :id")
	public Pessoa findPessoaByCpfAtualizado(@Param("cpf")String cpf,
			@Param("id")Integer id);
}
