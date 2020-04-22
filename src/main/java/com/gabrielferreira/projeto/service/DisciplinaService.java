package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	public List<Disciplina> consultarTodos(){
		return disciplinaRepositorio.findAll();
	}
	
	public Disciplina consultarPorId(Integer id) {
		Optional<Disciplina> disciplina = disciplinaRepositorio.findById(id);
		return disciplina.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	public List<Disciplina> consultarTodos(Integer id){
		return disciplinaRepositorio.getDisciplinas(id);
	}
	
	public Disciplina inserir(Disciplina disciplina) {
		if(disciplina.getId() == null) {
			return disciplinaRepositorio.save(disciplina);
		}
		else {
			return atualizar(disciplina.getId(), disciplina);
		}
	}
	
	public void deletar(Integer id) {
		try {
			disciplinaRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Disciplina atualizar(Integer id,Disciplina disciplina) {
		try {
			Disciplina entidade = disciplinaRepositorio.getOne(id);
			updateData(entidade,disciplina);
			return disciplinaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Disciplina entidade,Disciplina disciplina) {
		entidade.setNome(disciplina.getNome());
	}
	
	public List<Disciplina> buscarNome(String nome){
		return disciplinaRepositorio.findDisciplinaByNameDisciplina(nome);
	}
	
	public boolean buscarNomeDisciplina(Disciplina disciplina) {
		return disciplinaRepositorio.findDisciplinaByNome(disciplina.getNome()) != null;
	}
	
	public boolean buscarNomeAtualizado(Disciplina disciplina) {
		return disciplinaRepositorio.findDisciplinaByNomeAtualizado(disciplina.getNome(),disciplina.getId()) 
				!=null;
	}

	
}
