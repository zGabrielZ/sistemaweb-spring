package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class CursoService {

	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	public Curso consultarPorId(Integer id) {
		Optional<Curso> curso = cursoRepositorio.findById(id);
		return curso.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	public List<Curso> consultarTodos(){
		return cursoRepositorio.findAll();
	}
	
}
