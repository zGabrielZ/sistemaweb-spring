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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.dto.CursoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.CursoInserirDTO;
import com.gabrielferreira.projeto.service.CursoService;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Curso> cadastrarCurso(@Valid @RequestBody CursoInserirDTO cursoInserirDTO){
		Curso curso = paraInserirDto(cursoInserirDTO);
		curso = cursoService.inserir(curso);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<CursoDTO>> listagemCurso(){
		List<Curso> cursos = cursoService.listagem();
		return ResponseEntity.ok().body(paraListaDto(cursos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CursoDTO> buscarPorIdCurso(@PathVariable Long id){
		Curso curso = cursoService.consultarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(curso));
	}
	
	public Curso paraInserirDto(CursoInserirDTO inserirDTO) {
		return modelMapper.map(inserirDTO,Curso.class);
	}
	
	public CursoDTO paraVisualizacaoDto(Curso curso) {
		return modelMapper.map(curso,CursoDTO.class);
	}
	
	private List<CursoDTO> paraListaDto(List<Curso> cursos) {
		return cursos.stream()
				.map(curso -> paraVisualizacaoDto(curso))
				.collect(Collectors.toList());
	}	
}
