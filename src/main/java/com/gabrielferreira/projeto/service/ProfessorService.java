package com.gabrielferreira.projeto.service;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.ProfessorRepositorio;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.EscolaRepositorio;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.repositorio.SexoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepositorio professorRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private EscolaRepositorio escolaRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private SexoRepositorio sexoRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	public Professor consultarPorId(Integer id) {
		Optional<Professor> professor = professorRepositorio.findById(id);
		return professor.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	
	public List<Professor> consultarTodos(){
		return professorRepositorio.findAll();
	}
	
	public Long quantidade(){
		return professorRepositorio.quantidadeProfessor();
	}
	
	public Pessoa inserir(Pessoa professor) {
		if(professor.getId() == null) {
			cursoRepositorio.save(professor.getCurso());
			escolaRepositorio.save(professor.getEscola());
			sexoRepositorio.save(professor.getSexo());
			estadoRepositorio.save(professor.getEndereco().getCidade().getEstado());
			cidadeRepositorio.save(professor.getEndereco().getCidade());
			pessoaRepositorio.save(professor);
			enderecoRepositorio.save(professor.getEndereco());
			return professor;
		}
		else {
			return (Professor) atualizar(professor.getId(), professor);
		}
	}
	
	public void deletar(Integer id) {
		try {
			pessoaRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Pessoa atualizar(Integer id,Pessoa professor) {
		try {
			Pessoa entidade = professorRepositorio.getOne(id);
			updateData(entidade,professor);
			return pessoaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Pessoa entidade,Pessoa professor) {
		entidade.setNome(professor.getNome());
		entidade.setSobrenome(professor.getSobrenome());
		entidade.setCpf(professor.getCpf());
		Professor professor2 = (Professor) entidade;
		Professor professor3 = (Professor) professor;
		professor2.setSalario(professor3.getSalario());
		entidade.setEscola(professor.getEscola());
		entidade.setCurso(professor.getCurso());
		entidade.setEndereco(professor.getEndereco());
		entidade.setSexo(professor.getSexo());
	}
	
	public List<Professor> buscarNome(String nome){
		return professorRepositorio.findProfessorByName(nome);
	}
	
}
