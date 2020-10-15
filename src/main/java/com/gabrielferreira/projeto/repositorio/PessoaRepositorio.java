package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa,Long> {

	Pessoa findByEmail(String email);
}
