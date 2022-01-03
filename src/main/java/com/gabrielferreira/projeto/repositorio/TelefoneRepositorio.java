package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.Telefone;

@Repository
public interface TelefoneRepositorio extends JpaRepository<Telefone, Integer>{

}
