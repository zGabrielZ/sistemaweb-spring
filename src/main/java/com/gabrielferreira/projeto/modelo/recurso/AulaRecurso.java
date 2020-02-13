package com.gabrielferreira.projeto.modelo.recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Itens;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.service.DisciplinaService;
import com.gabrielferreira.projeto.service.ItensService;
import com.gabrielferreira.projeto.service.PessoaService;

@Controller
public class AulaRecurso {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	@Autowired
	private ItensService itensService;
	
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedapessoa")
	public ModelAndView procurarNome(@RequestParam("procurarnomepessoa") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodeaula");
		Iterable<Pessoa> pessoa = pessoaService.consultarTodos();
		Iterable<Disciplina> disciplina = disciplinaService.consultarTodos();
		modelAndView.addObject("pessoas", pessoa);
		modelAndView.addObject("disciplinas", disciplina);
		modelAndView.addObject("itens",itensService.buscarNome(nome));
		modelAndView.addObject("itensobj",new Itens());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/additens")
	public ModelAndView salvar(Itens itens) {
		
		itens.setPessoa(itens.getPessoa());
		itens.setDisciplina(itens.getDisciplina());
		itensService.inserir(itens);
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodeaula");
		Iterable<Pessoa> pessoa = pessoaService.consultarTodos();
		Iterable<Disciplina> disciplina = disciplinaService.consultarTodos();
		modelAndView.addObject("pessoas", pessoa);
		modelAndView.addObject("disciplinas", disciplina);
		modelAndView.addObject("msg","Cadastrado com sucesso");
		modelAndView.addObject("itens", itensService.consultarTodos());
		modelAndView.addObject("itensobj", new Itens());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletaritens/{iditens}")
	public ModelAndView deletar(@PathVariable("iditens") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodeaula");
		itensService.deletar(id);
		Iterable<Pessoa> pessoa = pessoaService.consultarTodos();
		Iterable<Disciplina> disciplina = disciplinaService.consultarTodos();
		modelAndView.addObject("pessoas", pessoa);
		modelAndView.addObject("disciplinas", disciplina);
		modelAndView.addObject("msg","Removido com sucesso");
		modelAndView.addObject("itens", itensService.consultarTodos());
		modelAndView.addObject("itensobj", new Itens());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaritens")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrodeaula");
		Iterable<Pessoa> pessoa = pessoaService.consultarTodos();
		Iterable<Disciplina> disciplina = disciplinaService.consultarTodos();
		modelAndView.addObject("pessoas", pessoa);
		modelAndView.addObject("disciplinas", disciplina);
		modelAndView.addObject("itens", itensService.consultarTodos());
		modelAndView.addObject("itensobj", new Itens());
		return modelAndView;
	}
	
}
