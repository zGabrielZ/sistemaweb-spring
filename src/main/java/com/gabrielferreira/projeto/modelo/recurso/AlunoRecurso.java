package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.List;

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
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.enums.Estado;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.CursoService;
import com.gabrielferreira.projeto.service.PessoaService;

@Controller
public class AlunoRecurso {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CursoService cursoService;
	
	@RequestMapping(method = RequestMethod.POST,value = "**/salvaraluno")
	public ModelAndView salvar(@Valid Aluno aluno,
			BindingResult bindingResult) {		
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelAndView.addObject("sexos",Sexo.values());
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",Estado.values());
			modelAndView.addObject("alunos",alunos);
			modelAndView.addObject("alunoobj",new Aluno());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modelAndView.addObject("msgdeerro",msg);
			return modelAndView;
		}
		
		if(aluno.getId() == null) {
			if(pessoaService.buscarCpfPessoa(aluno)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<Aluno> alunos = alunoService.consultarTodos();
				modelAndView.addObject("sexos",Sexo.values());
				modelAndView.addObject("cursos",cursos);
				modelAndView.addObject("estados",Estado.values());
				modelAndView.addObject("alunos",alunos);
				modelAndView.addObject("msgdeerro2","Cpf ja existe no banco !!");
				modelAndView.addObject("alunoobj",new Aluno());
				return modelAndView;
			}
			
			alunoService.inserir(aluno);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelAndView.addObject("sexos",Sexo.values());
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",Estado.values());
			modelAndView.addObject("alunos",alunos);
			modelAndView.addObject("msg","Cadastrado com sucesso !!");
			modelAndView.addObject("alunoobj",new Aluno());
			return modelAndView;
		}
		else {
			if(pessoaService.buscarCpfAtualizado(aluno)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
				Iterable<Curso> cursos = cursoService.consultarTodos();
				Iterable<Aluno> alunos = alunoService.consultarTodos();
				modelAndView.addObject("sexos",Sexo.values());
				modelAndView.addObject("cursos",cursos);
				modelAndView.addObject("estados",Estado.values());
				modelAndView.addObject("alunos",alunos);
				modelAndView.addObject("msgdeerro2","Cpf que informou ja existe cadastrado!!");
				modelAndView.addObject("alunoobj",new Aluno());
				return modelAndView;
			}
			
			alunoService.inserir(aluno);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<Curso> cursos = cursoService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelAndView.addObject("sexos",Sexo.values());
			modelAndView.addObject("cursos",cursos);
			modelAndView.addObject("estados",Estado.values());
			modelAndView.addObject("alunos",alunos);
			modelAndView.addObject("msg","Atualizado com sucesso !!");
			modelAndView.addObject("alunoobj",new Aluno());
			return modelAndView;
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaaluno")
	public ModelAndView consultarTodos() {
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
	
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedoaluno")
	public ModelAndView procurarNome(@RequestParam("procurarnomealuno") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.buscarNome(nome);
		modelAndView.addObject("sexos",Sexo.values());
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",Estado.values());
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletaraluno/{idaluno}")
	public ModelAndView deletar(@PathVariable("idaluno") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		alunoService.deletar(id);
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		modelAndView.addObject("sexos",Sexo.values());
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",Estado.values());
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		modelAndView.addObject("msg","Deletado com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editaraluno/{idaluno}")
	public ModelAndView editar(@PathVariable("idaluno") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Curso> cursos = cursoService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		Aluno aluno = alunoService.consultarPorId(id);
		modelAndView.addObject("sexos",Sexo.values());
		modelAndView.addObject("cursos",cursos);
		modelAndView.addObject("estados",Estado.values());
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",aluno);
		return modelAndView;
	}
}
