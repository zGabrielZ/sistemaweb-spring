package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.Cidade;

@Repository
public interface CidadeRepositorio extends JpaRepository<Cidade, Integer>{

	@Query("SELECT c FROM Cidade c join c.estado e where e.id = :idEstado")
	public List<Cidade> getCidadesPorEstado(@Param("idEstado") Integer idEstado);
}
