package com.gabrielferreira.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Sala;
import com.gabrielferreira.projeto.modelo.entidade.Turma;
import com.gabrielferreira.projeto.repositorio.SalaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;
import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

@Service
public class SalaService {

	@Autowired
	private SalaRepositorio salaRepositorio;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private AlunoService alunoService;
	
	public Sala inserir(Sala sala) {
		
		Aluno aluno = alunoService.buscarPorId(sala.getAluno().getId());
		sala.setAluno(aluno);
		aluno.getSalas().add(sala);
		
		Turma turma = turmaService.buscarPorId(sala.getTurma().getId());
		sala.setTurma(turma);
		turma.getSalas().add(sala);
		
		Integer vagasAtual = turma.getVagas() - 1;
		sala.getTurma().setVagas(vagasAtual);
		
		if(vagasAtual < 0) {
			throw new RegraDeNegocioException("Não podemos inserir este aluno nesta turma, porque já esgotou as vagas");
		}
		
		
		return salaRepositorio.save(sala);
	}
		
	public Sala buscarPorId(Long id) {
		Optional<Sala> sala = salaRepositorio.findById(id);
		if(!sala.isPresent()) {
			throw new EntidadeNotFoundException("Sala não encontrada");
		}
		
		return sala.get();
	}
	
	public void deletar(Sala sala) {
		try {
			
			Integer vagasAtual = sala.getTurma().getVagas() + 1;
			sala.getTurma().setVagas(vagasAtual);
			
			salaRepositorio.deleteById(sala.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Sala não encontrada");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com outra entidade");
		}
	}
		
}
