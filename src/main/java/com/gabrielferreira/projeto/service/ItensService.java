package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Itens;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.repositorio.ItensRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.RecursoNotFoundException;

@Service
public class ItensService {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private ItensRepositorio itensRepositorio;
	
	public Itens consultarPorId(Integer id) {
		Optional<Itens> itens = itensRepositorio.findById(id);
		return itens.orElseThrow(() -> new RecursoNotFoundException(id));
	}
	
	public List<Itens> consultarTodos(){
		return itensRepositorio.findAll();
	}
	
	public Itens inserir(Itens itens) {
		itens.setId(null);
		pessoaRepositorio.save(itens.getPessoa());
		disciplinaRepositorio.save(itens.getDisciplina());
		return itensRepositorio.save(itens);
	}
	
	public void deletar(Integer id) {
		itensRepositorio.deleteById(id);
	}
	
	public List<Itens> buscarNome(String nome){
		return itensRepositorio.getItens(nome);
	}

}
