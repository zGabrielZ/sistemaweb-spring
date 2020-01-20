package com.gabrielferreira.projeto.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Contato;
import com.gabrielferreira.projeto.repositorio.ContatoRepositorio;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepositorio contatoRepositorio;
	
	public List<Contato> consultarTodos(){
		return contatoRepositorio.findAll();
	}
	
	
}
