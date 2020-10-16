package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Curso> cadastrarCurso(@Valid @RequestBody CursoInserirDTO cursoInserirDTO){
		Curso curso = paraInserirDto(cursoInserirDTO);
		curso = cursoService.inserir(curso);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','ALUNO','PROFESSOR')")
	@GetMapping
	public ResponseEntity<List<CursoDTO>> listagemCurso(){
		List<Curso> cursos = cursoService.listagem();
		return ResponseEntity.ok().body(paraListaDto(cursos));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','ALUNO','PROFESSOR')")
	@GetMapping("/paginacao")
	public ResponseEntity<Page<CursoDTO>> pageCurso(
			@RequestParam(value = "pagina",defaultValue = "0")Integer pagina,
			@RequestParam(value = "linhasPorPagina",defaultValue = "24")Integer linhasPorPagina,
			@RequestParam(value = "ordernarPor",defaultValue = "nomeCurso") String ordernarPor,
			@RequestParam(value = "direcao",defaultValue = "ASC") String direcao,
			@RequestParam(value = "nome",defaultValue = "") String nome){
		Page<Curso> cursos = cursoService.buscarPagina(pagina, linhasPorPagina, ordernarPor, direcao, nome);
		return ResponseEntity.ok().body(paraPageDto(cursos));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','ALUNO','PROFESSOR')")
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
	
	private Page<CursoDTO> paraPageDto(Page<Curso> cursos) {
		return cursos.map(curso -> paraVisualizacaoDto(curso));
	}
}
