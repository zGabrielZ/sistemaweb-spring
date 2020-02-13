package com.gabrielferreira.projeto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public boolean buscarCpfPessoa(Pessoa pessoa) {
		return pessoaRepositorio.findPessoaByCpf(pessoa.getCpf()) != null;
	}
	
	public boolean buscarCpfAtualizado(Pessoa curso) {
		return pessoaRepositorio.findPessoaByCpfAtualizado(
				curso.getCpf(),curso.getId()) 
				!=null;
	}
	
	public List<Pessoa> consultarTodos(){
		return pessoaRepositorio.findAll();
	}
}
