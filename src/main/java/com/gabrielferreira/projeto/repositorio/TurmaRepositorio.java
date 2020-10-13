package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Turma;

@Repository
public interface TurmaRepositorio extends JpaRepository<Turma,Long> {

	@Query("select t from Turma t where t.nomeTurma LIKE %:nome%")
	public Page<Turma> filtrar(@Param("nome")String nome,Pageable pageable);
}
