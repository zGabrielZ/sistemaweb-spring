package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.Curso;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.to.CursoTo;
import com.gabrielferreira.projeto.modelo.to.consulta.ConsultaPessoaTo;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;

@Service
public class CursoService {

	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Transactional
	public Curso inserir(CursoTo cursoTo) {
		Curso curso = new CursoTo().getCurso(cursoTo);
		validarNomeCurso(curso);
		return cursoRepositorio.save(curso);
	}
	
	public List<CursoTo> getCursos(){
		List<Curso> cursos = cursoRepositorio.findAll();
		List<CursoTo> cursoTos = cursos.stream().map(c -> new CursoTo(c)).collect(Collectors.toList());
		return cursoTos;
	}
	
	public void deletar(Integer id) {
		Curso curso = getCurso(id);
		cursoRepositorio.deleteById(curso.getId());
	}
	
	public Curso getCurso(Integer id) {
		Optional<Curso> optionalCurso = cursoRepositorio.findById(id);
		if(!optionalCurso.isPresent()) {
			throw new EntidadeException("Curso não encontrado.");
		}
		return optionalCurso.get();
	}
	
	public List<ConsultaPessoaTo> getPessoasPorCurso(Integer idCurso){
		List<Pessoa> pessoas = pessoaRepositorio.getPessoasPorCurso(idCurso);
		List<ConsultaPessoaTo> pessoasTo = pessoas.stream().map(p -> new ConsultaPessoaTo(p)).collect(Collectors.toList());
		return pessoasTo;
	}
	
	private void validarNomeCurso(Curso curso) {
		
		// Validar o nome do curso ao inserir
		if(curso.getId() == null) {
			
			Curso cursoValidacao = cursoRepositorio.findByNomeCurso(curso.getNome());
			
			if(cursoValidacao != null) {
				throw new RegraException("Nome ja existente.");
			}
		} 
		// Validar o nome do curso ao atualizar
		else if(curso.getId() != null) {
			Curso cursoValidacao = cursoRepositorio.findByNomeCursoAtualizado(curso.getNome(),curso.getId());
			if(cursoValidacao != null) {
				throw new RegraException("Nome ja existente ao atualizar.");
			}
		}
	}
}
