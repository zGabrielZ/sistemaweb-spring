package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielferreira.projeto.modelo.entidade.Telefone;

public interface TelefoneRepositorio extends JpaRepository<Telefone,Integer> {
	
}
