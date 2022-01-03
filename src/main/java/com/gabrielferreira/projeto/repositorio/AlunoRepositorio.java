package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.Aluno;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Integer>{

}
