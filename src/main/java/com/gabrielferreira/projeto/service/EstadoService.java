package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.modelo.Estado;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	public List<Estado> getEstados(){
		List<Estado> estados = estadoRepositorio.findAll();
		return estados;
	}
	
	public Estado getEstado(Integer id) {
		Optional<Estado> estadoOp = estadoRepositorio.findById(id);
		if(!estadoOp.isPresent()) {
			throw new EntidadeException("Estado não encontrado");
		}
		return estadoOp.get();
	}
	
}
