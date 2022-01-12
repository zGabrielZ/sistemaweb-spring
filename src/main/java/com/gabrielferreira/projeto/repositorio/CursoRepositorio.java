package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Integer>{

	@Query("SELECT c FROM Curso c where c.nome = :nome")
	public Curso findByNomeCurso(@Param("nome") String nome);
	
	@Query("SELECT c FROM Curso c where c.nome = :nome and c.id <> :id")
	public Curso findByNomeCursoAtualizado(@Param ("nome") String nome, @Param("id") Integer id);
}
