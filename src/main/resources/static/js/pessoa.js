// Mascara para pessoa
$(document).ready(function() {
	$('#cpf').mask("000.000.000-00");
	$('#nascimento').mask("00/00/0000");
	$('#ra').mask("0000000000");
	$('#cep').mask("00000-000");
	$('#salario').mask("999.999.990,00",{reverse:true});
});

// Função para o cadastro da pessoa
function cadastrarPessoa(){
	var tipoAluno = $("#tipo-aluno").val();
	if(tipoAluno != undefined){
		cadastrarAluno();
	} else {
		console.log('é professor')
	}
}

// Função para o cadastro de aluno -> cria um js para essa funcao de post, delete, put e get
function cadastrarAluno() {
	$(document).ready(function() {
		$("#frmCadastroPessoa").submit(function(e) {
			e.preventDefault();

			$.ajax({
				method: "POST",
				url: "/pessoa/salvar-aluno",
				data: JSON.stringify({
					nome: obterCampos().nome,
					sobrenome: obterCampos().sobrenome,
					cpf: obterCampos().cpf,
					dataNascimento: obterCampos().nascimento,
					sexo: obterCampos().sexo,
					idCurso: obterCampos().curso.id,
					ra: obterCampos().ra,
					cep: obterCampos().endereco.cep,
					logradouro: obterCampos().endereco.logradouro,
					complemento: obterCampos().endereco.complemento,
					numero: obterCampos().endereco.numero,
					bairro: obterCampos().endereco.bairro,
					idCidade: obterCampos().endereco.cidade,
					idEstado: obterCampos().endereco.estado
				}),
				contentType: "application/json; charset=utf-8",
				success: function(response) {
					alert('Salvo com sucesso !');
					limparPessoa();
					$('#msgsSalvo').html("<p style='color:blue;' > Cadastrado com sucesso o aluno  " + response.nome + " !" + "</p>");
					$('#msgsErros').html("");
				}
			}).fail(function(xhr) {
				var msgs = "";
				alert('Mensagem : ' + xhr.responseJSON.mensagem);
				for (var i = 0; i < xhr.responseJSON.campos.length; i++) {
					msgs += "<p style='color:red;' > " + (xhr.responseJSON.campos[i].mensagem) + "</p>"
				}
				$('#msgsErros').html(msgs);
			})

		})
	});
}

// Função para obter campos do html
function obterCampos(){
	var pessoa = new Object();
	pessoa.nome =  $("#nome").val();
	pessoa.sobrenome = $("#sobrenome").val();
	pessoa.cpf = $("#cpf").val();
	pessoa.sexo = $("#sexo").val();
	pessoa.nascimento = $("#nascimento").val();
	
	var curso = new Object();
	curso.id = $("#curso").val();
	pessoa.curso = curso;
	
	var endereco = new Object();
	endereco.cep = $("#cep").val();
	endereco.logradouro = $("#logradouro").val();
	endereco.complemento = $("#complemento").val();
	endereco.bairro = $("#bairro").val();
	endereco.numero = $("#numero").val();
	endereco.estado = $("#estado").val();
	endereco.cidade = $("#cidade").val();
	pessoa.endereco = endereco;
	pessoa.ra = $("#ra").val();
	pessoa.salario = $("#salario").val();;
	
	return pessoa; 
}

// Função para limpar o formulario
function limparPessoa(){
	var codigoPessoa = document.getElementById('codigo-pessoa');
	var nome = document.getElementById('nome');
	var sobrenome = document.getElementById('sobrenome');
	var cpf = document.getElementById('cpf');
	var sexo = document.getElementById('sexo');
	var nascimento = document.getElementById('nascimento');
	var curso = document.getElementById('curso');
	var ra = document.getElementById('ra');
	var salario = document.getElementById('salario');
	var codigoEndereco = document.getElementById('codigo-endereco');
	var cep = document.getElementById('cep');
	var logradouro = document.getElementById('logradouro');
	var complemento = document.getElementById('complemento');
	var bairro = document.getElementById('bairro');
	var numero = document.getElementById('numero');
	var estado = document.getElementById('estado');
	var cidade = document.getElementById('cidade');
	var msgsErros = document.getElementById('msgsErros')
	
	if(ra != null){
		ra.value = '';
	}	
	if(salario != null){
		salario.value = '';	
	}
	
	codigoPessoa.value = '';
	nome.value = '';
	sobrenome.value = '';
	cpf.value = '';
	sexo.value = '';
	nascimento.value = '';
	curso.value = '';
	codigoEndereco.value = '';
	cep.value = '';
	logradouro.value = '';
	complemento.value = '';
	bairro.value = '';
	estado.value = '';
	cidade.value = '';
	numero.value = '';
	msgsErros.innerHTML = '';
	$('select').formSelect(); // para aparecer o select no materialize, é necessário inserir o formSelect
}