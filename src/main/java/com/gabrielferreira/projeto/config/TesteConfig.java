package com.gabrielferreira.projeto.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.gabrielferreira.projeto.modelo.entidade.Aluno;
import com.gabrielferreira.projeto.modelo.entidade.Cidade;
import com.gabrielferreira.projeto.modelo.entidade.Curso;
import com.gabrielferreira.projeto.modelo.entidade.Disciplina;
import com.gabrielferreira.projeto.modelo.entidade.Endereco;
import com.gabrielferreira.projeto.modelo.entidade.Escola;
import com.gabrielferreira.projeto.modelo.entidade.Estado;
import com.gabrielferreira.projeto.modelo.entidade.Pessoa;
import com.gabrielferreira.projeto.modelo.entidade.Professor;
import com.gabrielferreira.projeto.modelo.entidade.Sexo;
import com.gabrielferreira.projeto.modelo.entidade.Telefone;
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.repositorio.AlunoRepositorio;
import com.gabrielferreira.projeto.repositorio.CidadeRepositorio;
import com.gabrielferreira.projeto.repositorio.CursoRepositorio;
import com.gabrielferreira.projeto.repositorio.DisciplinaRepositorio;
import com.gabrielferreira.projeto.repositorio.EnderecoRepositorio;
import com.gabrielferreira.projeto.repositorio.EscolaRepositorio;
import com.gabrielferreira.projeto.repositorio.EstadoRepositorio;
import com.gabrielferreira.projeto.repositorio.PessoaRepositorio;
import com.gabrielferreira.projeto.repositorio.ProfessorRepositorio;
import com.gabrielferreira.projeto.repositorio.SexoRepositorio;
import com.gabrielferreira.projeto.repositorio.TelefoneRepositorio;
import com.gabrielferreira.projeto.repositorio.TipoTelefoneRepositorio;


@Configuration
public class TesteConfig implements CommandLineRunner{

	
	@Autowired
	private EstadoRepositorio estadoRepositorio;
	
	@Autowired
	private CidadeRepositorio cidadeRepositorio;
	
	@Autowired
	private PessoaRepositorio pessoaRepositorio;
	
	@Autowired
	private AlunoRepositorio alunoRepositorio;
	
	@Autowired
	private ProfessorRepositorio professorRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	@Autowired
	private EscolaRepositorio escolaRepositorio;
	
	@Autowired
	private DisciplinaRepositorio disciplinaRepositorio;
	
	@Autowired
	private TelefoneRepositorio telefoneRepositorio;
	
	@Autowired
	private TipoTelefoneRepositorio tipoTelefoneRepositorio;
	
	@Autowired
	private SexoRepositorio sexoRepositorio;
	
	@Override
	public void run(String... args) throws Exception {

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
		
		Cidade cidade1 = new Cidade(null,"São Paulo",estado25);
		Cidade cidade2 = new Cidade(null,"Campinas",estado25);
		Cidade cidade3 = new Cidade(null,"Ouro Preto",estado13);
		
		Sexo sexo1 = new Sexo(null,"Masculino");
		Sexo sexo2 = new Sexo(null,"Feminino");
		
		estado25.getCidades().addAll(Arrays.asList(cidade1,cidade2));
		estado13.getCidades().addAll(Arrays.asList(cidade3));
		
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
		
		Telefone telefone1 = new Telefone(null,"123",tipoTelefone1);
		Telefone telefone2 = new Telefone(null,"1233",tipoTelefone3);
		
		Telefone telefone3 = new Telefone(null,"909090",tipoTelefone1);
		Telefone telefone4 = new Telefone(null,"70070",tipoTelefone2);
		
		Telefone telefone5 = new Telefone(null,"777",tipoTelefone3);
		Telefone telefone6 = new Telefone(null,"000",tipoTelefone3);
		
		Telefone telefone7 = new Telefone(null,"888",tipoTelefone2);
		Telefone telefone8 = new Telefone(null,"1111",tipoTelefone2);
		
		tipoTelefone1.getTelefones().addAll(Arrays.asList(telefone1,telefone3));
		tipoTelefone2.getTelefones().addAll(Arrays.asList(telefone4,telefone7,telefone8));
		tipoTelefone3.getTelefones().addAll(Arrays.asList(telefone2,telefone5,telefone6));
		
		Pessoa pessoa1 = new Aluno(null,"Gabriel","Ferreira","123",sexo1,
				curso1,escola1,123,"1222");
		Pessoa pessoa2 = new Aluno(null,"João","Pereira","123456",sexo2,
			curso1,escola1,112222,"0909090");
		
		Aluno aluno1 = (Aluno) pessoa1;
		Aluno aluno2 = (Aluno) pessoa2;
		
		Pessoa pessoa3 = new Professor(null,"Gabriel","Ferreira","123321",sexo2,
				curso2,escola2,2000.00);
		Pessoa pessoa4 = new Professor(null,"João","Pereira","12999",sexo2,
			curso3,escola3,2000.00);
		
		Professor professor1 = (Professor) pessoa3;
		Professor professor2 = (Professor) pessoa4;
		
		sexo1.getPessoa().addAll(Arrays.asList(aluno1));
		sexo2.getPessoa().addAll(Arrays.asList(aluno2,professor1,professor2));
		Endereco endereco1 = new Endereco(null,"bla","bla","bla","bla","bla",cidade3);
		Endereco endereco2 = new Endereco(null,"111","111","111","111","122",cidade2);
		Endereco endereco3 = new Endereco(null,"111","111","111","111","122",cidade1);
		Endereco endereco4 = new Endereco(null,"111","111","111","111","122",cidade1);
			
		aluno1.setEndereco(endereco1);
		endereco1.setPessoa(aluno1);
		
		aluno2.setEndereco(endereco2);
		endereco2.setPessoa(aluno2);
		
		professor1.setEndereco(endereco3);
		endereco3.setPessoa(professor1);
		
		professor2.setEndereco(endereco4);
		endereco4.setPessoa(professor2);
		
		curso1.getPessoas().addAll(Arrays.asList(aluno1,aluno2));
		curso2.getPessoas().add(professor1);
		curso3.getPessoas().add(professor2);
		
		escola1.getPessoas().addAll(Arrays.asList(aluno1,aluno2));
		escola2.getPessoas().addAll(Arrays.asList(professor1));
		escola3.getPessoas().addAll(Arrays.asList(professor2));
		
		aluno1.getDisciplinas().addAll(Arrays.asList(disciplina1,disciplina2));
		aluno2.getDisciplinas().addAll(Arrays.asList(disciplina3));
		professor1.getDisciplinas().addAll(Arrays.asList(disciplina1,disciplina2,
				disciplina3));
		professor2.getDisciplinas().addAll(Arrays.asList(disciplina3));
		
		disciplina1.getPessoas().addAll(Arrays.asList(aluno1,professor1));
		disciplina2.getPessoas().addAll(Arrays.asList(aluno1,professor1));
		disciplina3.getPessoas().addAll(Arrays.asList(aluno2,professor1,professor2));
		
		
		telefone1.setPessoa(aluno1);
		telefone2.setPessoa(aluno1);
		
		telefone3.setPessoa(aluno2);
		telefone4.setPessoa(aluno2);
		
		telefone5.setPessoa(professor1);
		telefone6.setPessoa(professor1);
		
		telefone7.setPessoa(professor2);
		telefone8.setPessoa(professor2);
		
		aluno1.getTelefones().addAll(Arrays.asList(telefone1,telefone2));
		aluno2.getTelefones().addAll(Arrays.asList(telefone3,telefone4));
		professor1.getTelefones().addAll(Arrays.asList(telefone5,telefone6));
		professor2.getTelefones().addAll(Arrays.asList(telefone7,telefone8));
		

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
		pessoaRepositorio.saveAll(Arrays.asList(pessoa1,pessoa2,pessoa3,pessoa4));
		alunoRepositorio.saveAll(Arrays.asList(aluno1,aluno2));
		professorRepositorio.saveAll(Arrays.asList(professor1,professor2));
		enderecoRepositorio.saveAll(Arrays.asList(endereco1,endereco2,endereco3,endereco4));
		cidadeRepositorio.saveAll(Arrays.asList(cidade1,cidade2,cidade3));	
		telefoneRepositorio.saveAll(Arrays.asList(telefone1,telefone2,telefone3,
				telefone4,telefone5,telefone6,telefone7,telefone8));
	}

}
