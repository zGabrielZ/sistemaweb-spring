package com.gabrielferreira.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielferreira.projeto.modelo.Cidade;
import com.gabrielferreira.projeto.modelo.Estado;
import com.gabrielferreira.projeto.service.CidadeService;
import com.gabrielferreira.projeto.service.EstadoService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/cidades-estado/{idEstado}")
	public List<Cidade> cidadesPorEstado(@PathVariable Integer idEstado){
		Estado estado = estadoService.getEstado(idEstado);
		List<Cidade> cidades = cidadeService.getCidadesPorEstado(estado.getId());
		return cidades;
	}
}
