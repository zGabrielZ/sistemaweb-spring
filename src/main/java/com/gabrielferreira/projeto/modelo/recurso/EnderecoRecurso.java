package com.gabrielferreira.projeto.modelo.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.service.AlunoService;

@Controller
public class EnderecoRecurso {

	@Autowired
	private AlunoService alunoService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/enderecoaluno/{idaluno}")
	public ModelAndView editar(@PathVariable("idaluno") Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/enderecoaluno");
		modelAndView.addObject("alunoobj",aluno);
		return modelAndView;
	}
	
	
}
