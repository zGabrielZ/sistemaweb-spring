function limparDisciplina(){
	var codigo = document.getElementById('codigo');
	var nome = document.getElementById('nome');
	var sucesso = document.getElementById('sucesso');
	var erro = document.getElementById('erro');
	var errosValidacao = document.getElementById('errosValidacao');
	nome.value = '';
	codigo.value = '';
	sucesso.innerHTML = '';
	erro.innerHTML = '';
	errosValidacao.innerHTML = '';
}