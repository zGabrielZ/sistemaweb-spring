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
import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.service.AlunoService;
import com.gabrielferreira.projeto.service.TelefoneService;
import com.gabrielferreira.projeto.service.TipoTelefoneService;

@Controller
public class ContatoAlunoRecurso {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private TipoTelefoneService tipoService;
	
	@Autowired
	private TelefoneService telefoneService;
	
	@RequestMapping(method = RequestMethod.GET,value = "/contatoaluno/{idaluno}")
	public ModelAndView editar(@PathVariable("idaluno") Integer id) {
		Aluno aluno = alunoService.consultarPorId(id);
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoaluno");
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		Iterable<Telefone> telefones = telefoneService.consultarTodos(id);
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("alunoobj",aluno);
		modelAndView.addObject("telefones",telefones);
		modelAndView.addObject("telefoneobj",new Telefone());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarcontato/{idcontato}")
	public ModelAndView deletar(@PathVariable("idcontato") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoaluno");
		Aluno aluno = (Aluno) telefoneService.consultarPorId(id).get().getPessoa();
		telefoneService.deletar(id);
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("alunoobj",aluno);
		modelAndView.addObject("telefoneobj",new Telefone());
		modelAndView.addObject("msg","Telefone removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/editarcontato/{idcontato}")
	public ModelAndView editarcontato(@PathVariable("idcontato") Integer id) {
		Aluno aluno = (Aluno) telefoneService.consultarPorId(id).get().getPessoa();
		ModelAndView modelAndView = new ModelAndView("cadastro/contatoaluno");
		Telefone telefone = telefoneService.consultarPorId(id).get();
		Iterable<TipoTelefone> tipos = tipoService.consultarTodos();	
		modelAndView.addObject("tipos",tipos);
		modelAndView.addObject("alunoobj",aluno);
		modelAndView.addObject("telefoneobj",telefone);
		return modelAndView;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value = "**/addcontato/{alunoid}")
	public ModelAndView salvar(@Valid @ModelAttribute("telefone") Telefone telefone , BindingResult bindingResult, 
			@PathVariable ("alunoid") Integer alunoid) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modeAndView = new ModelAndView("cadastro/contatoaluno");
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			Iterable<Telefone> telefones = telefoneService.consultarTodos(alunoid);
			Aluno aluno = alunoService.consultarPorId(alunoid);
			modeAndView.addObject("tipos",tipos);
			modeAndView.addObject("telefones",telefones);
			modeAndView.addObject("alunoobj",aluno);
			modeAndView.addObject("telefoneobj",new Telefone());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modeAndView.addObject("msgdeerrotelefone",msg);
			return modeAndView;
		}
		
		if(telefone.getId() == null) {
			Aluno aluno = alunoService.consultarPorId(alunoid);
			TipoTelefone tipoTelefone = new TipoTelefone();
			tipoTelefone.getTelefones().add(telefone);
			telefone.setPessoa(aluno);
			telefoneService.inserir(telefone);
			ModelAndView modelAndView = new ModelAndView("cadastro/contatoaluno");
			modelAndView.addObject("alunoobj",aluno);
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			modelAndView.addObject("tipos",tipos);
			Iterable<Telefone> telefones = telefoneService.consultarTodos(alunoid);
			modelAndView.addObject("telefones",telefones);
			modelAndView.addObject("msg","Telefone cadastrado com sucesso");
			modelAndView.addObject("telefoneobj",new Telefone());
			return modelAndView;
		}
		
		else {
			Aluno aluno = alunoService.consultarPorId(alunoid);
			TipoTelefone tipoTelefone = new TipoTelefone();
			tipoTelefone.getTelefones().add(telefone);
			telefone.setPessoa(aluno);
			telefoneService.inserir(telefone);
			ModelAndView modelAndView = new ModelAndView("cadastro/contatoaluno");
			modelAndView.addObject("alunoobj",aluno);
			Iterable<TipoTelefone> tipos = tipoService.consultarTodos();
			modelAndView.addObject("tipos",tipos);
			Iterable<Telefone> telefones = telefoneService.consultarTodos(alunoid);
			modelAndView.addObject("telefones",telefones);
			modelAndView.addObject("msg","Telefone atualizado com sucesso");
			modelAndView.addObject("telefoneobj",new Telefone());
			return modelAndView;
		}
		
	}
	
	
}
