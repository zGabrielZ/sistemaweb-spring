package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public List<Pessoa> consultarTodos(){
		return pessoaRepositorio.findAll();
	}
	
	public Pessoa consultarPorId(Integer id) {
		Optional<Pessoa> pessoa = pessoaRepositorio.findById(id);
		return pessoa.orElseThrow(()-> new RecursoNotFoundException(id));
	}
	
	public boolean buscarCpf(Pessoa pessoa) {
		return pessoaRepositorio.findPessoaByCpf(pessoa.getCpf()) != null;
	}
	
	public boolean buscarCpfAtualizado(Pessoa pessoa) {
		return pessoaRepositorio.findPessoaByCpfAtualizado(pessoa.getCpf(),pessoa.getId()) 
				!=null;
	}
}
