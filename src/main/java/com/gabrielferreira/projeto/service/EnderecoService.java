package com.gabrielferreira.projeto.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielferreira.projeto.exception.CepException;
import com.gabrielferreira.projeto.modelo.Estado;
import com.gabrielferreira.projeto.modelo.to.consulta.ConsultaEnderecoTo;
import com.google.gson.Gson;

@Service
public class EnderecoService {
	
	@Autowired
	private EstadoService estadoService;

	public ConsultaEnderecoTo buscarCep(String cep) throws IOException {
		// Fazer o consumo do webservice do via cep
		String urlWebService = "https://viacep.com.br/ws/"+cep+"/json/"; 
		URL url = new URL(urlWebService);
		HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
		
		if(conexao.getResponseCode() != 200) {
			throw new CepException("Não é possível seguir em frente com este cep.");
		}
		
		BufferedReader rb = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String linha = null;
		while((linha = rb.readLine()) != null) {
			sb.append(linha);
		}
		
		if(sb.toString().contains("erro")) {
			throw new CepException("Não é possível seguir em frente com esse cep, pois não é válido.");
		}
		
		ConsultaEnderecoTo endereco = new Gson().fromJson(sb.toString(), ConsultaEnderecoTo.class);
		
		Estado estado = verificarUfEstado(endereco.getUf());
		endereco.setIdEstado(estado.getId());
		
		rb.close();
		conexao.disconnect();
		
		return endereco;
	}
	
	public Estado verificarUfEstado(String uf) {
		List<Estado> estados = estadoService.getEstados();
		List<Estado> estado = estados.stream().filter(e -> e.getSigla().equals(uf)).collect(Collectors.toList());
		return estado.get(0);
	}
}
