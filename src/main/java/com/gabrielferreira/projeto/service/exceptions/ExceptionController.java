package com.gabrielferreira.projeto.service.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(DatabaseException.class)
	public ModelAndView naoPodeExcluir(DatabaseException ex) {
		ModelAndView model = new ModelAndView("error");

		model.addObject("status", 404);
		model.addObject("error", "Operação não pode ser realizada, pois a pessoa(aluno ou professor) está "
				+ "relacionada com a disciplina");
		model.addObject("message", ex.getMessage());
		return model;
	}
}
