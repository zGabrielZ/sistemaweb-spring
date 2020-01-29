package com.gabrielferreira.projeto.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Estado;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	public List<Estado> consultarTodos(){
		return estadoRepositorio.findAll();
	}

	
}
