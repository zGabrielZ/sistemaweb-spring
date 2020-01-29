package com.gabrielferreira.projeto.modelo.recurso;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.modelo.dto.AlunoDTO;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.modelo.entidade.Estado;
import com.gabrielferreira.projeto.modelo.entidade.Sexo;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.CursoService;
import com.gabrielferreira.projeto.service.DisciplinaService;
import com.gabrielferreira.projeto.service.EscolaService;
import com.gabrielferreira.projeto.service.EstadoService;
import com.gabrielferreira.projeto.service.SexoService;

@Controller
public class IndexRecurso {

	@Autowired
	private EscolaService escolaService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private SexoService sexoService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/cadastroescola",method = RequestMethod.GET)
	public ModelAndView cadastroescola() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroescola");
		modelAndView.addObject("escolaobj",new Escola());
		Iterable<Escola> escolas = escolaService.consultarTodos();
		modelAndView.addObject("escolas",escolas);
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrocurso",method = RequestMethod.GET)
	public ModelAndView cadastrocurso() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrocurso");
		modelAndView.addObject("cursoobj",new Curso());
		Iterable<Curso> cursos = cursoService.consultarTodos();
		modelAndView.addObject("cursos",cursos);
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrodisciplina",method = RequestMethod.GET)
	public ModelAndView cadastrodisciplina() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		modelAndView.addObject("disciplinaobj",new Disciplina());
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		modelAndView.addObject("disciplinas",disciplinas);
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastroaluno",method = RequestMethod.GET)
	public ModelAndView cadastroaluno() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Sexo> sexos = sexoService.consultarTodos();
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Estado> estados = estadoService.consultarTodos();
		Iterable<Escola> escolas = escolaService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		Iterable<AlunoDTO> alunoDTOs = ((Collection<Aluno>) alunos).stream()
					.map(x -> new AlunoDTO(x)).
						collect(Collectors.toList());
		modelAndView.addObject("sexos",sexos);
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",estados);
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("alunos",alunoDTOs);
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
}
