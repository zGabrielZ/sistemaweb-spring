package com.gabrielferreira.projeto.modelo.controller;

import java.net.URI;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Usuario;
import com.gabrielferreira.projeto.modelo.entidade.dto.UsuarioInserirDTO;
import com.gabrielferreira.projeto.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody UsuarioInserirDTO usuarioInserirDTO){
		Pessoa pessoa = usuarioService.fromDto(usuarioInserirDTO);
		pessoa = usuarioService.inserir(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
}
