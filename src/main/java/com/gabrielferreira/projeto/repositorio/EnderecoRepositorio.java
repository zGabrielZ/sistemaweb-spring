package com.gabrielferreira.projeto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Endereco;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco,Long> {

	@Query("select e from Endereco e where e.pessoa.id =:idPessoa and e.id =:idEndereco")
	public Optional<Endereco> verificarEnderecoPessoa(Long idPessoa,Long idEndereco);
}
