function buscarTipoPessoa(){
	var tipoPessoaSelecionada = document.getElementById('tipoPessoa').value;
	if (tipoPessoaSelecionada != null) {
		$.ajax({
			method: "GET",
			url: "/pessoa/verificar-tipo-pessoa/" + tipoPessoaSelecionada,
			data: "",
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				if (response != null) {
					window.location = "/pessoa/cadastro/tipo-pessoa/" + tipoPessoaSelecionada;
				}
			}
		}).fail(function(xhr) {
			alert('Erro ao consultar' + xhr.responseText);
		})
	}
}