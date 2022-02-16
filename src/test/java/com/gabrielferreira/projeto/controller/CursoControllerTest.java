package com.gabrielferreira.projeto.controller;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.gabrielferreira.projeto.builder.PessoaBuilder;
import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.Curso;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.modelo.to.CursoTo;
import com.gabrielferreira.projeto.modelo.to.consulta.ConsultaPessoaTo;
import com.gabrielferreira.projeto.service.CursoService;

@SpringBootTest
@AutoConfigureMockMvc
public class CursoControllerTest {

	private static String API_CURSO = "/curso";
	private static String PAG_CADASTRO_CURSO = "curso/cadastro-curso";
	private static String PAG_CONSULTA_CURSO = "curso/consulta-curso";
	private static String PAG_CADASTRADAS_PESSOAS = "curso/pessoas-cadastradas";
	private static String PAG_ERRO = "pagina-erro";
	
	@Autowired
	private MockMvc mockMvc;
		
	@MockBean // Quando o contexto subir, não pode injetar o objeto real, so com os objetos falsos
	private CursoService cursoService;

	@Test
	public void deveIrParaPaginaCadastroDoCurso() throws Exception {
		mockMvc.perform(get(API_CURSO + "/cadastro"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name(PAG_CADASTRO_CURSO));
	}
	
	@Test
	public void deveIrParaPaginaConsultaDoCursoComListaDeCursos() throws Exception {
		
		List<CursoTo> cursoTos = new ArrayList<CursoTo>();
		CursoTo curso = CursoTo.builder().id(1).nome("Engenharia da computação").build();
		CursoTo curso2 = CursoTo.builder().id(2).nome("Engenharia de Software").build();
		CursoTo curso3 = CursoTo.builder().id(3).nome("Engenharia Cívil").build();
		CursoTo curso4 = CursoTo.builder().id(4).nome("Engenharia Automação").build();
		
		cursoTos.add(curso);
		cursoTos.add(curso2);
		cursoTos.add(curso3);
		cursoTos.add(curso4);
		
		when(cursoService.getCursos()).thenReturn(cursoTos);
		
		mockMvc.perform(get(API_CURSO + "/consulta"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("cursos"))
			.andExpect(model().attribute("cursos", hasItem(curso)))
			.andExpect(view().name(PAG_CONSULTA_CURSO));
	}
	
	@Test
	public void deveIrParaPaginaConsultaDoCursoSemListaDeCursos() throws Exception {
		List<CursoTo> cursoTos = new ArrayList<CursoTo>();
		when(cursoService.getCursos()).thenReturn(cursoTos);
		
		mockMvc.perform(get(API_CURSO + "/consulta"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attribute("cursos", is(Matchers.empty())))
			.andExpect(model().attribute("msgCursos", equalTo("Nenhum registro encontrado.")))
			.andExpect(view().name(PAG_CONSULTA_CURSO));
		
	}
	
	@Test
	public void deveIrParaPaginaCadastroCursoComDadosObtidos() throws Exception {
		Curso curso = Curso.builder().id(1).nome("Engenharia").build();
		
		when(cursoService.getCurso(1)).thenReturn(curso);
		
		CursoTo cursoTo = CursoTo.builder().id(curso.getId()).nome(curso.getNome()).build();
		
		mockMvc.perform(get(API_CURSO + "/editar-curso/{idCurso}",curso.getId()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("curso"))
			// vai pegar a propriedade nome (atributo) e vai comparar com de lá e com aqui
			.andExpect(model().attribute("curso", Matchers.hasProperty("nome",Matchers.equalTo(cursoTo.getNome()))))
			.andExpect(view().name(PAG_CADASTRO_CURSO));
		
	}
	
	@Test
	public void naoDeveIrParaPaginaCadastroCursoPoisNaoExisteDados() throws Exception {
		when(cursoService.getCurso(any())).thenThrow(new EntidadeException("Erro"));
		
		mockMvc.perform(get(API_CURSO + "/editar-curso/{idCurso}",anyInt()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attribute("erro",not(is(Matchers.empty()))))
			.andExpect(view().name(PAG_ERRO));
		
	}
	
	@Test
	public void deveRedirecionarParaConsultaCursosAposDeletarUmCurso() throws Exception {
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		when(cursoService.getCurso(1)).thenReturn(curso);
		
		mockMvc.perform(get(API_CURSO + "/deletar-curso/{idCurso}",curso.getId()))
			.andDo(print())
			// o Metodo vai redirecionar para /cursos/consulta, por isso o isFound(Status 302)
			.andExpect(status().isFound())
			.andExpect(flash().attribute("msgCursoDelete", equalTo("Curso deletado com sucesso.")))
			.andExpect(redirectedUrl(API_CURSO + "/consulta"));
		
	}
	
	@Test
	public void deveIrParaPaginaCadastroPessoasPoisVaiFazerUmaConsultaPorCursoComListaDePessoas() throws Exception {
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		when(cursoService.getCurso(1)).thenReturn(curso);
		
		List<ConsultaPessoaTo> pessoas = new ArrayList<ConsultaPessoaTo>();
		Pessoa pessoa = PessoaBuilder.umAluno("202020202").comCpf("123").comDataNascimento(LocalDate.now()).comId(1).comNome("José").comSexo(Sexo.MASCULINO).comSobrenome("Marques").constroi();
		Pessoa pessoa2 = PessoaBuilder.umAluno("123321").comCpf("321").comDataNascimento(LocalDate.now()).comId(2).comNome("Marcos").comSexo(Sexo.MASCULINO).comSobrenome("Marinho").constroi();
		Pessoa pessoa3 = PessoaBuilder.umProfessor(BigDecimal.valueOf(2000.00)).comCpf("444").comDataNascimento(LocalDate.now()).comId(3).comNome("Sofia").comSexo(Sexo.FEMININO).comSobrenome("Texeira").constroi();
		Pessoa pessoa4 = PessoaBuilder.umProfessor(BigDecimal.valueOf(5000.00)).comCpf("356").comDataNascimento(LocalDate.now()).comId(4).comNome("Maria").comSexo(Sexo.FEMININO).comSobrenome("Silva").constroi();
		
		ConsultaPessoaTo consultaPessoaTo = new ConsultaPessoaTo(pessoa);
		pessoas.add(consultaPessoaTo);
		pessoas.add(new ConsultaPessoaTo(pessoa2));
		pessoas.add(new ConsultaPessoaTo(pessoa3));
		pessoas.add(new ConsultaPessoaTo(pessoa4));
		
		when(cursoService.getPessoasPorCurso(1)).thenReturn(pessoas);
		
		mockMvc.perform(get(API_CURSO + "/pessoas-cadastradas/{idCurso}",curso.getId()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("pessoas"))
			.andExpect(model().attribute("pessoas", hasItem(consultaPessoaTo)))
			.andExpect(view().name(PAG_CADASTRADAS_PESSOAS));
		
	}
	
	@Test
	public void deveIrParaPaginaCadastroPessoasPoisVaiFazerUmaConsultaPorCursoSemListaDePessoas() throws Exception {
		Curso curso = Curso.builder().id(1).nome("Engenharia da Computação").build();
		
		when(cursoService.getCurso(1)).thenReturn(curso);
		
		List<ConsultaPessoaTo> pessoas = new ArrayList<ConsultaPessoaTo>();
		when(cursoService.getPessoasPorCurso(1)).thenReturn(pessoas);
		
		mockMvc.perform(get(API_CURSO + "/pessoas-cadastradas/{idCurso}",curso.getId()))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attribute("pessoas", is(Matchers.empty())))
			.andExpect(model().attribute("msgPessoas", equalTo("Nenhum registro encontrado.")))
			.andExpect(view().name(PAG_CADASTRADAS_PESSOAS));
		
	}
	
	@Test
	public void deveIrParaPaginaCadastroCursoAposCadastrarComNomePreenchidoSemValidacao() throws Exception {
		CursoTo cursoTo = CursoTo.builder().id(null).nome("Engenharia Automação").build();
		
		Curso curso = new CursoTo().getCurso(cursoTo);
		
		when(cursoService.inserir(cursoTo)).thenReturn(curso);
		
		mockMvc.perform(post(API_CURSO + "/salvar-curso")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", cursoTo.getNome()))
			.andDo(print())
			.andExpect(status().isFound())
			.andExpect(flash().attribute("sucesso", equalTo(cursoTo.getNome() + " cadastrada com sucesso !")))
			.andExpect(redirectedUrl("cadastro"));
	}
	
	@Test
	public void deveIrParaPaginaCadastroCursoAposNaoCadastrarPoisNaoPreencheuCampos() throws Exception {
		mockMvc.perform(post(API_CURSO + "/salvar-curso"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(model().attribute("errosValidacao",not(is(Matchers.empty()))))
			.andExpect(view().name(PAG_CADASTRO_CURSO));
	}
	
	@Test
	public void deveAtualizarCurso() throws Exception {
		CursoTo cursoTo = CursoTo.builder().id(2).nome("Engenharia Automação").build();
		
		Curso curso = new CursoTo().getCurso(cursoTo);
		
		when(cursoService.inserir(cursoTo)).thenReturn(curso);
		
		mockMvc.perform(post(API_CURSO + "/salvar-curso")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", String.valueOf(cursoTo.getId()))
				.param("nome", cursoTo.getNome()))
			.andDo(print())
			.andExpect(status().isFound())
			.andExpect(flash().attribute("sucesso", equalTo(cursoTo.getNome() + " atualizada com sucesso !")))
			.andExpect(redirectedUrl("cadastro"));
	}
	
	@Test
	public void deveMostrarErroDeCadastroPoisJaTemNomeExistente() throws Exception {
		when(cursoService.inserir(any())).thenThrow(new RegraException("Erro"));
		
		mockMvc.perform(post(API_CURSO + "/salvar-curso")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", "Teste 123"))
			.andDo(print())
			.andExpect(status().isFound())
			.andExpect(model().attribute("erro",not(is(Matchers.empty()))))
			.andExpect(redirectedUrl("cadastro"));
		
	}
}
