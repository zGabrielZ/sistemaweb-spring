package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;

public interface TipoTelefoneRepositorio extends JpaRepository<TipoTelefone,Integer> {
	
}
