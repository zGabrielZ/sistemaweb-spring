package com.gabrielferreira.projeto.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.exception.PessoaException;
import com.gabrielferreira.projeto.modelo.Aluno;
import com.gabrielferreira.projeto.modelo.Cidade;
import com.gabrielferreira.projeto.modelo.Curso;
import com.gabrielferreira.projeto.modelo.Endereco;
import com.gabrielferreira.projeto.modelo.EnderecoPessoa;
import com.gabrielferreira.projeto.modelo.Estado;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.modelo.to.AlunoTo;
import com.gabrielferreira.projeto.modelo.to.ProfessorTo;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Transactional
	public Pessoa cadastrarAluno(AlunoTo alunoTo) {
		Aluno aluno = getAluno(alunoTo);
		validarDataNascimento(alunoTo.getDataNascimento());
		validarCpfExistente(alunoTo.getCpf());
		return pessoaRepositorio.save(aluno);
	}
	
	public Pessoa cadastrarProfessor(ProfessorTo professorTo) {
		return null;
	}
	
	private void validarCpfExistente(String cpf) {
		String cpfBuscado = pessoaRepositorio.getCpf(cpf);
		if(cpfBuscado != null) {
			throw new PessoaException("CPF existente.");
		}
	}
	
	private void validarDataNascimento(LocalDate dataNascimento) {
		if(dataNascimento.isAfter(LocalDate.now())) {
			throw new PessoaException("Data de nascimento não pode ser maior que a data atual.");
		}
	}
	
	private Aluno getAluno(AlunoTo alunoTo) {
		Aluno aluno = new Aluno();
		aluno.setId(alunoTo.getId());
		aluno.setNome(alunoTo.getNome());
		aluno.setSobrenome(alunoTo.getSobrenome());
		aluno.setCpf(alunoTo.getCpf());
		aluno.setDataNascimento(alunoTo.getDataNascimento());
		aluno.setSexo(getSexo(alunoTo.getSexo()));
		aluno.setRa(alunoTo.getRa());
		
		Optional<Curso> curso = cursoRepositorio.findById(alunoTo.getIdCurso());
		if(!curso.isPresent()) {
			throw new PessoaException("Curso não encontrado.");
		}
		aluno.setCurso(curso.get());
		
		Endereco endereco = new Endereco();
		endereco.setLogradouro(alunoTo.getLogradouro());
		endereco.setCep(alunoTo.getCep());
		endereco.setComplemento(alunoTo.getComplemento());
		
		EnderecoPessoa enderecoPessoa = new EnderecoPessoa();
		enderecoPessoa.setNumero(alunoTo.getNumero());
		
		endereco.setNumero(enderecoPessoa);
		endereco.setBairro(alunoTo.getBairro());
		
		aluno.setEndereco(endereco);	
		
		Optional<Cidade> cidade = cidadeRepositorio.findById(alunoTo.getIdCidade());
		if(!cidade.isPresent()) {
			throw new PessoaException("Cidade não encontrado.");
		}
		
		Optional<Estado> estado = estadoRepositorio.findById(alunoTo.getIdEstado());
		if(!estado.isPresent()) {
			throw new PessoaException("Estado não encontrado.");
		}
		
		cidade.get().setEstado(estado.get());
		endereco.setCidade(cidade.get());
		
		return aluno;
	}
	
	private Sexo getSexo(Integer idSexo) {
		
		Sexo sexo = null;
		
		switch (idSexo) {
		case 1:
			sexo = Sexo.MASCULINO;
			break;
		case 2:
			sexo = Sexo.FEMININO;
			break;
		default:
			throw new PessoaException("Informe o sexo correto.");
		}
		
		return sexo;
	}
}
