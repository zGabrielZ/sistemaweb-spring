package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.repositorio.ProfessorRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepositorio professorRepositorio;
	
	public List<Professor> consultarTodos(){
		return professorRepositorio.findAll();
	}
	
	public Professor consultarPorId(Integer id) {
		Optional<Professor> professor = professorRepositorio.findById(id);
		return professor.orElseThrow(()-> new RecursoNotFoundException(id));
	}
	
	public Pessoa inserir(Professor professor) {
		if(professor.getId() == null) {
			return professorRepositorio.save(professor);
		}
		else {
			return atualizar(professor.getId(), professor);
		}
	}

	public void deletar(Integer id) {
		try {
			professorRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public List<Professor> buscarNome(String nome){
		return professorRepositorio.findProfessorByName(nome);
	}
	
	public Pessoa atualizar(Integer id, Professor professor) {
		try {
			Professor entidade = professorRepositorio.getOne(id);
			updateData(entidade, professor);
			return professorRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException(e.getMessage());
		}
	}

	private void updateData(Professor entidade,Professor professor) {
		entidade.setNome(professor.getNome());
		entidade.setCpf(professor.getCpf());
		entidade.setDisciplina(professor.getDisciplina());
		entidade.setSalario(professor.getSalario());
		entidade.getContato().setEmail(professor.getContato().getEmail());
		entidade.getContato().setTelefone(professor.getContato().getTelefone());
	}
}
