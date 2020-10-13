package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno,Long> {

	@Query("select a from Aluno a where a.nomeCompleto LIKE %:nome%")
	public Page<Aluno> filtrar(@Param("nome")String nome,Pageable pageable);
}
