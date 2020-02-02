package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Telefone;

@Repository
public interface TelefoneRepositorio extends JpaRepository<Telefone,Integer> {

	@Query("select t from Telefone t where t.pessoa.id = ?1")
	public List<Telefone> getTelefones(Integer id);
	

	
}
