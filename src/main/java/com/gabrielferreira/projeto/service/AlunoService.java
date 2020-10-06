package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Cidade;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.AlunoInserirDTO;
import com.gabrielferreira.projeto.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
		
	public Pessoa inserir(Pessoa pessoa) {
		cidadeRepositorio.save(pessoa.getEndereco().getCidade());
		pessoaRepositorio.save(pessoa);
		enderecoRepositorio.save(pessoa.getEndereco());
		return pessoa;
	}
	
	public List<Aluno> listagem(){
		return alunoRepositorio.findAll();
	}
	
	public Aluno buscarPorId(Long id) {
		Optional<Aluno> aluno = alunoRepositorio.findById(id);
		if(!aluno.isPresent()) {
			throw new EntidadeNotFoundException("Aluno não encontrado");
		}
		
		return aluno.get();
	}
	
	public void deletar(Long id) {
		try {
			alunoRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Aluno não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com outra entidade");
		}
	}
	
	public Pessoa atualizar(Long idAluno, Pessoa pessoa) {
		try {
			Aluno entidade = alunoRepositorio.getOne(idAluno);
			updateData(entidade,pessoa);
			return alunoRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Aluno não encontrado");
		}
	}

	private void updateData(Pessoa entidade, Pessoa aluno) {
		entidade.setNomeCompleto(aluno.getNomeCompleto());
		Aluno aluno2 = (Aluno) entidade;
		Aluno aluno3 = (Aluno) aluno;
		aluno2.setRa(aluno3.getRa());
		aluno2.setEmail(aluno3.getEmail());
		entidade.setSexo(aluno.getSexo());
	}
	
	public Pessoa fromDto(AlunoInserirDTO alunoDTO) {
		Pessoa pessoa = new Aluno(null,alunoDTO.getNomeCompleto(),alunoDTO.getCpf(),alunoDTO.getSexo(),
				alunoDTO.getRa(),alunoDTO.getEmail(),alunoDTO.getSenha());
		
		Endereco endereco = new Endereco(null,alunoDTO.getEndereco().getLogradouro(),alunoDTO.getEndereco().getNumero(),
				alunoDTO.getEndereco().getBairro(),alunoDTO.getEndereco().getCep());
		
		pessoa.setEndereco(endereco);
		endereco.setPessoa(pessoa);
		
		Cidade cidade = new Cidade(null,alunoDTO.getEndereco().getCidade().getCidade(),
				alunoDTO.getEndereco().getCidade().getEstado());
		
		endereco.setCidade(cidade);
		
		return pessoa;
	}
	
	public Pessoa fromDto(AlunoAlterarDTO alunoDTO) {
		Pessoa pessoa = new Aluno(null,alunoDTO.getNomeCompleto(),null,alunoDTO.getSexo(),alunoDTO.getRa(),alunoDTO.getEmail(), null);
		return pessoa;
	}
}
