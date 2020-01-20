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

import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.service.PessoaService;
import com.gabrielferreira.projeto.service.ProfessorService;

@Controller
public class ProfessorRecurso {

	@Autowired
	private ProfessorService professorService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarprofessor")
	public ModelAndView salvar(@Valid Professor professor,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modelandView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Professor> professores = professorService.consultarTodos();
			modelandView.addObject("professores",professores);
			modelandView.addObject("professorobj",professor);
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modelandView.addObject("msgdeerro",msg);
			return modelandView;
		}
		
		if(professor.getId() == null ) {
			if(pessoaService.buscarCpf(professor)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
				Iterable<Professor> professores = professorService.consultarTodos();
				modelAndView.addObject("professores",professores);
				modelAndView.addObject("msg","Cpf ja existe no banco !!");
				modelAndView.addObject("professorobj",new Professor());
				return modelAndView;
			}
			
			professorService.inserir(professor);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Professor> professores = professorService.consultarTodos();
			modelAndView.addObject("professores",professores);
			modelAndView.addObject("msg","Cadastrado com sucesso !!");
			modelAndView.addObject("professorobj",new Professor());
			return modelAndView;
		}
		else {
			if(pessoaService.buscarCpfAtualizado(professor)) {
				ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
				Iterable<Professor> professores = professorService.consultarTodos();
				modelAndView.addObject("professores",professores);
				modelAndView.addObject("msg","Cpf que informou ja existe cadastrado!!");
				modelAndView.addObject("professorobj",new Professor());
				return modelAndView;
			}
			
			professorService.inserir(professor);
			ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
			Iterable<Professor> professores = professorService.consultarTodos();
			modelAndView.addObject("professores",professores);
			modelAndView.addObject("msg","Atualizado com sucesso!!");
			modelAndView.addObject("professorobj",new Professor());
			return modelAndView;
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaprofessores")
	public ModelAndView consultarTodos() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("professores",professores);
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/editarprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Professor professor = professorService.consultarPorId(id);
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("professores",professores);
		modelAndView.addObject("professorobj",professor);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarprofessor/{idprofessor}")
	public ModelAndView deletar(@PathVariable("idprofessor") Integer id){
		professorService.deletar(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Professor> professores = professorService.consultarTodos();
		modelAndView.addObject("professores",professores);
		modelAndView.addObject("professorobj",new Professor());
		modelAndView.addObject("msg","Professor removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "**/pesquisarnomeprofessor")
	public ModelAndView buscarNome(@RequestParam("procurarnomeprofessor") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroprofessor");
		Iterable<Professor> professores = professorService.buscarNome(nome);
		modelAndView.addObject("professores",professores);
		modelAndView.addObject("professorobj",new Professor());
		return modelAndView;
	}
	
	
}
