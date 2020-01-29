package com.gabrielferreira.projeto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.gabrielferreira.projeto.*"})
public class ProjetoSpringHerancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringHerancaApplication.class, args);
	}

}
