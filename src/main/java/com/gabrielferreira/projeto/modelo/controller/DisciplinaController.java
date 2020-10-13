package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.dto.DisciplinaDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.DisciplinaInserirDTO;
import com.gabrielferreira.projeto.service.DisciplinaService;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaController {

	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Disciplina> cadastrarDisciplina(@Valid @RequestBody DisciplinaInserirDTO disciplinaInserirDTO){
		Disciplina disciplina = paraInserirDto(disciplinaInserirDTO);
		disciplina = disciplinaService.inserir(disciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(disciplina.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<DisciplinaDTO>> listagemDisciplinas(){
		List<Disciplina> disciplinas = disciplinaService.listagem();
		return ResponseEntity.ok().body(paraListaDto(disciplinas));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> buscarPorIdDisciplina(@PathVariable Long id){
		Disciplina disciplina = disciplinaService.consultarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(disciplina));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Disciplina> alterarDisciplina(@Valid @RequestBody DisciplinaInserirDTO alterarDTO,
			@PathVariable Long id) {
		Disciplina disciplina = paraInserirDto(alterarDTO);
		disciplinaService.atualizar(id, disciplina);
		return ResponseEntity.noContent().build();
	}
	
	public Disciplina paraInserirDto(DisciplinaInserirDTO inserirDTO) {
		return modelMapper.map(inserirDTO,Disciplina.class);
	}
	
	public DisciplinaDTO paraVisualizacaoDto(Disciplina disciplina) {
		return modelMapper.map(disciplina,DisciplinaDTO.class);
	}
	
	private List<DisciplinaDTO> paraListaDto(List<Disciplina> disciplinas) {
		return disciplinas.stream()
				.map(disciplina -> paraVisualizacaoDto(disciplina))
				.collect(Collectors.toList());
	}	
}
