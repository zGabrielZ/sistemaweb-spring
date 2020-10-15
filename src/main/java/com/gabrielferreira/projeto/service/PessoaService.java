package com.gabrielferreira.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public Pessoa buscarPorEmail(String email) {
		Pessoa pessoa = pessoaRepositorio.findByEmail(email);
		if(pessoa == null) {
			throw new EntidadeNotFoundException("Email da pessoa não encontrada");
		}
		
		return pessoa;
	}	
}
