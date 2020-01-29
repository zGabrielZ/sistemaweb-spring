package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	public List<Endereco> consultarTodos(){
		return enderecoRepositorio.findAll();
	}
	
	public Endereco consultarPorId(Integer id) {
		Optional<Endereco> enderecos = enderecoRepositorio.findById(id);
		return enderecos.orElseThrow(() -> new RecursoNotFoundException(id));
	}

	
}
