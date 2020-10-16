package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielferreira.projeto.modelo.entidade.Sala;
import com.gabrielferreira.projeto.modelo.entidade.dto.SalaInserirDTO;
import com.gabrielferreira.projeto.service.SalaService;

@RestController
@RequestMapping(value = "/salas")
public class SalaController {

	@Autowired
	private SalaService salaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Sala> cadastrarSala(@Valid @RequestBody SalaInserirDTO salaInserirDTO){
		Sala sala = paraInserirDto(salaInserirDTO);
		sala = salaService.inserir(sala);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(sala.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
		Sala sala = salaService.buscarPorId(id);
		salaService.deletar(sala);
		return ResponseEntity.noContent().build();
	}
		
	public Sala paraInserirDto(SalaInserirDTO inserirDTO) {
		return modelMapper.map(inserirDTO,Sala.class);
	}

}
