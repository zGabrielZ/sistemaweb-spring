package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielferreira.projeto.modelo.entidade.Contato;

public interface ContatoRepositorio extends JpaRepository<Contato,Integer> {
	
}
