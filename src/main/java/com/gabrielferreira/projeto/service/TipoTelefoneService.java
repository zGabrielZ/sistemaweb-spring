package com.gabrielferreira.projeto.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gabrielferreira.projeto.modelo.entidade.TipoTelefone;
import com.gabrielferreira.projeto.repositorio.TipoTelefoneRepositorio;

@Service
public class TipoTelefoneService {

	@Autowired
	private TipoTelefoneRepositorio tipoRepositorio;
	
	public List<TipoTelefone> consultarTodos(){
		return tipoRepositorio.findAll();
	}
	
}
