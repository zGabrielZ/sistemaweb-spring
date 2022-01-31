function buscarCep(){
	
	var cep = document.getElementById('cep').value;
	var logradouro = document.getElementById('logradouro');
	var complemento = document.getElementById('complemento');
	var bairro = document.getElementById('bairro');
	var estado = document.getElementById('estado');
	var cidade = document.getElementById('cidade');
	
	if (cep != null) {
		$.ajax({
			method: "GET",
			url: "/endereco/buscar-cep/" + cep,
			data: "",
			contentType: "application/json; charset=utf-8",
			success: function(response) {
				logradouro.value = response.logradouro;
				complemento.value = response.complemento;
				bairro.value = response.bairro;
				estado.value = response.idEstado;
				$('select').formSelect(); // para aparecer o select no materialize, é necessário inserir o formSelect
				buscarCidadesPorEstado(estado.value);
			}
		}).fail(function(xhr) {
			logradouro.value = '';
			complemento.value = '';
			bairro.value = '';
			estado.value = '';
			cidade.value = '';
			$('select').formSelect(); // para aparecer o select no materialize, é necessário inserir o formSelect
			alert('Mensagem : ' + xhr.responseJSON.mensagem + ' - Data : ' + xhr.responseJSON.data);
		})
	}
}

function buscarCidadesPorEstado(estado) {
	$.ajax({
		method: "GET",
		url: "/cidade/cidades-estado/" + estado,
		data: "",
		contentType: "application/json; charset=utf-8",
		success: function(response) {
			var items = "";
			for (var i = 0; i < response.length; i++) {
				items += "<option value='" + response[i].id + "'>" + response[i].nome + "</option>";
			}
			$('#cidade').html(items);
			$('select').formSelect(); // para aparecer o select no materialize, é necessário inserir o formSelect

		}
	}).fail(function(xhr) {
		alert('Mensagem : ' + xhr.responseJSON.mensagem + ' - Data : ' + xhr.responseJSON.data);
	})
}