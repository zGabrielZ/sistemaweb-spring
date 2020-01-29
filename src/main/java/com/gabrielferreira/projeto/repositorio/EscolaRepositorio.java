package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Escola;

@Repository
public interface EscolaRepositorio extends JpaRepository<Escola,Integer> {

	@Query("select e from Escola e where e.nome like %?1%")
	public List<Escola> findEscolaByNameEscola(String nome);
	
	public Escola findEscolaByNome(String nome);

	@Query("select e from Escola e where e.nome = :nome and e.id <> :id")
	public Escola findEscolaByNomeAtualizado(@Param("nome")String nome,
			@Param("id")Integer id);
}
