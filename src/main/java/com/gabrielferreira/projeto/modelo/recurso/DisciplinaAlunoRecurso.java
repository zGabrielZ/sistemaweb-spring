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

import com.gabrielferreira.projeto.modelo.dto.PessoaDisciplinaDTO;
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
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		modelAndView.addObject("alunoobj", aluno);
		modelAndView.addObject("disciplinas2", disciplinas2);
		modelAndView.addObject("disciplinas", disciplinas);
		modelAndView.addObject("alunos", alunos);
		modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/adddisciplinaaluno/{alunoid}")
	public ModelAndView salvar(@Valid @ModelAttribute("disciplina") PessoaDisciplinaDTO dto,
			BindingResult bindingResult, @PathVariable("alunoid") Integer alunoid) {

		Aluno aluno = (Aluno) dto.getPessoa();
		Disciplina disciplina = dto.getDisciplina();
		aluno = alunoService.consultarPorId(alunoid);
		if (!aluno.getDisciplinas().contains(disciplina)) {
			aluno.addDisciplina(disciplina);
			alunoService.inserir(aluno);
			ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaaluno");
			modelAndView.addObject("alunoobj", aluno);
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modelAndView.addObject("disciplinas", disciplinas);
			Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(alunoid);
			modelAndView.addObject("disciplinas2", disciplinas2);
			modelAndView.addObject("msg", "Disciplina cadastrado com sucesso");
			modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
			return modelAndView;
		}
		else {
			ModelAndView modelAndView = new ModelAndView("cadastro/disciplinaaluno");
			modelAndView.addObject("alunoobj", aluno);
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modelAndView.addObject("disciplinas", disciplinas);
			Iterable<Disciplina> disciplinas2 = disciplinaService.consultarTodos(alunoid);
			modelAndView.addObject("disciplinas2", disciplinas2);
			modelAndView.addObject("msg", "Disciplina já está cadastrado");
			modelAndView.addObject("disciplinaobj", new PessoaDisciplinaDTO());
			return modelAndView;
		}
	}
}
