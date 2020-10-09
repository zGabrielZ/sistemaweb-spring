package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielferreira.projeto.modelo.entidade.Turma;
import com.gabrielferreira.projeto.modelo.entidade.dto.TurmaDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.TurmaInserirDTO;
import com.gabrielferreira.projeto.service.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Turma> cadastrarTurma(@Valid @RequestBody TurmaInserirDTO turmaInserirDTO){
		Turma turma = turmaService.fromDto(turmaInserirDTO);
		turma = turmaService.inserir(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(turma.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<TurmaDTO>> listagemTurma(){
		List<Turma> turmas = turmaService.listagem();
		return ResponseEntity.ok().body(paraListaDto(turmas));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TurmaDTO> buscarPorIdTurma(@PathVariable Long id){
		Turma turma = turmaService.buscarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(turma));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarTurma(@PathVariable Long id) {
		turmaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
	public TurmaDTO paraVisualizacaoDto(Turma turma) {
		return modelMapper.map(turma,TurmaDTO.class);
	}
	
	private List<TurmaDTO> paraListaDto(List<Turma> turmas) {
		return turmas.stream()
				.map(turma -> paraVisualizacaoDto(turma))
				.collect(Collectors.toList());
	}	
}
