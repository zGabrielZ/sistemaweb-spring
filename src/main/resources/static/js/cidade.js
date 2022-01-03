function buscarCidade() {
	var idEstado = document.getElementById('estado').value;

	$.ajax({
		method: "GET",
		url: "/cidade/cidades-estado/" + idEstado,
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
		alert('Erro ao consultar estado' + xhr.responseText);
	})

}