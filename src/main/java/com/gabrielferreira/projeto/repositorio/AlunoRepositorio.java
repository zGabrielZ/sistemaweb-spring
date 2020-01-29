package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno,Integer> {

	@Query("select a from Aluno a where a.nome like %?1%")
	public List<Aluno> findAlunoByName(String nome);
}
