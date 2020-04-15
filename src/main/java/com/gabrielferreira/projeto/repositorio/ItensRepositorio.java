package com.gabrielferreira.projeto.repositorio;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Itens;

@Repository
public interface ItensRepositorio extends JpaRepository<Itens,Integer> {
	
	@Query("select i From Itens i inner join i.pessoa p inner join i.disciplina d where p.nome like :nome%")
	public List<Itens> getItens(@Param("nome")String nome);
}

