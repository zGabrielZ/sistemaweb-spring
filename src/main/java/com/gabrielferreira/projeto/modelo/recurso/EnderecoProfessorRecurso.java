package com.gabrielferreira.projeto.modelo.recurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.service.ProfessorService;

@Controller
public class EnderecoProfessorRecurso {

	@Autowired
	private ProfessorService professorService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/enderecoprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Integer id) {
		Professor professor = professorService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/enderecoprofessor");
		modelAndView.addObject("professorobj",professor);
		return modelAndView;
	}
	
	
}
