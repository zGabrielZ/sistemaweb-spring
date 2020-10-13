package com.gabrielferreira.projeto.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	public Disciplina consultarPorId(Long idDisciplina) {
		
		Disciplina disciplina = disciplinaRepositorio.findById(idDisciplina)
				.orElseThrow(()-> new EntidadeNotFoundException("Disciplina não encontrado"));
		return disciplina;
	}
	
	public Disciplina inserir(Disciplina disciplina) {
		return disciplinaRepositorio.save(disciplina);
	}
	
	public List<Disciplina> listagem(){
		return disciplinaRepositorio.findAll();
	}
	
	public Page<Disciplina> buscarPagina(Integer pagina,Integer linhasPorPagina,String ordernarPor,String direcao,String nome){
		PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina,Direction.valueOf(direcao),ordernarPor);
		return disciplinaRepositorio.filtrar(nome,pageRequest);
	}
	
	public Disciplina atualizar(Long idDisciplina,Disciplina disciplina) {
		try {
			Disciplina entidade = disciplinaRepositorio.getOne(idDisciplina);
			updateData(entidade,disciplina);			
			return disciplinaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Disciplina não encontrado");
		}
	}

	private void updateData(Disciplina entidade, Disciplina disciplina) {
		entidade.setNomeDisciplina(disciplina.getNomeDisciplina());
	}
}

