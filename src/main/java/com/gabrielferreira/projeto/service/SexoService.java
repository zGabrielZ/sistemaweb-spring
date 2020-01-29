package com.gabrielferreira.projeto.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Sexo;
import com.gabrielferreira.projeto.repositorio.SexoRepositorio;

@Service
public class SexoService {

	@Autowired
	private SexoRepositorio sexoRepositorio;
	
	public List<Sexo> consultarTodos(){
		return sexoRepositorio.findAll();
	}

	
}
