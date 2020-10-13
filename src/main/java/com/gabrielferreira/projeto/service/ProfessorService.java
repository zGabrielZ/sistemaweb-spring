package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Cidade;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Graduacao;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.dto.ProfessorAlterarDTO;
import com.gabrielferreira.projeto.modelo.entidade.dto.ProfessorInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.GraduacaoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.repositorio.ProfessorRepositorio;
import com.gabrielferreira.projeto.service.exceptions.DatabaseException;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepositorio professorRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private GraduacaoRepositorio graduacaoRepositorio;
		
	public Pessoa inserir(Pessoa pessoa) {
		cidadeRepositorio.save(pessoa.getEndereco().getCidade());
		pessoaRepositorio.save(pessoa);
		enderecoRepositorio.save(pessoa.getEndereco());
		return pessoa;
	}
	
	public List<Professor> listagem(){
		return professorRepositorio.findAll();
	}
	
	public Professor buscarPorId(Long id) {
		Optional<Professor> professor = professorRepositorio.findById(id);
		if(!professor.isPresent()) {
			throw new EntidadeNotFoundException("Professor não encontrado");
		}
		
		return professor.get();
	}
	
	public void deletar(Long id) {
		try {
			professorRepositorio.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNotFoundException("Professor não encontrado");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possivel deletar, pois está relacionada com outra entidade");
		}
	}
	
	public Pessoa atualizar(Long idProfessor, Pessoa pessoa) {
		try {
			Professor entidade = professorRepositorio.getOne(idProfessor);
			updateData(entidade,pessoa);
			return professorRepositorio.save(entidade);
		} catch (EntityNotFoundException e) {
			throw new EntidadeNotFoundException("Professor não encontrado");
		}
	}

	private void updateData(Pessoa entidade, Pessoa professor) {
		entidade.setNomeCompleto(professor.getNomeCompleto());
		Professor professor2 = (Professor) entidade;
		Professor professor3 = (Professor) professor;
		professor2.setQtdHoras(professor3.getQtdHoras());
		entidade.setSexo(professor.getSexo());
	}
	
	public Page<Professor> buscarPagina(Integer pagina,Integer linhasPorPagina,String ordernarPor,String direcao,String nome){
		PageRequest pageRequest = PageRequest.of(pagina,linhasPorPagina,Direction.valueOf(direcao),ordernarPor);
		return professorRepositorio.filtrar(nome,pageRequest);
	}
	
	public Pessoa fromDto(ProfessorInserirDTO professorDTO) {
		
		Pessoa pessoa = new Professor(null, professorDTO.getNomeCompleto(),professorDTO.getCpf(),
				Sexo.converterParaEnum(professorDTO.getSexo()),professorDTO.getAnoAdmissao(),professorDTO.getQtdHoras());
		
		Graduacao graduacao = new Graduacao(null, professorDTO.getGraduacao().getNomeGraduacao());
		
		Professor professor2 = (Professor) pessoa;
		
		professor2.setGraduacao(graduacao);
		graduacao.getProfessores().add(professor2);
		
		graduacaoRepositorio.save(professor2.getGraduacao());
			
		Endereco endereco = new Endereco(null,professorDTO.getEndereco().getLogradouro(),professorDTO.getEndereco().getNumero(),
				professorDTO.getEndereco().getBairro(),professorDTO.getEndereco().getCep());
		
		pessoa.setEndereco(endereco);
		endereco.setPessoa(pessoa);
		
		Cidade cidade = new Cidade(null,professorDTO.getEndereco().getCidade().getCidade(),
				Estado.converterParaEnum(professorDTO.getEndereco().getCidade().getEstado()));
		
		endereco.setCidade(cidade);
		
		return pessoa;
	}
	
	public Pessoa fromDto(ProfessorAlterarDTO professorDTO) {
		Pessoa pessoa = new Professor(null,professorDTO.getNomeCompleto(),null,Sexo.converterParaEnum(professorDTO.getSexo()),null,professorDTO.getQtdHoras());
		return pessoa;
	}
}
