function limparTipoTelefone(){
	var codigo = document.getElementById('codigo');
	var descricao = document.getElementById('descricao');
	var sucesso = document.getElementById('sucesso');
	var erro = document.getElementById('erro');
	var errosValidacao = document.getElementById('errosValidacao');
	descricao.value = '';
	codigo.value = '';
	sucesso.innerHTML = '';
	erro.innerHTML = '';
	errosValidacao.innerHTML = '';
}