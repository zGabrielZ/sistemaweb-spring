package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.dto.QuantidadeDTO;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Itens;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.CursoService;
import com.gabrielferreira.projeto.service.DisciplinaService;
import com.gabrielferreira.projeto.service.ItensService;
import com.gabrielferreira.projeto.service.PessoaService;
import com.gabrielferreira.projeto.service.ProfessorService;

@Controller
public class IndexRecurso {
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private ItensService itensService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(value = "/entrar",method = RequestMethod.GET)
	public String entrar() {
		return "entrar";
	}

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/quantidadedepessoas",method = RequestMethod.GET)
	public ModelAndView quantidade() {
		ModelAndView modelAndView = new ModelAndView("/quantidadedepessoas");
		List<QuantidadeDTO> qtd = new ArrayList<>();
		Long alunos = alunoService.quantidade();
		Long professores = professorService.quantidade();
		
		QuantidadeDTO q = new QuantidadeDTO(alunos,"Aluno");
		qtd.add(q);
		
		q = new QuantidadeDTO(professores,"Professor");
		qtd.add(q);
		modelAndView.addObject("grafico",qtd);		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrodisciplina",method = RequestMethod.GET)
	public ModelAndView cadastroDisciplina() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		modelAndView.addObject("disciplinaobj",new Disciplina());
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		modelAndView.addObject("disciplinas",disciplinas);
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastroaluno",method = RequestMethod.GET)
	public ModelAndView cadastroAluno() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		modelAndView.addObject("sexos",Sexo.values());
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",Estado.values());
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastroprofessor",method = RequestMethod.GET)
	public ModelAndView cadastroProfessor() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("sexos",Sexo.values());
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",Estado.values());
		modelAndView.addObject("professores",professores);
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/cadastrodeaula")
	public ModelAndView cadastroItens() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodeaula");
		Iterable<Pessoa> pessoa = pessoaService.consultarTodos();
		Iterable<Disciplina> disciplina = disciplinaService.consultarTodos();
		modelAndView.addObject("pessoas", pessoa);
		modelAndView.addObject("disciplinas", disciplina);
		modelAndView.addObject("itens", itensService.consultarTodos());
		modelAndView.addObject("itensobj", new Itens());
		return modelAndView;
	}
}
