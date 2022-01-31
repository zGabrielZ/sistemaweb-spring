package com.gabrielferreira.projeto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.modelo.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.enums.Sexo;
import com.gabrielferreira.projeto.modelo.entidade.enums.TipoPessoa;
import com.gabrielferreira.projeto.modelo.to.AlunoTo;
import com.gabrielferreira.projeto.modelo.to.PessoaTo;
import com.gabrielferreira.projeto.modelo.to.ProfessorTo;
import com.gabrielferreira.projeto.service.CursoService;
import com.gabrielferreira.projeto.service.EstadoService;
import com.gabrielferreira.projeto.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private static String PAG_CAD_PESSOA = "pessoa/cadastro-pessoa";
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping("/cadastro")
	public ModelAndView paginaInicialCadastroPessoa() {
		ModelAndView modelAndView = new ModelAndView(PAG_CAD_PESSOA);
		modelAndView.addObject("tipoPessoas",getTipoPessoas());
		return modelAndView;
	}
	
	@GetMapping("/verificar-tipo-pessoa/{idTipoPessoaSelecionado}")
	public ResponseEntity<Integer> tipoPessoaSelecionado(@PathVariable Integer idTipoPessoaSelecionado){
		return new ResponseEntity<Integer>(idTipoPessoaSelecionado,HttpStatus.OK);
	}
	
	@GetMapping("/cadastro/tipo-pessoa/{tipoPessoaSelecionado}")
	public ModelAndView pessoaSelecionadaCadastro(@PathVariable Integer tipoPessoaSelecionado) {
		ModelAndView modelAndView = new ModelAndView(PAG_CAD_PESSOA);
		
		if(tipoPessoaSelecionado.equals(TipoPessoa.ALUNO.getId())) {
			PessoaTo pessoaTo = new AlunoTo();
			AlunoTo alunoTo = (AlunoTo) pessoaTo;
			modelAndView.addObject("pessoa",alunoTo);
			modelAndView.addObject("alunoV",true);
		} else if(tipoPessoaSelecionado.equals(TipoPessoa.PROFESSOR.getId())) {
			PessoaTo pessoaTo = new ProfessorTo();
			ProfessorTo professorTo = (ProfessorTo) pessoaTo;
			modelAndView.addObject("pessoa",professorTo);
			modelAndView.addObject("professorV",true);
		} else {
			throw new EntidadeException("Tipo de pessoa selecionada inexistente");
		}
		
		modelAndView.addObject("estados", estadoService.getEstados());
		modelAndView.addObject("tipoPessoas",getTipoPessoas());
		modelAndView.addObject("sexos",getSexos());
		modelAndView.addObject("cursos",cursoService.getCursos());
		return modelAndView;
	}
	
	@PostMapping("/salvar-aluno")
	public ResponseEntity<PessoaTo> cadastrarAluno(@Valid @RequestBody AlunoTo alunoTo){
		Pessoa pessoa = pessoaService.cadastrarAluno(alunoTo);
		PessoaTo pessoaTo = new PessoaTo(pessoa);
		return new ResponseEntity<PessoaTo>(pessoaTo,HttpStatus.OK);
	}
	
	private TipoPessoa[] getTipoPessoas() {
		return TipoPessoa.values();
	}
	
	private Sexo[] getSexos() {
		return Sexo.values();
	}
}
