package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.service.ProfessorService;
import com.gabrielferreira.projeto.service.TelefoneService;
import com.gabrielferreira.projeto.service.TipoTelefoneService;

@Controller
public class ContatoProfessorRecurso {

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private TipoTelefoneService tipoService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/contatoprofessor/{idprofessor}")
	public ModelAndView editar(@PathVariable("idprofessor") Integer id) {
		Professor professor = professorService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoprofessor");
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		Iterable<Telefone> telefones = telefoneService.consultarTodos(id);
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("professorobj",professor);
		modelAndView.addObject("telefones",telefones);
		modelAndView.addObject("telefoneobj",new Telefone());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarcontatoprofessor/{idcontato}")
	public ModelAndView deletar(@PathVariable("idcontato") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoprofessor");
		Professor professor = (Professor) telefoneService.consultarPorId(id).get().getPessoa();
		telefoneService.deletar(id);
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("professorobj",professor);
		modelAndView.addObject("telefoneobj",new Telefone());
		modelAndView.addObject("telefones",telefoneService.consultarTodos(professor.getId()));
		modelAndView.addObject("msg","Telefone removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/editarcontatoprofessor/{idcontato}")
	public ModelAndView editarcontato(@PathVariable("idcontato") Integer id) {
		Professor professor = (Professor) telefoneService.consultarPorId(id).get().getPessoa();
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoprofessor");
		Telefone telefone = telefoneService.consultarPorId(id).get();
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("professorobj",professor);
		modelAndView.addObject("telefoneobj",telefone);
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value = "**/addcontatoprofessor/{professorid}")
	public ModelAndView salvar(@Valid @ModelAttribute("telefone") Telefone telefone , BindingResult bindingResult, 
			@PathVariable ("professorid") Integer professorid) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modeAndView = new ModelAndView("cadastro/contatoprofessor");
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			Iterable<Telefone> telefones = telefoneService.consultarTodos(professorid);
			Professor professor = professorService.consultarPorId(professorid);
			modeAndView.addObject("tipos",tipos);
			modeAndView.addObject("telefones",telefones);
			modeAndView.addObject("professorobj",professor);
			modeAndView.addObject("telefoneobj",new Telefone());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modeAndView.addObject("msgdeerrotelefone",msg);
			return modeAndView;
		}
		
		if(telefone.getId() == null) {
			Professor professor = professorService.consultarPorId(professorid);
			TipoTelefone tipoTelefone = new TipoTelefone();
			tipoTelefone.getTelefones().add(telefone);
			telefone.setPessoa(professor);
			telefoneService.inserir(telefone);
			ModelAndView modelAndView = new ModelAndView("cadastro/contatoprofessor");
			modelAndView.addObject("professorobj",professor);
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			modelAndView.addObject("tipos",tipos);
			Iterable<Telefone> telefones = telefoneService.consultarTodos(professorid);
			modelAndView.addObject("telefones",telefones);
			modelAndView.addObject("msg","Telefone cadastrado com sucesso");
			modelAndView.addObject("telefoneobj",new Telefone());
			return modelAndView;
		}
		
		else {
			Professor professor = professorService.consultarPorId(professorid);
			TipoTelefone tipoTelefone = new TipoTelefone();
			tipoTelefone.getTelefones().add(telefone);
			telefone.setPessoa(professor);
			telefoneService.inserir(telefone);
			ModelAndView modelAndView = new ModelAndView("cadastro/contatoprofessor");
			modelAndView.addObject("professorobj",professor);
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			modelAndView.addObject("tipos",tipos);
			Iterable<Telefone> telefones = telefoneService.consultarTodos(professorid);
			modelAndView.addObject("telefones",telefones);
			modelAndView.addObject("msg","Telefone atualizado com sucesso");
			modelAndView.addObject("telefoneobj",new Telefone());
			return modelAndView;
		}
		
	}
	
	
}
