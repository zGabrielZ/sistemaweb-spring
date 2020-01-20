package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepositorio alunoRepositorio;

	public List<Aluno> consultarTodos() {
		return alunoRepositorio.findAll();
	}

	public Aluno consultarPorId(Integer id) {
		Optional<Aluno> aluno = alunoRepositorio.findById(id);
		return aluno.orElseThrow(()-> new RecursoNotFoundException(id));
	}

	public Pessoa inserir(Aluno aluno) {
		if(aluno.getId() == null) {
			return alunoRepositorio.save(aluno);
		}
		else {
			return atualizar(aluno.getId(), aluno);
		}
	}

	public void deletar(Integer id) {
		try {
			alunoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new RecursoNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Pessoa atualizar(Integer id, Aluno aluno) {
		try {
			Aluno entidade = alunoRepositorio.getOne(id);
			updateData(entidade, aluno);
			return alunoRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException(e.getMessage());
		}
	}
	
	public List<Aluno> buscarNome(String nome){
		return alunoRepositorio.findAlunoByName(nome);
	}
	
	private void updateData(Aluno entidade, Aluno aluno) {
		entidade.setNome(aluno.getNome());
		entidade.setCpf(aluno.getCpf());
		entidade.setRa(aluno.getRa());
		entidade.setCurso(aluno.getCurso());
		entidade.setNumeroDaMatricula(aluno.getNumeroDaMatricula());
		entidade.getContato().setEmail(aluno.getContato().getEmail());
		entidade.getContato().setTelefone(aluno.getContato().getTelefone());
	}

}
