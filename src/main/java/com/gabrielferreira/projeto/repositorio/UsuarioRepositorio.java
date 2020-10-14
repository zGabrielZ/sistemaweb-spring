package com.gabrielferreira.projeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gabrielferreira.projeto.modelo.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

	Usuario findByEmail(String email);
}
