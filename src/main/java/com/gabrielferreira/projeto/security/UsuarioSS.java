package com.gabrielferreira.projeto.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gabrielferreira.projeto.modelo.entidade.enums.Perfil;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UsuarioSS implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorithies;
	
	public UsuarioSS(Long id, String email, String senha, Set<Perfil> perfis) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorithies = perfis.stream().map(x->new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorithies;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
