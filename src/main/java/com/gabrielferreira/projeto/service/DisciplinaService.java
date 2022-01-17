package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.Disciplina;
import com.gabrielferreira.projeto.modelo.to.DisciplinaTo;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Transactional
	public Disciplina inserir(DisciplinaTo disciplinaTo) {
		Disciplina disciplina = new DisciplinaTo().getDisciplina(disciplinaTo);
		validarNomeDisciplina(disciplina);
		return disciplinaRepositorio.save(disciplina);
	}
	
	public List<DisciplinaTo> getDisciplinas(){
		List<Disciplina> disciplinas = disciplinaRepositorio.findAll();
		List<DisciplinaTo> disciplinaTo = disciplinas.stream().map(d -> new DisciplinaTo(d)).collect(Collectors.toList());
		return disciplinaTo;
	}
	
	public void deletar(Integer id) {
		Disciplina disciplina = getDisciplina(id);
		disciplinaRepositorio.deleteById(disciplina.getId());
	}
	
	public Disciplina getDisciplina(Integer id) {
		Optional<Disciplina> optionalDisciplina = disciplinaRepositorio.findById(id);
		if(!optionalDisciplina.isPresent()) {
			throw new EntidadeException("Disciplina não encontrada.");
		}
		return optionalDisciplina.get();
	}
	
	private void validarNomeDisciplina(Disciplina disciplina) {
		// Validar o nome da disciplina ao inserir
		if(disciplina.getId() == null) {
			
			Disciplina disciplinaValidacao = disciplinaRepositorio.findByNomeDisciplina(disciplina.getNome());
			
			if(disciplinaValidacao != null) {
				throw new RegraException("Nome ja existente.");
			}
		} 
		// Validar o nome da disciplina ao atualizar
		else if(disciplina.getId() != null) {
			Disciplina disciplinaValidacao = disciplinaRepositorio.findByNomeDisciplinaAtualizado(disciplina.getNome(),disciplina.getId());
			if(disciplinaValidacao != null) {
				throw new RegraException("Nome ja existente ao atualizar.");
			}
		}
	}
}
