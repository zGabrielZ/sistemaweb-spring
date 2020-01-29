package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Integer> {

	@Query("select c from Curso c where c.nome like %?1%")
	public List<Curso> findCursoByNameCurso(String nome);
	
	public Curso findCursoByNome(String nome);

	@Query("select c from Curso c where c.nome = :nome and c.id <> :id")
	public Curso findCursoByNomeAtualizado
	(@Param("nome")String nome,
			@Param("id")Integer id);
}
