package com.gabrielferreira.projeto.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.
			authorizeRequests()
				.antMatchers("/index").hasAnyRole("PG_PROJETOS")
				.antMatchers("/cadastroaluno").hasAnyRole("PG_PROJETOS_ALUNO")
				.antMatchers("/cadastroprofessor").hasAnyRole("PG_PROJETOS_PROFESSOR")
				.anyRequest()
					.authenticated()
						.and()
							.formLogin()
								.loginPage("/entrar")
									.permitAll()
										.and()
											.logout()
												.logoutSuccessUrl("/entrar?logout")
													.permitAll();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/materialize/**", "/js/**","/imagens/**","/css/**");
	}
}
