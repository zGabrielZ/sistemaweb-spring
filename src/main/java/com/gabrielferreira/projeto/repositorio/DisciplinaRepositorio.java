package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina,Integer> {

	@Query("select d from Disciplina d inner join d.pessoas p where p.id = ?1")
	public List<Disciplina> getDisciplinas(Integer alunoid);
	
	@Query("select d from Disciplina d where d.nome like %?1%")
	public List<Disciplina> findDisciplinaByNameDisciplina(String nome);
	
	public Disciplina findDisciplinaByNome(String nome);

	@Query("select d from Disciplina d where d.nome = :nome and d.id <> :id")
	public Disciplina findDisciplinaByNomeAtualizado
	(@Param("nome")String nome,
			@Param("id")Integer id);
}
