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
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.EnderecoAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.EnderecoDTO;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.EnderecoService;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Aluno> cadastrarAluno(@Valid @RequestBody AlunoInserirDTO alunoInserirDTO){
		Pessoa pessoa = alunoService.fromDto(alunoInserirDTO);
		pessoa = alunoService.inserir(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> listagemAluno(){
		List<Aluno> alunos = alunoService.listagem();
		return ResponseEntity.ok().body(paraListaDto(alunos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AlunoDTO> buscarPorIdAluno(@PathVariable Long id){
		Aluno aluno = alunoService.buscarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(aluno));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
		alunoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alterarAluno(
			@Valid @RequestBody AlunoAlterarDTO alunoDTO,
			@PathVariable Long id) {
		Pessoa pessoa = alunoService.fromDto(alunoDTO);
		alunoService.atualizar(id, pessoa);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idAluno}/enderecos")
	public ResponseEntity<EnderecoDTO> listagemDeEndereco(@PathVariable Long idAluno){
		Aluno aluno = alunoService.buscarPorId(idAluno);
		return ResponseEntity.ok().body(paraVisualizacaoDto(aluno.getEndereco()));
	}
	
	@GetMapping("/{idAluno}/enderecos/{idEndereco}")
	public ResponseEntity<EnderecoDTO> consultarIdDoEndereco(@PathVariable Long idAluno,@PathVariable Long idEndereco){
		Endereco endereco = enderecoService.consultarPorId(idEndereco, idAluno);
		return ResponseEntity.ok().body(paraVisualizacaoDto(endereco));
	}
	
	@PutMapping("/{idAluno}/enderecos/{idEndereco}")
	public ResponseEntity<Endereco> alterarEnderco(
			@Valid @RequestBody EnderecoAlterarDTO enderecoDTO,
			@PathVariable Long idAluno,@PathVariable Long idEndereco) {
		Endereco endereco = enderecoService.consultarPorId(idEndereco, idAluno);
		endereco = paraAtualizarEndereco(enderecoDTO);
		enderecoService.atualizar(idEndereco, endereco, idAluno);
		return ResponseEntity.noContent().build();
	}
	
	private Endereco paraAtualizarEndereco(EnderecoAlterarDTO dto) {
		return modelMapper.map(dto,Endereco.class);
	}
	
	public AlunoDTO paraVisualizacaoDto(Aluno aluno) {
		return modelMapper.map(aluno,AlunoDTO.class);
	}
	
	public EnderecoDTO paraVisualizacaoDto(Endereco endereco) {
		return modelMapper.map(endereco,EnderecoDTO.class);
	}
	
	private List<AlunoDTO> paraListaDto(List<Aluno> alunos) {
		return alunos.stream()
				.map(aluno -> paraVisualizacaoDto(aluno))
				.collect(Collectors.toList());
	}
	
}
