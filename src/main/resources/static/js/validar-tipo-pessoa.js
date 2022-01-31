function validarTipoPessoa(){
	var tipoPessoa = document.getElementById('tipoPessoa').value;
	if(tipoPessoa === ''){
		alert('Selecione o tipo da pessoa');
		return false;
	}
	return true;
}