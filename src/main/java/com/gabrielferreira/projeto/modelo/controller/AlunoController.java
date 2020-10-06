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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoInserirDTO;
import com.gabrielferreira.projeto.service.AlunoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Aluno> cadastrar(@Valid @RequestBody AlunoInserirDTO alunoInserirDTO){
		Pessoa pessoa = alunoService.fromDto(alunoInserirDTO);
		pessoa = alunoService.inserir(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listagem(){
		List<Aluno> alunos = alunoService.listagem();
		return ResponseEntity.ok().body(paraListaDto(alunos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable Long id){
		Aluno aluno = alunoService.buscarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(aluno));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		alunoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alterarQuantidade(
			@Valid @RequestBody AlunoAlterarDTO alunoDTO,
			@PathVariable Long id) {
		Pessoa pessoa = alunoService.fromDto(alunoDTO);
		alunoService.atualizar(id, pessoa);
		return ResponseEntity.noContent().build();
	}
	
	public AlunoDTO paraVisualizacaoDto(Aluno aluno) {
		return modelMapper.map(aluno,AlunoDTO.class);
	}
	
	private List<AlunoDTO> paraListaDto(List<Aluno> alunos) {
		return alunos.stream()
				.map(aluno -> paraVisualizacaoDto(aluno))
				.collect(Collectors.toList());
	}
	
}
