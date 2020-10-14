package com.gabrielferreira.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Usuario;
import com.gabrielferreira.projeto.security.UsuarioSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioService.buscarPorEmail(email);
		return new UsuarioSS(usuario.getId(),usuario.getEmail(),usuario.getSenha(),usuario.getPerfil());
	}

}
