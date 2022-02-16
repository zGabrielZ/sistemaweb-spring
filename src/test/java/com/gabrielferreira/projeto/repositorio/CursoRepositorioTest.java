package com.gabrielferreira.projeto.repositorio;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.gabrielferreira.projeto.modelo.Curso;

@AutoConfigureTestDatabase(replace = Replace.NONE) // Não desconfigure o banco de dados de teste, nao vai sobrescrever minhas configurações
@DataJpaTest // -> Cria uma instancia de banco em memoria e depois que finaliza o teste, é encerrado o banco de dados
@ActiveProfiles("test")
public class CursoRepositorioTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	private Curso curso;
	
	private Curso curso2;
	
	@BeforeEach
	public void criarInstancias() {
		curso = Curso.builder().id(null).nome("Engenharia da Computação").build();
		curso2 = Curso.builder().id(null).nome("Engenharia de Software").build();
	}
	
	@Test
	public void deveBuscarNomeCurso() {
		entityManager.persist(curso);
		
		Curso cursoPesquisado = cursoRepositorio.findByNomeCurso(curso.getNome());
		assertNotNull(cursoPesquisado);
	}
	
	@Test
	public void deveBuscarNomeCursoQuandoForAtualizar() {
		entityManager.persist(curso);
		entityManager.persist(curso2);
		
		// Voce quer atualizar o curso2, porém quer colocar o nome igual ao curso1 (Engenharia da Computação)
		curso2.setNome("Engenharia da Computação");
		
		Curso cursoPesquisado = cursoRepositorio.findByNomeCursoAtualizado(curso2.getNome(), curso2.getId());
		assertNotNull(cursoPesquisado);
		
		
	}
	
}
