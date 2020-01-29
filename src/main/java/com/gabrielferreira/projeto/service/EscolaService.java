package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.repositorio.EscolaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class EscolaService {

	@Autowired
	private EscolaRepositorio escolaRepositorio;
	
	public Escola consultarPorId(Integer id) {
		Optional<Escola> escola = escolaRepositorio.findById(id);
		return escola.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	public List<Escola> consultarTodos(){
		return escolaRepositorio.findAll();
	}
	
	public Escola inserir(Escola escola) {
		if(escola.getId() == null) {
			return escolaRepositorio.save(escola);
		}
		else {
			return atualizar(escola.getId(), escola);
		}
	}
	
	public void deletar(Integer id) {
		try {
			escolaRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Escola atualizar(Integer id,Escola escola) {
		try {
			Escola entidade = escolaRepositorio.getOne(id);
			updateData(entidade,escola);
			return escolaRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Escola entidade,Escola escola) {
		entidade.setNome(escola.getNome());
	}
	
	public List<Escola> buscarNome(String nome){
		return escolaRepositorio.findEscolaByNameEscola(nome);
	}
	
	public boolean buscarNomeEscola(Escola escola) {
		return escolaRepositorio.findEscolaByNome(escola.getNome()) != null;
	}
	
	public boolean buscarNomeAtualizado(Escola escola) {
		return escolaRepositorio.findEscolaByNomeAtualizado(escola.getNome(),escola.getId()) 
				!=null;
	}
}
