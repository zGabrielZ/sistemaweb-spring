package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Turma;
import com.gabrielferreira.projeto.modelo.entidade.dto.TurmaInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.enums.Turno;
import com.gabrielferreira.projeto.repositorio.TurmaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepositorio turmaRepositorio;
	
	@Autowired
	private ProfessorService professorService;
	
	public Turma inserir(Turma turma) {
		
		Professor professor = professorService.buscarPorId(turma.getProfessor().getId());
		
		turma.setProfessor(professor);
		professor.getTurmas().add(turma);
		
		return turmaRepositorio.save(turma);
	}
	
	public List<Turma> listagem(){
		return turmaRepositorio.findAll();
	}
	
	public Turma buscarPorId(Long id) {
		Optional<Turma> turma = turmaRepositorio.findById(id);
		if(!turma.isPresent()) {
			throw new EntidadeNotFoundException("Turma não encontrada");
		}
		
		return turma.get();
	}
	
	public void deletar(Long id) {
		try {
			turmaRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Turma não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com outra entidade");
		}
	}
	
	public Page<Turma> buscarPagina(Integer pagina,Integer linhasPorPagina,String ordernarPor,String direcao,String nome){
		PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina,Direction.valueOf(direcao),ordernarPor);
		return turmaRepositorio.filtrar(nome,pageRequest);
	}
	
	public Turma fromDto(TurmaInserirDTO turmaInserirDTO) {
		
		Turma turma = new Turma(null,turmaInserirDTO.getNomeTurma(),turmaInserirDTO.getNumeroTurma(),
				turmaInserirDTO.getVagas(),Turno.converterParaEnum(turmaInserirDTO.getTurno()));
		
		Professor professor = new Professor(turmaInserirDTO.getProfessor(),null,null,null,null,null);
		
		turma.setProfessor(professor);
		professor.getTurmas().add(turma);
		
		return turma;
		
	}
}
