package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Professor;

@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor,Integer> {

	@Query("select p from Professor p where p.nome like %?1%")
	public List<Professor> findProfessorByName(String nome);
	
	@Query("select count(p) from Professor p")
	public Long quantidadeProfessor();
}
