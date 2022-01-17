package com.gabrielferreira.projeto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabrielferreira.projeto.api.exception.ErroPadrao;
import com.gabrielferreira.projeto.exception.RegraException;
import com.gabrielferreira.projeto.modelo.TipoTelefone;
import com.gabrielferreira.projeto.modelo.to.TipoTelefoneTo;
import com.gabrielferreira.projeto.service.TipoTelefoneService;

@Controller
@RequestMapping("/tipo-telefone")
public class TipoTelefoneController {

	private static String PAG_CADASTRO_TIPO_TELEFONE = "tipo-telefone/cadastro-tipo-telefone";
	private static String PAG_CONSULTA_TIPO_TELEFONE = "tipo-telefone/consulta-tipo-telefone";
	
	@Autowired
	private TipoTelefoneService tipoTelefoneService;

	@GetMapping("/cadastro")
	public ModelAndView paginaCadastroTipoTelefone() {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_TIPO_TELEFONE);
		modelAndView.addObject("tipoTelefone",new TipoTelefoneTo());
		return modelAndView;
	}
	
	@GetMapping("/consulta")
	public ModelAndView consultaTiposTelefones() {
		ModelAndView modelAndView = new ModelAndView(PAG_CONSULTA_TIPO_TELEFONE);
		List<TipoTelefoneTo> tipoTelefoneTos = tipoTelefoneService.getTiposTelefones();
		modelAndView.addObject("tiposTelefones",tipoTelefoneTos);
		if(tipoTelefoneTos.isEmpty()) {
			modelAndView.addObject("msgTiposTelefones","Nenhum registro encontrado.");
		}
		return modelAndView;
	}
	
	@PostMapping("/consultar-tipo-telefone")
	public ModelAndView consultarTipoTelefonesDescricao(String descricaoTipoTelefone) {
		ModelAndView modelAndView = new ModelAndView(PAG_CONSULTA_TIPO_TELEFONE);
		List<TipoTelefoneTo> tipoTelefoneTos = tipoTelefoneService.getTiposTelefonesPorDescricao(descricaoTipoTelefone);
		modelAndView.addObject("tiposTelefones",tipoTelefoneTos);
		if(tipoTelefoneTos.isEmpty()) {
			modelAndView.addObject("msgTiposTelefones","Nenhum registro encontrado.");
		}
		return modelAndView;
	}
	
	@GetMapping("/editar-tipo-telefone/{idTipoTelefone}")
	public ModelAndView editarTipoTelefone(@PathVariable Integer idTipoTelefone) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_TIPO_TELEFONE);
		TipoTelefone tipoTelefone = tipoTelefoneService.getTipoTelefone(idTipoTelefone);
		TipoTelefoneTo tipoTelefoneTo = new TipoTelefoneTo(tipoTelefone);
		modelAndView.addObject("tipoTelefone",tipoTelefoneTo);
		return modelAndView;
	}
	
	@GetMapping("/deletar-tipo-telefone/{idTipoTelefone}")
	public ModelAndView deletarCurso(@PathVariable Integer idTipoTelefone, RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/tipo-telefone/consulta");
		tipoTelefoneService.deletar(idTipoTelefone);
		redirectAttributes.addFlashAttribute("msgTiposTelefonesDelete","Tipo Telefone deletado com sucesso.");
		return modelAndView;
	}
	
	@PostMapping("/salvar-tipo-telefone")
	public ModelAndView cadastrarTipoTelefone(@Valid TipoTelefoneTo tipoTelefoneTo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		ModelAndView modelAndView = new ModelAndView("redirect:cadastro");
		
		if(bindingResult.hasErrors()) {
			return verificarValidacaoTipoTelefone(tipoTelefoneTo, bindingResult);
		}
	
		try {
			
			Integer idTipoTelefone = tipoTelefoneTo.getId();
			tipoTelefoneService.inserir(tipoTelefoneTo);
			
			if(idTipoTelefone == null) {
				redirectAttributes.addFlashAttribute("sucesso",tipoTelefoneTo.getDescricao() + " cadastrada com sucesso !");
			} else {
				redirectAttributes.addFlashAttribute("sucesso",tipoTelefoneTo.getDescricao() + " atualizada com sucesso !");
			}
		
		} catch (RegraException e) {
			redirectAttributes.addFlashAttribute("erro",e.getMessage());
		}
		
		return modelAndView;
	}
	
	private ModelAndView verificarValidacaoTipoTelefone(TipoTelefoneTo tipoTelefoneTo, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView(PAG_CADASTRO_TIPO_TELEFONE);
		modelAndView.addObject("tipoTelefone", tipoTelefoneTo);
		List<ErroPadrao> erroPadraos = new ArrayList<>();
		for(ObjectError objectError :bindingResult.getAllErrors()) {
			erroPadraos.add(new ErroPadrao(objectError.getDefaultMessage()));
		}
		modelAndView.addObject("errosValidacao",erroPadraos);
		return modelAndView;
	}
	
	
	
}
