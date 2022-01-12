package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT p FROM Pessoa p join p.curso c where c.id = :idCurso")
	public List<Pessoa> getPessoasPorCurso(@Param("idCurso") Integer idCurso);
}
