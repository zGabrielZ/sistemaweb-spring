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
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.modelo.entidade.dto.EnderecoAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.EnderecoDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.ProfessorAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.ProfessorDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.ProfessorInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.TelefoneDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.TelefoneInserirDTO;
import com.gabrielferreira.projeto.service.EnderecoService;
import com.gabrielferreira.projeto.service.ProfessorService;
import com.gabrielferreira.projeto.service.TelefoneService;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<Professor> cadastrarProfessor(@Valid @RequestBody ProfessorInserirDTO professorInserirDTO){
		Pessoa pessoa = professorService.fromDto(professorInserirDTO);
		pessoa = professorService.inserir(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> listagemProfessor(){
		List<Professor> professores = professorService.listagem();
		return ResponseEntity.ok().body(paraListaDto(professores));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProfessorDTO> buscarPorIdProfessor(@PathVariable Long id){
		Professor professor = professorService.buscarPorId(id);
		return ResponseEntity.ok().body(paraVisualizacaoDto(professor));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
		professorService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> alterarProfessor(
			@Valid @RequestBody ProfessorAlterarDTO professorDTO,
			@PathVariable Long id) {
		Pessoa pessoa = professorService.fromDto(professorDTO);
		professorService.atualizar(id, pessoa);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idProfessor}/enderecos")
	public ResponseEntity<EnderecoDTO> listagemDeEndereco(@PathVariable Long idProfessor){
		Professor professor = professorService.buscarPorId(idProfessor);
		return ResponseEntity.ok().body(paraVisualizacaoDto(professor.getEndereco()));
	}
	
	@GetMapping("/{idProfessor}/enderecos/{idEndereco}")
	public ResponseEntity<EnderecoDTO> consultarIdDoEndereco(@PathVariable Long idProfessor,@PathVariable Long idEndereco){
		Endereco endereco = enderecoService.consultarPorId(idEndereco, idProfessor);
		return ResponseEntity.ok().body(paraVisualizacaoDto(endereco));
	}
	
	@PutMapping("/{idProfessor}/enderecos/{idEndereco}")
	public ResponseEntity<Endereco> alterarEndereco(
			@Valid @RequestBody EnderecoAlterarDTO enderecoDTO,
			@PathVariable Long idProfessor,@PathVariable Long idEndereco) {
		Endereco endereco = enderecoService.consultarPorId(idEndereco, idProfessor);
		endereco = enderecoService.fromDto(enderecoDTO);
		enderecoService.atualizar(idEndereco, endereco, idProfessor);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{idProfessor}/telefones")
	public ResponseEntity<List<TelefoneDTO>> listagemDeTelefones(@PathVariable Long idProfessor){
		Professor professor = professorService.buscarPorId(idProfessor);
		return ResponseEntity.ok().body(paraListaDtoTelefone(professor.getTelefones()));
	}
	
	@GetMapping("/{idProfessor}/telefones/{idTelefone}")
	public ResponseEntity<TelefoneDTO> consultarIdDoTelefone(@PathVariable Long idProfessor,@PathVariable Long idTelefone){
		Telefone telefone = telefoneService.consultarPorId(idTelefone, idProfessor);
		return ResponseEntity.ok().body(paraVisualizacaoDto(telefone));
	}
	
	@PostMapping("/{idProfessor}/telefones")
	public ResponseEntity<Telefone> inserir(@Valid @RequestBody TelefoneInserirDTO telefoneInserirDTO,@PathVariable Long idProfessor) {
		Telefone telefone = telefoneService.fromDto(telefoneInserirDTO);
		telefone = telefoneService.inserir(telefone,idProfessor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(telefone.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{idProfessor}/telefones/{idTelefone}")
	public ResponseEntity<Endereco> alterarTelefone(
			@Valid @RequestBody TelefoneInserirDTO telefoneDTO,
			@PathVariable Long idProfessor,@PathVariable Long idTelefone) {
		Telefone telefone = telefoneService.consultarPorId(idTelefone, idProfessor);
		telefone = telefoneService.fromDto(telefoneDTO);
		telefoneService.atualizar(idTelefone, telefone, idProfessor);
		return ResponseEntity.noContent().build();
	}
	
	public ProfessorDTO paraVisualizacaoDto(Professor professor) {
		return modelMapper.map(professor,ProfessorDTO.class);
	}
	
	public EnderecoDTO paraVisualizacaoDto(Endereco endereco) {
		return modelMapper.map(endereco,EnderecoDTO.class);
	}
	
	public TelefoneDTO paraVisualizacaoDto(Telefone telefone) {
		return modelMapper.map(telefone,TelefoneDTO.class);
	}
	
	private List<ProfessorDTO> paraListaDto(List<Professor> professores) {
		return professores.stream()
				.map(professor -> paraVisualizacaoDto(professor))
				.collect(Collectors.toList());
	}
	
	private List<TelefoneDTO> paraListaDtoTelefone(List<Telefone> telefones) {
		return telefones.stream()
				.map(telefone -> paraVisualizacaoDto(telefone))
				.collect(Collectors.toList());
	}
	
	public Telefone paraInserirDto(TelefoneInserirDTO inserirDTO) {
		return modelMapper.map(inserirDTO,Telefone.class);
	}
	
}
