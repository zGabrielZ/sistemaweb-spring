package com.gabrielferreira.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Aula;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.dto.AulaInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.enums.Semestre;
import com.gabrielferreira.projeto.repositorio.AulaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class AulaService {

	@Autowired
	private AulaRepositorio aulaRepositorio;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private AlunoService alunoService;
	
	public Aula inserir(Aula aula) {
		return aulaRepositorio.save(aula);
	}
		
	public Aula buscarPorId(Long id) {
		Optional<Aula> aula = aulaRepositorio.findById(id);
		if(!aula.isPresent()) {
			throw new EntidadeNotFoundException("Aula não encontrada");
		}
		
		return aula.get();
	}
	
	public void deletar(Long id) {
		try {
			aulaRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Aula não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com outra entidade");
		}
	}
	
	public Aula fromDto(AulaInserirDTO aulaInserirDTO) {
		
		Aula aula = new Aula();
		
		Disciplina disciplina = new Disciplina(aulaInserirDTO.getDisciplina(),null,null);
		disciplina = disciplinaService.consultarPorId(disciplina.getId());
		
		aula.setDisciplina(disciplina);
		disciplina.getAulas().add(aula);
		
		Aluno aluno = new Aluno(aulaInserirDTO.getAluno(),null,null,null,null,null,null,null);
		aluno = alunoService.buscarPorId(aluno.getId());
		
		aula.setAluno(aluno);
		aluno.getAulas().add(aula);
		
		aula.setSemestre(Semestre.converterParaEnum(aulaInserirDTO.getSemestre()));
				
		return aula;
	}
	
	
}
