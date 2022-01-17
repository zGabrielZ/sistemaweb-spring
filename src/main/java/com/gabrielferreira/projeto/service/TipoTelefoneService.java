package com.gabrielferreira.projeto.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielferreira.projeto.exception.EntidadeException;
import com.gabrielferreira.projeto.modelo.TipoTelefone;
import com.gabrielferreira.projeto.modelo.to.TipoTelefoneTo;
import com.gabrielferreira.projeto.repositorio.TipoTelefoneRepositorio;

@Service
public class TipoTelefoneService {

	@Autowired
	private TipoTelefoneRepositorio tipoTelefoneRepositorio;
	
	@Transactional
	public TipoTelefone inserir(TipoTelefoneTo tipoTelefoneTo) {
		TipoTelefone tipoTelefone = new TipoTelefoneTo().getTipoTelefone(tipoTelefoneTo);
		return tipoTelefoneRepositorio.save(tipoTelefone);
	}
	
	public List<TipoTelefoneTo> getTiposTelefones(){
		List<TipoTelefone> tipos = tipoTelefoneRepositorio.findAll();
		List<TipoTelefoneTo> tipoTelefoneTos = tipos.stream().map(t -> new TipoTelefoneTo(t)).collect(Collectors.toList());
		return tipoTelefoneTos;
	}
	
	public void deletar(Integer id) {
		TipoTelefone tipoTelefone = getTipoTelefone(id);
		tipoTelefoneRepositorio.deleteById(tipoTelefone.getId());
	}
	
	public TipoTelefone getTipoTelefone(Integer id) {
		Optional<TipoTelefone> optionalTipo = tipoTelefoneRepositorio.findById(id);
		if(!optionalTipo.isPresent()) {
			throw new EntidadeException("Tipo telefone não encontrado.");
		}
		return optionalTipo.get();
	}
}
