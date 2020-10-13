package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Sala;

@Repository
public interface SalaRepositorio extends JpaRepository<Sala,Long> {

}
