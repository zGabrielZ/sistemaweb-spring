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
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.service.DisciplinaService;

@Controller
public class DisciplinaRecurso {

	@Autowired
	private DisciplinaService disciplinaService;
	
	@RequestMapping(method = RequestMethod.POST,value = "**/salvardisciplina")
	public ModelAndView salvar(@Valid Disciplina disciplina,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrodisciplina");
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modeAndView.addObject("disciplinas",disciplinas);
			modeAndView.addObject("disciplinaobj",new Disciplina());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modeAndView.addObject("msgdeerro",msg);
			return modeAndView;
		}
		
		if(disciplina.getId() == null) {
			if(disciplinaService.buscarNomeDisciplina(disciplina)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastrodisciplina");
				Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
				modeAndView.addObject("msgdeerro","Nome já existe no banco !!");
				modeAndView.addObject("disciplinas",disciplinas);
				modeAndView.addObject("disciplinaobj",new Disciplina());
				return modeAndView;
			}
			
			disciplinaService.inserir(disciplina);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrodisciplina");
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modeAndView.addObject("msg","Cadastrado com sucesso !!");
			modeAndView.addObject("disciplinas",disciplinas);
			modeAndView.addObject("disciplinaobj",new Disciplina());
			return modeAndView;
			
		}
		else {
			if(disciplinaService.buscarNomeAtualizado(disciplina)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastrodisciplina");
				Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
				modeAndView.addObject("msgdeerro","Nome que informou já existe cadastrado !!");
				modeAndView.addObject("disciplinas",disciplinas);
				modeAndView.addObject("disciplinaobj",new Disciplina());
				return modeAndView;
			}
			
			disciplinaService.inserir(disciplina);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastrodisciplina");
			Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
			modeAndView.addObject("msg","Atualizado com sucesso !!");
			modeAndView.addObject("disciplinas",disciplinas);
			modeAndView.addObject("disciplinaobj",new Disciplina());
			return modeAndView;
		}

	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listadisciplina")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		modelAndView.addObject("disciplinas",disciplinas);
		modelAndView.addObject("disciplinaobj",new Disciplina());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/editardisciplina/{iddisciplina}")
	public ModelAndView editar(@PathVariable("iddisciplina") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		Disciplina disciplina = disciplinaService.consultarPorId(id);
		modelAndView.addObject("disciplinaobj",disciplina);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletardisciplina/{iddisciplina}")
	public ModelAndView deletar(@PathVariable("iddisciplina") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		disciplinaService.deletar(id);
		Iterable<Disciplina> disciplinas = disciplinaService.consultarTodos();
		modelAndView.addObject("disciplinas",disciplinas);
		modelAndView.addObject("disciplinaobj",new Disciplina());
		modelAndView.addObject("msg","Disciplina removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedadisciplina")
	public ModelAndView procurarNome(@RequestParam("procurarnomedisciplina") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodisciplina");
		modelAndView.addObject("disciplinas",disciplinaService.buscarNome(nome));
		modelAndView.addObject("disciplinaobj",new Disciplina());
		return modelAndView;
	}
	
}
