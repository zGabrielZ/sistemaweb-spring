package com.gabrielferreira.projeto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.TipoTelefone;

@Repository
public interface TipoTelefoneRepositorio extends JpaRepository<TipoTelefone, Integer>{

	@Query("SELECT t FROM TipoTelefone t where t.descricao like %:descricao%")
	public List<TipoTelefone> findDescricao(@Param("descricao") String descricao);
}
