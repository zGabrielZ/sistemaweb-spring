package com.gabrielferreira.projeto.modelo.recurso;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.dto.PessoaDisciplinaDTO;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.service.ProfessorService;
import com.gabrielferreira.projeto.service.DisciplinaService;

@Controller
public class DisciplinaProfessorRecurso {
	
	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private DisciplinaService disciplinaService;

	@RequestMapping(method = RequestMethod.GET,value = "/disciplinaprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Integer id) {
		Professor professor = professorService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaprofessor");
		Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(id);
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("professorobj", professor);
		modelAndView.addObject("disciplinas2", disciplinas2);
		modelAndView.addObject("disciplinas", disciplinas);
		modelAndView.addObject("professores", professores);
		modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/adddisciplinaprofessor/{professorid}")
	public ModelAndView salvar(@Valid @ModelAttribute("disciplina") PessoaDisciplinaDTO dto,
			BindingResult bindingResult, @PathVariable("professorid") Integer professorid) {

		Professor professor = (Professor) dto.getPessoa();
		Disciplina disciplina = dto.getDisciplina();
		professor = professorService.consultarPorId(professorid);
		if (!professor.getDisciplinas().contains(disciplina)) {
			professor.addDisciplina(disciplina);
			professorService.inserir(professor);
			ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaprofessor");
			modelAndView.addObject("professorobj", professor);
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modelAndView.addObject("disciplinas", disciplinas);
			Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(professorid);
			modelAndView.addObject("disciplinas2", disciplinas2);
			modelAndView.addObject("msg", "Disciplina cadastrado com sucesso");
			modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
			return modelAndView;
		}
		else {
			ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaprofessor");
			modelAndView.addObject("professorobj", professor);
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modelAndView.addObject("disciplinas", disciplinas);
			Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(professorid);
			modelAndView.addObject("disciplinas2", disciplinas2);
			modelAndView.addObject("msg", "Disciplina já está cadastrado");
			modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
			return modelAndView;
		}
		
	}
}
