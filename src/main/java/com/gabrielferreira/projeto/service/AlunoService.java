package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	public Aluno consultarPorId(Integer id) {
		Optional<Aluno> aluno = alunoRepositorio.findById(id);
		return aluno.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	public List<Aluno> consultarTodos(){
		return alunoRepositorio.findAll();
	}
	
	public Long quantidade(){
		return alunoRepositorio.quantidadeAluno();
	}
	
	public Pessoa inserir(Pessoa aluno) {
		if(aluno.getId() == null) {
			cursoRepositorio.save(aluno.getCurso());
			cidadeRepositorio.save(aluno.getEndereco().getCidade());
			pessoaRepositorio.save(aluno);
			enderecoRepositorio.save(aluno.getEndereco());
			return aluno;
		}
		else {
			return (Aluno) atualizar(aluno.getId(), aluno);
		}
	}
	
	public void deletar(Integer id) {
		try {
			pessoaRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Pessoa atualizar(Integer id,Pessoa aluno) {
		try {
			Pessoa entidade = alunoRepositorio.getOne(id);
			updateData(entidade,aluno);
			return pessoaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Pessoa entidade,Pessoa aluno) {
		entidade.setNome(aluno.getNome());
		entidade.setSobrenome(aluno.getSobrenome());
		entidade.setCpf(aluno.getCpf());
		Aluno aluno4 = (Aluno) entidade;
		Aluno aluno5 = (Aluno) aluno;
		aluno4.setRa(aluno5.getRa());
		entidade.setCurso(aluno.getCurso());
		entidade.setEndereco(aluno.getEndereco());
		entidade.setSexo(aluno.getSexo());
	}
	
	public List<Aluno> buscarNome(String nome){
		return alunoRepositorio.findAlunoByName(nome);
	}
	
}
