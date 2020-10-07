package com.gabrielferreira.projeto.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class CursoService {

	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	public Curso consultarPorId(Long idCurso) {
		
		Curso curso = cursoRepositorio.findById(idCurso)
				.orElseThrow(()-> new EntidadeNotFoundException("Curso não encontrado"));
		return curso;
	}
	
	public Curso inserir(Curso curso) {
		return cursoRepositorio.save(curso);
	}
	
	public List<Curso> listagem(){
		return cursoRepositorio.findAll();
	}
}

