package com.gabrielferreira.projeto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;

@Repository
public interface TelefoneRepositorio extends JpaRepository<Telefone,Long> {

	@Query("select t from Telefone t where t.pessoa.id =:idPessoa and t.id =:idTelefone")
	public Optional<Telefone> verificarTelefonePessoa(Long idPessoa,Long idTelefone);
}
