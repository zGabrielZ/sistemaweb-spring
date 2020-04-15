package com.gabrielferreira.projeto.service;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.repositorio.TelefoneRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneRepositorio telefoneRepositorio;
	
	public List<Telefone> consultarTodos(Integer id){
		return telefoneRepositorio.getTelefones(id);
	}
	
	public Optional<Telefone> consultarPorId(Integer id) {
		return telefoneRepositorio.findById(id);
	}

	public void deletar(Integer id) {
		try {
			telefoneRepositorio.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Telefone inserir(Telefone telefone) {
		if(telefone.getId() == null) {
			telefoneRepositorio.save(telefone);
			return telefone;
		}
		else {
			return atualizar(telefone.getId(), telefone);
		}
	}
	
	public Telefone atualizar(Integer id,Telefone telefone) {
		try {
			Telefone entidade = telefoneRepositorio.getOne(id);
			updateData(entidade,telefone);
			return telefoneRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(Telefone entidade,Telefone telefone) {
		entidade.setNome(telefone.getNome());
		entidade.setTipoTelefone(telefone.getTipoTelefone());
	}

}
