package com.gabrielferreira.projeto.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
@Service
public class DbService {
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;


	public void instanciarTesteDatabase() {
		
		Disciplina disciplina1 = new Disciplina(null,"Cálculo 1");
		Disciplina disciplina2 = new Disciplina(null,"Programação");
		Disciplina disciplina3 = new Disciplina(null,"Física 1");
	
		Curso curso1 = new Curso(null,"Engenharia Da Computação");
		Curso curso2 = new Curso(null,"Engenharia Da Produção");
		Curso curso3 = new Curso(null,"Engenharia Cívil");
	
		cursoRepositorio.saveAll(Arrays.asList(curso1,curso2,curso3));
		disciplinaRepositorio.saveAll(Arrays.asList(disciplina1,disciplina2,disciplina3));
	}
}
