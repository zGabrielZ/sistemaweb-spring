package com.gabrielferreira.projeto.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.Cidade;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Usuario;
import com.gabrielferreira.projeto.modelo.entidade.dto.UsuarioInserirDTO;
import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.repositorio.UsuarioRepositorio;
import com.gabrielferreira.projeto.service.exceptions.EntidadeNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
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
		
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepositorio.findById(id);
		if(!usuario.isPresent()) {
			throw new EntidadeNotFoundException("Usuário não encontrado");
		}
		
		return usuario.get();
	}
	
	public Pessoa fromDto(UsuarioInserirDTO usuarioDTO) {
		
		Pessoa pessoa = new Usuario(null,usuarioDTO.getNomeCompleto(),usuarioDTO.getCpf(),
				Sexo.converterParaEnum(usuarioDTO.getSexo()),usuarioDTO.getEmail(),bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));
		
		Endereco endereco = new Endereco(null,usuarioDTO.getEndereco().getLogradouro(),usuarioDTO.getEndereco().getNumero(),
				usuarioDTO.getEndereco().getBairro(),usuarioDTO.getEndereco().getCep());
		
		pessoa.setEndereco(endereco);
		endereco.setPessoa(pessoa);
		
		Cidade cidade = new Cidade(null,usuarioDTO.getEndereco().getCidade().getCidade(),
				Estado.converterParaEnum(usuarioDTO.getEndereco().getCidade().getEstado()));
		
		endereco.setCidade(cidade);
		
		return pessoa;
	}
	
}
