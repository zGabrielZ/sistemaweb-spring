package com.gabrielferreira.projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class InMemorySecurityConfig {
	
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.inMemoryAuthentication()
		.withUser("admin").password("{noop}admin").roles("PG_PROJETOS","PG_PROJETOS_ALUNO","PG_PROJETOS_PROFESSOR");
	}
	
}
