package com.gabrielferreira.projeto.modelo.recurso;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.modelo.entidade.Professor;
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
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("professorobj", professor);
		modelAndView.addObject("disciplinas2", disciplinas2);
		modelAndView.addObject("professores", professores);
		return modelAndView;
	}
}
