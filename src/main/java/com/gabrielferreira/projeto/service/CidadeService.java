package com.gabrielferreira.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.Cidade;
import com.gabrielferreira.projeto.modelo.Estado;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;

@Service
public class CidadeService {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	public List<Cidade> getCidadesPorEstado(Integer idEstado){
		Estado estado = estadoService.getEstado(idEstado);
		List<Cidade> cidades = cidadeRepositorio.getCidadesPorEstado(estado.getId());
		return cidades;
	}
	
}
