package com.gabrielferreira.projeto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.gabrielferreira.projeto.builder.PessoaBuilder;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.Curso;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.modelo.to.CursoTo;
import com.gabrielferreira.projeto.modelo.to.consulta.ConsultaPessoaTo;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;

@SpringBootTest
public class CursoServiceTest {

	private CursoService cursoService;
	private CursoRepositorio cursoRepositorio;
	private PessoaRepositorio pessoaRepositorio;
	
	@BeforeEach
	public void criarInstancias() {
		cursoRepositorio = Mockito.mock(CursoRepositorio.class);
		
		pessoaRepositorio = Mockito.mock(PessoaRepositorio.class);
		
		cursoService = new CursoService(cursoRepositorio, pessoaRepositorio);
	}
	
	@Test
	public void deveInserirCurso() {
		
		// Criando um curso
		Curso curso = Curso.builder().id(null).nome("Engenharia da Computação").build();
		
		// Passando para o To
		CursoTo cursoTo = CursoTo.builder().id(curso.getId()).nome(curso.getNome()).build();
		
		// Fazendo a execução do método
		cursoService.inserir(cursoTo);
		
		// Verificando se foi salvo
		verify(cursoRepositorio).save(curso);
		
	}
	
	@Test
	public void naoDeveInserirCursoPoisJaExisteNomeCadastrado() {
		// Criando um curso, esse tem que esta salvo no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Criando outro curso, esse é o que vai inserir, não está salvo no banco de dados
		Curso curso2 = Curso.builder().id(null).nome("Engenharia da Computação").build();
		
		// Vai ensinar a retornar o curso do id 1 
		when(cursoRepositorio.findByNomeCurso(curso2.getNome())).thenReturn(curso);
		
		// Passando curso2 para o To
		CursoTo cursoTo = CursoTo.builder().id(curso2.getId()).nome(curso2.getNome()).build();
		
		try {
			// Executando o método 
			cursoService.inserir(cursoTo);
			fail("Deveria lançar exception, pois já existe esse nome cadastrado");
		} catch (RegraException e) {
			assertEquals("Nome ja existente.", e.getMessage());
		}
		
	}
	
	@Test
	public void naoDeveAtualizarCursoPoisJaExisteNomeCadastrado() {
		// Criando um curso, esse curso já estava salvo no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Criando outro curso, esse também já estava salvo no banco de dados, porém trocou só de nome
		Curso curso2 = Curso.builder().id(2).nome("Engenharia da Computação").build();
		
		// Vai ensinar a retornar o primeiro curso 
		when(cursoRepositorio.findByNomeCursoAtualizado(curso2.getNome(), curso2.getId())).thenReturn(curso);
				
		// Passando curso2 para o To
		CursoTo cursoTo = CursoTo.builder().id(curso2.getId()).nome(curso2.getNome()).build();
		
		try {
			// Executando o método 
			cursoService.inserir(cursoTo);
			fail("Deveria lançar exception, pois já existe esse nome cadastrado ao atualizar");
		} catch (RegraException e) {
			assertEquals("Nome ja existente ao atualizar.", e.getMessage());
		}
		
	}
	
	@Test
	public void deveMostrarUmaListaDeCursos() {
		// Criando varios cursos
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		Curso curso2 = Curso.builder().id(2).nome("Engenharia de Softare").build();
		Curso curso3 = Curso.builder().id(3).nome("Engenharia Automação").build();
		Curso curso4 = Curso.builder().id(4).nome("Medicina").build();
	
		// Associando os cursos na lista
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(curso);
		cursos.add(curso2);
		cursos.add(curso3);
		cursos.add(curso4);
		
		// Vai ensinar a retornar a lista 
		when(cursoRepositorio.findAll()).thenReturn(cursos);
		
		List<CursoTo> cursoTos = cursoService.getCursos();
		
		// Verificar 
		assertEquals(4,cursoTos.size());
		verify(cursoRepositorio).findAll();
		
	}
	
	@Test
	public void deveVerificarCursoExistente() {
		// Criando um curso, esse curso já existe no banco de dados 
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Vai ensinar a retornar o curso de cima 
		doReturn(Optional.of(curso)).when(cursoRepositorio).findById(curso.getId());
		
		// Executando o método
		Curso cursoExistente = cursoService.getCurso(curso.getId());
		
		// Verificando 
		verify(cursoRepositorio).findById(cursoExistente.getId());
		
	}
	
	@Test
	public void naoDeveVerificarCursoInexistente() {
		// Criando um curso, esse curso já existe no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Vai ensinar a retornar o curso de cima 
		doReturn(Optional.of(curso)).when(cursoRepositorio).findById(curso.getId());
		
		// Executando o método, porém com o id diferente do de cima e sendo assim vai returnar uma excessão 
		try {
			cursoService.getCurso(2);
			fail("Deveria lançar uma exception, pois esse curso pesquisado não existe.");
		} catch (Exception e) {
			assertEquals("Curso não encontrado.", e.getMessage());
		}
	}
	
	@Test
	public void deveDeletarCursoExistente() {
		// Criando um curso, esse curso já existe no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
				
		// Vai ensinar a retornar o curso de cima 
		doReturn(Optional.of(curso)).when(cursoRepositorio).findById(curso.getId());
		
		// Executando o método 
		cursoService.deletar(curso.getId());
		
		// Verificando 
		verify(cursoRepositorio).deleteById(curso.getId());
	}
	
	@Test
	public void naoDeveDeletarCursoInexistente() {
		// Criando um curso, esse curso já existe no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();

		// Vai ensinar a retornar o curso de cima
		doReturn(Optional.of(curso)).when(cursoRepositorio).findById(curso.getId());

		// Executando o método, porém com o id diferente do de cima e sendo assim vai returnar uma excessão
		try {
			cursoService.deletar(5);
			fail("Deveria lançar uma exception, pois esse curso pesquisado não existe.");
		} catch (Exception e) {
			assertEquals("Curso não encontrado.", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveDeletarCursosExistentePoisTemPessoasRelacionadasHaEsteCurso() {
		// Criando um curso, esse curso já existe no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Criando varias pessoas e associando em uma lista 
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa = PessoaBuilder.umAluno("202020202").comCpf("123").comDataNascimento(LocalDate.now()).comId(1).comNome("José").comSexo(Sexo.MASCULINO).comSobrenome("Marques").constroi();
		Pessoa pessoa2 = PessoaBuilder.umAluno("202020202").comCpf("321").comDataNascimento(LocalDate.now()).comId(2).comNome("Marcos").comSexo(Sexo.MASCULINO).comSobrenome("Marinho").constroi();
	
		pessoas.add(pessoa);
		pessoas.add(pessoa2);
		
		curso.getPessoas().addAll(pessoas);
		
		// Vai ensinar a retornar o curso de cima junto com as pessoas
		doReturn(Optional.of(curso)).when(cursoRepositorio).findById(curso.getId());
		
		//Executando o método 
		try {
			cursoService.deletar(curso.getId());
			fail("Deveria lançar uma exception, pois esse curso tem pessoas relacionadas.");
		} catch (Exception e) {
			assertEquals("Não é possível deletar o curso, pois já existe pessoas relacionadas há este curso.", e.getMessage());
		}
	
	}
	
	@Test
	public void deveBuscarPessoasPorCurso() {
		// Criando um curso, esse curso já existe no banco de dados
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		// Criando varias pessoas e associando em uma lista 
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Pessoa pessoa = PessoaBuilder.umAluno("202020202").comCpf("123").comDataNascimento(LocalDate.now()).comId(1).comNome("José").comSexo(Sexo.MASCULINO).comSobrenome("Marques").constroi();
		Pessoa pessoa2 = PessoaBuilder.umAluno("202020202").comCpf("321").comDataNascimento(LocalDate.now()).comId(2).comNome("Marcos").comSexo(Sexo.MASCULINO).comSobrenome("Marinho").constroi();
		Pessoa pessoa3 = PessoaBuilder.umProfessor(BigDecimal.valueOf(2000.00)).comCpf("444").comDataNascimento(LocalDate.now()).comId(3).comNome("Sofia").comSexo(Sexo.FEMININO).comSobrenome("Texeira").constroi();
		Pessoa pessoa4 = PessoaBuilder.umProfessor(BigDecimal.valueOf(5000.00)).comCpf("356").comDataNascimento(LocalDate.now()).comId(4).comNome("Maria").comSexo(Sexo.FEMININO).comSobrenome("Silva").constroi();
		
		pessoas.add(pessoa);
		pessoas.add(pessoa2);
		pessoas.add(pessoa3);
		pessoas.add(pessoa4);
		
		// Vai ensinar a retorar a lista de pessoas
		when(pessoaRepositorio.getPessoasPorCurso(curso.getId())).thenReturn(pessoas);
		
		// Executando o metodo 
		List<ConsultaPessoaTo> consultaPessoaTos = cursoService.getPessoasPorCurso(curso.getId());
		
		// Verificando 
		assertEquals(4,consultaPessoaTos.size());
		verify(pessoaRepositorio).getPessoasPorCurso(curso.getId());
						
	}
	
}
