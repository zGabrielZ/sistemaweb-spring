package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.dto.ProfessorDTO;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.modelo.entidade.Estado;
import com.gabrielferreira.projeto.modelo.entidade.Sexo;
import com.gabrielferreira.projeto.service.ProfessorService;
import com.gabrielferreira.projeto.service.CursoService;
import com.gabrielferreira.projeto.service.EscolaService;
import com.gabrielferreira.projeto.service.EstadoService;
import com.gabrielferreira.projeto.service.PessoaService;
import com.gabrielferreira.projeto.service.SexoService;

@Controller
public class ProfessorRecurso {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private SexoService sexoService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping(method = RequestMethod.POST,value = "**/salvarprofessor")
	public ModelAndView salvar(@Valid Professor professor,
			BindingResult bindingResult) {		
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Sexo> sexos = sexoService.consultarTodos();
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Estado> estados = estadoService.consultarTodos();
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<Professor> professores = professorService.consultarTodos();
			Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
						.map(x -> new ProfessorDTO(x)).
							collect(Collectors.toList());
			modelAndView.addObject("sexos",sexos);
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",estados);
			modelAndView.addObject("escolas",escolas);
			modelAndView.addObject("professores",professorDTOs);
			modelAndView.addObject("professorobj",new Professor());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modelAndView.addObject("msgdeerro",msg);
			return modelAndView;
		}
		
		if(professor.getId() == null) {
			if(pessoaService.buscarCpfPessoa(professor)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
				Iterable<Sexo> sexos = sexoService.consultarTodos();
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<Estado> estados = estadoService.consultarTodos();
				Iterable<Escola> escolas = escolaService.consultarTodos();
				Iterable<Professor> professores = professorService.consultarTodos();
				Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
							.map(x -> new ProfessorDTO(x)).
								collect(Collectors.toList());
				modelAndView.addObject("sexos",sexos);
				modelAndView.addObject("cursos",cursos);
				modelAndView.addObject("estados",estados);
				modelAndView.addObject("escolas",escolas);
				modelAndView.addObject("professores",professorDTOs);
				modelAndView.addObject("msgdeerro2","Cpf ja existe no banco !!");
				modelAndView.addObject("professorobj",new Professor());
				return modelAndView;
			}
			
			professorService.inserir(professor);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Sexo> sexos = sexoService.consultarTodos();
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Estado> estados = estadoService.consultarTodos();
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<Professor> professores = professorService.consultarTodos();
			Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
						.map(x -> new ProfessorDTO(x)).
							collect(Collectors.toList());
			modelAndView.addObject("sexos",sexos);
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",estados);
			modelAndView.addObject("escolas",escolas);
			modelAndView.addObject("professores",professorDTOs);
			modelAndView.addObject("msg","Cadastrado com sucesso !!");
			modelAndView.addObject("professorobj",new Professor());
			return modelAndView;
		}
		else {
			if(pessoaService.buscarCpfAtualizado(professor)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
				Iterable<Sexo> sexos = sexoService.consultarTodos();
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<Estado> estados = estadoService.consultarTodos();
				Iterable<Escola> escolas = escolaService.consultarTodos();
				Iterable<Professor> professores = professorService.consultarTodos();
				Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
							.map(x -> new ProfessorDTO(x)).
								collect(Collectors.toList());
				modelAndView.addObject("sexos",sexos);
				modelAndView.addObject("cursos",cursos);
				modelAndView.addObject("estados",estados);
				modelAndView.addObject("escolas",escolas);
				modelAndView.addObject("professores",professorDTOs);
				modelAndView.addObject("msgdeerro2","Cpf que informou ja existe cadastrado!!");
				modelAndView.addObject("professorobj",new Professor());
				return modelAndView;
			}
			
			professorService.inserir(professor);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Sexo> sexos = sexoService.consultarTodos();
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Estado> estados = estadoService.consultarTodos();
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<Professor> professores = professorService.consultarTodos();
			Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
						.map(x -> new ProfessorDTO(x)).
							collect(Collectors.toList());
			modelAndView.addObject("sexos",sexos);
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",estados);
			modelAndView.addObject("escolas",escolas);
			modelAndView.addObject("professores",professorDTOs);
			modelAndView.addObject("msg","Atualizado com sucesso !!");
			modelAndView.addObject("professorobj",new Professor());
			return modelAndView;
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaprofessor")
	public ModelAndView consultarTodos() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Sexo> sexos = sexoService.consultarTodos();
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Estado> estados = estadoService.consultarTodos();
		Iterable<Escola> escolas = escolaService.consultarTodos();
		Iterable<Professor> professores = professorService.consultarTodos();
		Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
					.map(x -> new ProfessorDTO(x)).
						collect(Collectors.toList());
		modelAndView.addObject("sexos",sexos);
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",estados);
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("professores",professorDTOs);
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedoprofessor")
	public ModelAndView procurarNome(@RequestParam("procurarnomeprofessor") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Sexo> sexos = sexoService.consultarTodos();
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Estado> estados = estadoService.consultarTodos();
		Iterable<Escola> escolas = escolaService.consultarTodos();
		Iterable<Professor> professores = professorService.buscarNome(nome);
		Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
					.map(x -> new ProfessorDTO(x)).
						collect(Collectors.toList());
		modelAndView.addObject("sexos",sexos);
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",estados);
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("professores",professorDTOs);
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarprofessor/{idprofessor}")
	public ModelAndView deletar(@PathVariable("idprofessor") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		professorService.deletar(id);
		Iterable<Sexo> sexos = sexoService.consultarTodos();
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Estado> estados = estadoService.consultarTodos();
		Iterable<Escola> escolas = escolaService.consultarTodos();
		Iterable<Professor> professores = professorService.consultarTodos();
		Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
					.map(x -> new ProfessorDTO(x)).
						collect(Collectors.toList());
		modelAndView.addObject("sexos",sexos);
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",estados);
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("professores",professorDTOs);
		modelAndView.addObject("professorobj",new Professor());
		modelAndView.addObject("msg","Deletado com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Sexo> sexos = sexoService.consultarTodos();
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Estado> estados = estadoService.consultarTodos();
		Iterable<Escola> escolas = escolaService.consultarTodos();
		Iterable<Professor> professores = professorService.consultarTodos();
		Iterable<ProfessorDTO> professorDTOs = ((Collection<Professor>) professores).stream()
					.map(x -> new ProfessorDTO(x)).
						collect(Collectors.toList());
		Professor professor = professorService.consultarPorId(id);
		modelAndView.addObject("sexos",sexos);
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",estados);
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("professores",professorDTOs);
		modelAndView.addObject("professorobj",professor);
		return modelAndView;
	}
	
}
