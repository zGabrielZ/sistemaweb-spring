package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielferreira.projeto.modelo.entidade.Aula;
import com.gabrielferreira.projeto.modelo.entidade.dto.AulaDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AulaInserirDTO;
import com.gabrielferreira.projeto.service.AulaService;

@RestController
@RequestMapping(value = "/aulas")
public class AulaController {

	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Aula> cadastrarAula(@Valid @RequestBody AulaInserirDTO aulaInserirDTO){
		Aula aula = aulaService.fromDto(aulaInserirDTO);
		aula = aulaService.inserir(aula);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(aula.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<AulaDTO> buscarPorIdAula(@PathVariable Long id){
		Aula aula = aulaService.buscarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(aula));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAula(@PathVariable Long id) {
		aulaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
		
	public AulaDTO paraVisualizacaoDto(Aula aula) {
		return modelMapper.map(aula,AulaDTO.class);
	}
	
}
