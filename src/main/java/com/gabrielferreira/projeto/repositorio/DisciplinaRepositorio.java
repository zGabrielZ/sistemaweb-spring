package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Integer>{

	@Query("SELECT d FROM Disciplina d where d.nome = :nome")
	public Disciplina findByNomeDisciplina(@Param("nome") String nome);
	
	@Query("SELECT d FROM Disciplina d where d.nome = :nome and d.id <> :id")
	public Disciplina findByNomeDisciplinaAtualizado(@Param ("nome") String nome, @Param("id") Integer id);
}
