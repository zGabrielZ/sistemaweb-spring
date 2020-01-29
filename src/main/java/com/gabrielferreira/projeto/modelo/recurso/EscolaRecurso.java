package com.gabrielferreira.projeto.modelo.recurso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

import com.gabrielferreira.projeto.modelo.dto.EscolaDTO;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.service.EscolaService;

@Controller
public class EscolaRecurso {

	@Autowired
	private EscolaService escolaService;
	
	@RequestMapping(method = RequestMethod.POST,value = "**/salvarescola")
	public ModelAndView salvar(@Valid Escola escola,
			BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastroescola");
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escolas).stream().map(x -> new EscolaDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("escolas",escolaDTOs);
			modeAndView.addObject("escolaobj",new Escola());
			
			List<String> msg = new ArrayList<String>();
			for(ObjectError object : bindingResult.getAllErrors()) {
				msg.add(object.getDefaultMessage());
			}		
			modeAndView.addObject("msgdeerro",msg);
			return modeAndView;
		}
		
		if(escola.getId() == null) {
			if(escolaService.buscarNomeEscola(escola)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastroescola");
				Iterable<Escola> escolas = escolaService.consultarTodos();
				Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escolas).stream().map(x -> new EscolaDTO(x)).
							collect(Collectors.toList());
				modeAndView.addObject("msgdeerro","Nome já existe no banco !!");
				modeAndView.addObject("escolas",escolaDTOs);
				modeAndView.addObject("escolaobj",new Escola());
				return modeAndView;
			}
			
			escolaService.inserir(escola);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastroescola");
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escolas).stream().map(x -> new EscolaDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("msg","Cadastrado com sucesso !!");
			modeAndView.addObject("escolas",escolaDTOs);
			modeAndView.addObject("escolaobj",new Escola());
			return modeAndView;
			
		}
		else {
			if(escolaService.buscarNomeAtualizado(escola)) {
				ModelAndView modeAndView = new ModelAndView("cadastro/cadastroescola");
				Iterable<Escola> escolas = escolaService.consultarTodos();
				Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escolas).stream().map(x -> new EscolaDTO(x)).
							collect(Collectors.toList());
				modeAndView.addObject("msgdeerro","Nome que informou já existe cadastrado !!");
				modeAndView.addObject("escolas",escolaDTOs);
				modeAndView.addObject("escolaobj",new Escola());
				return modeAndView;
			}
			
			escolaService.inserir(escola);
			ModelAndView modeAndView = new ModelAndView("cadastro/cadastroescola");
			Iterable<Escola> escolas = escolaService.consultarTodos();
			Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escolas).stream().map(x -> new EscolaDTO(x)).
						collect(Collectors.toList());
			modeAndView.addObject("msg","Atualizado com sucesso !!");
			modeAndView.addObject("escolas",escolaDTOs);
			modeAndView.addObject("escolaobj",new Escola());
			return modeAndView;
		}

	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/listaescola")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroescola");
		Iterable<Escola> escola = escolaService.consultarTodos();
		Iterable<EscolaDTO> escolaDTOs = ((Collection<Escola>) escola).stream().map(x -> new EscolaDTO(x)).
					collect(Collectors.toList());
		modelAndView.addObject("escolas",escolaDTOs);
		modelAndView.addObject("escolaobj",new Escola());
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/editarescola/{idescola}")
	public ModelAndView editar(@PathVariable("idescola") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroescola");
		Escola escola = escolaService.consultarPorId(id);
		modelAndView.addObject("escolaobj",escola);
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/deletarescola/{idescola}")
	public ModelAndView deletar(@PathVariable("idescola") Integer id) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroescola");
		escolaService.deletar(id);
		Iterable<Escola> escolas = escolaService.consultarTodos();
		modelAndView.addObject("escolas",escolas);
		modelAndView.addObject("escolaobj",new Escola());
		modelAndView.addObject("msg","Escola removido com sucesso !!");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST,value = "**/procurarnomedaescola")
	public ModelAndView procurarNome(@RequestParam("procurarnomeescola") String nome) {
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastroescola");
		modelAndView.addObject("escolas",escolaService.buscarNome(nome));
		modelAndView.addObject("escolaobj",new Escola());
		return modelAndView;
	}
	
}
