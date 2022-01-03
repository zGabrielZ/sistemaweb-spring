package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabrielferreira.projeto.modelo.Professor;

@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor, Integer>{

}
