package com.gabrielferreira.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.security.UsuarioSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private PessoaService pessoaService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Pessoa pessoa = pessoaService.buscarPorEmail(email);
		return new UsuarioSS(pessoa.getId(),pessoa.getEmail(),pessoa.getSenha(),pessoa.getPerfil());
	}

}
