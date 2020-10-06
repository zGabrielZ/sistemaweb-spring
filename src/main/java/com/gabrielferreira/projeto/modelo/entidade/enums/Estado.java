package com.gabrielferreira.projeto.modelo.entidade.enums;

public enum Estado {

	Acre("Acre"),
	Alagoas("Alagoas"),
	Amapa("Amapá"),
	Amazonas("Amazonas"),
	Bahia("Bahia"),
	Ceara("Ceará"),
	DistritoFederal("Distrito Federal"),
	EspiritoSantos("Espírito Santo"),
	Goias("Goiás"),
	Maranhao("Maranhão"),
	MatoGrosso("Mato Grosso"),
	MatoGrossoDoSul("Mato Grosso do Sul"),
	MinasGerias("Minas Gerais"),
	Para("Pará"),
	Paraiba("Paraíba"),
	PARANA("Paraná"),
	Pernambuco("Pernambuco"),
	Piaui("Piauí"),
	RioDeJaneiro("Rio de Janeiro"),
	RioGrandeDoNorte("Rio Grande do Norte"),
	RioGrandeDoSul("Rio Grande do Sul"),
	Rondonia("Rondônia"),
	Roraima("Roraima"),
	SantaCatarina("Santa Catarina"),
	SaoPaulo("São Paulo"),
	Sergipe("Sergipe"),
	Tocantins("Tocantins");
	
	private String descricao;
	
	Estado(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
