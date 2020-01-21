package com.gabrielferreira.projeto.recurso;
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
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.PessoaService;
import com.gabrielferreira.projeto.service.TipoTelefoneService;

@Controller
public class AlunoRecurso {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired 
	private TipoTelefoneService tipoTelefoneService;
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvaraluno")
	public ModelAndView salvar(@Valid Aluno aluno,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelandView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelandView.addObject("tipostelefones",tipos);
			modelandView.addObject("alunos",alunos);
			modelandView.addObject("alunoobj",aluno);
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modelandView.addObject("msgdeerro",msg);
			return modelandView;
		}
		
		
		
		
		
		
		if(aluno.getId() == null ) {
			if(pessoaService.buscarCpf(aluno)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
				Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
				Iterable<Aluno> alunos = alunoService.consultarTodos();
				modelAndView.addObject("tipostelefones",tipos);
				modelAndView.addObject("alunos",alunos);
				modelAndView.addObject("msg","Cpf ja existe no banco !!");
				modelAndView.addObject("alunoobj",new Aluno());
				return modelAndView;
			}
			
			alunoService.inserir(aluno);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelAndView.addObject("tipostelefones",tipos);
			modelAndView.addObject("alunos",alunos);
			modelAndView.addObject("msg","Cadastrado com sucesso !!");
			modelAndView.addObject("alunoobj",new Aluno());
			return modelAndView;
		}
		else {
			if(pessoaService.buscarCpfAtualizado(aluno)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
				Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
				Iterable<Aluno> alunos = alunoService.consultarTodos();
				modelAndView.addObject("tipostelefones",tipos);
				modelAndView.addObject("alunos",alunos);
				modelAndView.addObject("msg","Cpf que informou ja existe cadastrado!!");
				modelAndView.addObject("alunoobj",new Aluno());
				return modelAndView;
			}
			
			alunoService.inserir(aluno);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
			Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
			Iterable<Aluno> alunos = alunoService.consultarTodos();
			modelAndView.addObject("tipostelefones",tipos);
			modelAndView.addObject("alunos",alunos);
			modelAndView.addObject("msg","Atualizado com sucesso!!");
			modelAndView.addObject("alunoobj",new Aluno());
			return modelAndView;
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaalunos")
	public ModelAndView consultarTodos() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		modelAndView.addObject("tipostelefones",tipos);
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editaraluno/{idaluno}")
	public ModelAndView editar(@PathVariable("idaluno") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Aluno aluno = alunoService.consultarPorId(id);
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
		modelAndView.addObject("tipostelefones",tipos);
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",aluno);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletaraluno/{idaluno}")
	public ModelAndView deletar(@PathVariable("idaluno") Integer id){
		alunoService.deletar(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Aluno> alunos = alunoService.consultarTodos();
		Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
		modelAndView.addObject("tipostelefones",tipos);
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		modelAndView.addObject("msg","Aluno removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "**/pesquisarnome")
	public ModelAndView buscarNome(@RequestParam("procurarnome") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroaluno");
		Iterable<Aluno> alunos = alunoService.buscarNome(nome);
		Iterable<TipoTelefone> tipos = tipoTelefoneService.consultarTodos();
		modelAndView.addObject("tipostelefones",tipos);
		modelAndView.addObject("alunos",alunos);
		modelAndView.addObject("alunoobj",new Aluno());
		return modelAndView;
	}
	
	
}
