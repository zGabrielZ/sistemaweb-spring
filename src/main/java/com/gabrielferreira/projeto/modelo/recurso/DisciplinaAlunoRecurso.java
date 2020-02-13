package com.gabrielferreira.projeto.modelo.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.DisciplinaService;

@Controller
public class DisciplinaAlunoRecurso {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private DisciplinaService disciplinaService;

	@RequestMapping(method = RequestMethod.GET, value = "/disciplinaaluno/{idaluno}")
	public ModelAndView editar(@PathVariable("idaluno") Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaaluno");
		Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(id);
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		modelAndView.addObject("alunoobj", aluno);
		modelAndView.addObject("disciplinas2", disciplinas2);
		modelAndView.addObject("alunos", alunos);
		return modelAndView;
	}
}
