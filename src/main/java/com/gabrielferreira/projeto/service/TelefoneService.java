package com.gabrielferreira.projeto.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.repositorio.TelefoneRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepositorio telefoneRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public Telefone consultarPorId(Long idTelefone,Long idPessoa) {
		
		Pessoa pessoa = pessoaRepositorio.findById(idPessoa)
				.orElseThrow(()-> new EntidadeNotFoundException("Pessoa não encontrada"));
		
		Optional<Telefone> telefone = telefoneRepositorio.verificarTelefonePessoa(pessoa.getId(), idTelefone);
		if(!telefone.isPresent()) {
			throw new EntidadeNotFoundException("Telefone não encontrado");
		}
		
		return telefone.get();
	}
	
	public Telefone inserir(Telefone telefone, Long idPessoa) {
		
		Pessoa pessoa = pessoaRepositorio.findById(idPessoa)
				.orElseThrow(()-> new EntidadeNotFoundException("Pessoa não encontrada"));
		
		telefone.setPessoa(pessoa);
		pessoa.getTelefones().add(telefone);
		
		return telefoneRepositorio.save(telefone);
	}
	
	public Telefone atualizar(Long idTelefone,Telefone telefone,Long idPessoa) {
		try {
			Telefone entidade = telefoneRepositorio.getOne(idTelefone);
			updateData(entidade,telefone);			
			return telefoneRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Telefone não encontrado");
		}
	}

	private void updateData(Telefone entidade, Telefone telefone) {
		entidade.setNomeContato(telefone.getNomeContato());
		entidade.setNumero(telefone.getNumero());
		entidade.setTipoTelefone(telefone.getTipoTelefone());
	}

}

