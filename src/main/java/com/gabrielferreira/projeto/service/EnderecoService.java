package com.gabrielferreira.projeto.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	public Endereco consultarPorId(Long idEndereco,Long idPessoa) {
		Pessoa pessoa = pessoaRepositorio.findById(idPessoa)
				.orElseThrow(()-> new EntidadeNotFoundException("Pessoa não encontrada"));
		
		Optional<Endereco> endereco = enderecoRepositorio.verificarEnderecoPessoa(pessoa.getId(), idEndereco);
		if(!endereco.isPresent()) {
			throw new EntidadeNotFoundException("Endereço não encontrado");
		}
		
		return endereco.get();
	}
	
	public Endereco atualizar(Long idEndereco,Endereco endereco,Long idPessoa) {
		try {
			Endereco entidade = enderecoRepositorio.getOne(idEndereco);
			updateData(entidade,endereco);			
			return enderecoRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Endereço não encontrado");
		}
	}

	private void updateData(Endereco entidade, Endereco endereco) {
		entidade.setLogradouro(endereco.getLogradouro());
		entidade.setNumero(endereco.getNumero());
		entidade.setBairro(endereco.getBairro());
		entidade.setCep(endereco.getCep());
		entidade.getCidade().setCidade(endereco.getCidade().getCidade());
		entidade.getCidade().setEstado(endereco.getCidade().getEstado());
	}

}

