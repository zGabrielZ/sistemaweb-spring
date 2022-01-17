package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.TipoTelefone;

@Repository
public interface TipoTelefoneRepositorio extends JpaRepository<TipoTelefone, Integer>{

}
