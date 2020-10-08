package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Curso;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso,Long> {

}