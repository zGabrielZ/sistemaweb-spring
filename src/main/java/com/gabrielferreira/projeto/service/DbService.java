package com.gabrielferreira.projeto.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.modelo.entidade.Estado;
import com.gabrielferreira.projeto.modelo.entidade.Sexo;
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.repositorio.EscolaRepositorio;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;
import com.gabrielferreira.projeto.repositorio.SexoRepositorio;
import com.gabrielferreira.projeto.repositorio.TipoTelefoneRepositorio;

@Service
public class DbService {
	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private EscolaRepositorio escolaRepositorio;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private TipoTelefoneRepositorio tipoTelefoneRepositorio;
	
	@Autowired
	private SexoRepositorio sexoRepositorio;

	public void instanciarTesteDatabase() {
		Estado estado1 = new Estado(null,"Acre"); 
		Estado estado2 = new Estado(null,"Alagoas"); 
		Estado estado3 = new Estado(null,"Amapá"); 
		Estado estado4 = new Estado(null,"Amazonas"); 
		Estado estado5 = new Estado(null,"Bahia"); 
		Estado estado6 = new Estado(null,"Ceará"); 
		Estado estado7 = new Estado(null,"Distrito Federal"); 
		Estado estado8 = new Estado(null,"Espírito Santo"); 
		Estado estado9 = new Estado(null,"Goiás"); 
		Estado estado10 = new Estado(null,"Maranhão"); 
		Estado estado11 = new Estado(null,"Mato Grosso"); 
		Estado estado12 = new Estado(null,"Mato Grosso do Sul"); 
		Estado estado13 = new Estado(null,"Minas Gerais"); 
		Estado estado14 = new Estado(null,"Pará"); 
		Estado estado15 = new Estado(null,"Paraiba"); 
		Estado estado16 = new Estado(null,"Paraná"); 
		Estado estado17 = new Estado(null,"Pernambuco"); 
		Estado estado18 = new Estado(null,"Piauí"); 
		Estado estado19 = new Estado(null,"Rio de Janeiro"); 
		Estado estado20 = new Estado(null,"Rio Grade do Norte"); 
		Estado estado21 = new Estado(null,"Rio Grade do Sul"); 
		Estado estado22 = new Estado(null,"Rondônia"); 
		Estado estado23 = new Estado(null,"Roraima"); 
		Estado estado24 = new Estado(null,"Santa Catarina"); 
		Estado estado25 = new Estado(null,"São Paulo"); 
		Estado estado26 = new Estado(null,"Sergipe"); 
		Estado estado27 = new Estado(null,"Tocantins"); 
		
		Sexo sexo1 = new Sexo(null,"Masculino");
		Sexo sexo2 = new Sexo(null,"Feminino");

		
		Escola escola1 = new Escola(null,"São Judas");
		Escola escola2 = new Escola(null,"PUC");
		Escola escola3 = new Escola(null,"FIAP");
		
		Disciplina disciplina1 = new Disciplina(null,"Cálculo 1");
		Disciplina disciplina2 = new Disciplina(null,"Programação");
		Disciplina disciplina3 = new Disciplina(null,"Física 1");
		
		Curso curso1 = new Curso(null,"Engenharia Da Computação");
		Curso curso2 = new Curso(null,"Engenharia Da Produção");
		Curso curso3 = new Curso(null,"Engenharia Civil");
		
		TipoTelefone tipoTelefone1 = new TipoTelefone(null,"Residencial");
		TipoTelefone tipoTelefone2 = new TipoTelefone(null,"Comercial");
		TipoTelefone tipoTelefone3 = new TipoTelefone(null,"Celular");
		
		estadoRepositorio.saveAll(Arrays.asList(estado1,estado2,estado3
				,estado4,estado5,estado6,estado7,estado8,estado9,estado10
				,estado11,estado12,estado13,estado14,estado15,estado16,estado17
				,estado18,estado19,estado20,estado21,estado22,estado23,estado24
				,estado25,estado26,estado27));
		
		tipoTelefoneRepositorio.saveAll(Arrays.asList(tipoTelefone1,tipoTelefone2,tipoTelefone3));	
		sexoRepositorio.saveAll(Arrays.asList(sexo1,sexo2));
		escolaRepositorio.saveAll(Arrays.asList(escola1,escola2,escola3));
		cursoRepositorio.saveAll(Arrays.asList(curso1,curso2,curso3));
		disciplinaRepositorio.saveAll(Arrays.asList(disciplina1,disciplina2,disciplina3));
	}
}
