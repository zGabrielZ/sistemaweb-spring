package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;	
import com.gabrielferreira.projeto.modelo.entidade.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Long> {

	@Query("select c from Curso c where c.nomeCurso LIKE %:nome%")
	public Page<Curso> filtrar(@Param("nome")String nome,Pageable pageable);
}
