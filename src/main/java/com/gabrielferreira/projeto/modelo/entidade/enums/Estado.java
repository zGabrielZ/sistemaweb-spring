package com.gabrielferreira.projeto.modelo.entidade.enums;

import com.gabrielferreira.projeto.service.exceptions.RegraDeNegocioException;

public enum Estado {

	Acre(1,"Acre"),
	Alagoas(2,"Alagoas"),
	Amapa(3,"Amapá"),
	Amazonas(4,"Amazonas"),
	Bahia(5,"Bahia"),
	Ceara(6,"Ceará"),
	DistritoFederal(7,"Distrito Federal"),
	EspiritoSantos(8,"Espírito Santo"),
	Goias(9,"Goiás"),
	Maranhao(10,"Maranhão"),
	MatoGrosso(11,"Mato Grosso"),
	MatoGrossoDoSul(12,"Mato Grosso do Sul"),
	MinasGerias(13,"Minas Gerais"),
	Para(14,"Pará"),
	Paraiba(15,"Paraíba"),
	PARANA(16,"Paraná"),
	Pernambuco(17,"Pernambuco"),
	Piaui(18,"Piauí"),
	RioDeJaneiro(19,"Rio de Janeiro"),
	RioGrandeDoNorte(20,"Rio Grande do Norte"),
	RioGrandeDoSul(21,"Rio Grande do Sul"),
	Rondonia(22,"Rondônia"),
	Roraima(23,"Roraima"),
	SantaCatarina(24,"Santa Catarina"),
	SaoPaulo(25,"São Paulo"),
	Sergipe(26,"Sergipe"),
	Tocantins(27,"Tocantins");
	
	private int codigo;
	private String descricao;
	
	Estado(int codigo,String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static Estado converterParaEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(Estado estado : Estado.values()) {
			if(codigo.equals(estado.getCodigo())) {
				return estado;
			}
		}
		
		throw new RegraDeNegocioException("Estado não encontrado");
	}
}
