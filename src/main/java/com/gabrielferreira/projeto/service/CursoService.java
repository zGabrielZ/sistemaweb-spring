package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
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
	
	public Curso inserir(Curso curso) {
		if(curso.getId() == null) {
			return cursoRepositorio.save(curso);
		}
		else {
			return atualizar(curso.getId(), curso);
		}
	}
	
	public void deletar(Integer id) {
		try {
			cursoRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Curso atualizar(Integer id,Curso curso) {
		try {
			Curso entidade = cursoRepositorio.getOne(id);
			updateData(entidade,curso);
			return cursoRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Curso entidade,Curso curso) {
		entidade.setNome(curso.getNome());
	}
	
	public List<Curso> buscarNome(String nome){
		return cursoRepositorio.findCursoByNameCurso(nome);
	}
	
	public boolean buscarNomeCurso(Curso curso) {
		return cursoRepositorio.findCursoByNome(curso.getNome()) != null;
	}
	
	public boolean buscarNomeAtualizado(Curso curso) {
		return cursoRepositorio.findCursoByNomeAtualizado(
				curso.getNome(),curso.getId()) 
				!=null;
	}
}
