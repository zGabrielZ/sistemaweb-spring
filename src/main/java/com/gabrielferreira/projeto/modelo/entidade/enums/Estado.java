package com.gabrielferreira.projeto.modelo.entidade.enums;

public enum Estado {

	ACRE("Acre"),
	ALAGOAS("Alagoas"),
	AMAPA("Amapá"),
	AMAZONAS("Amazonas"),
	BAHIA("Bahia"),
	CEARA("Ceará"),
	DISTRITOFEDEREAL("Distrito Federal"),
	ESPIRITOSANTOS("Espírito Santo"),
	GOIAS("Goiás"),
	MARANHAO("Maranhão"),
	MATOGROSS("Mato Grosso"),
	MATOGROSSODOSUL("Mato Grosso do Sul"),
	MINASGERAIS("Minas Gerais"),
	PARA("Pará"),
	PARAIBA("Paraíba"),
	PARANA("Paraná"),
	PERNAMBUCO("Pernambuco"),
	PIAIU("Piauí"),
	RIODEJANEIRO("Rio de Janeiro"),
	RIOGRANDEDONORTE("Rio Grande do Norte"),
	RIOGRANDEDOSUL("Rio Grande do Sul"),
	RONDONIA("Rondônia"),
	RORAIMA("Roraima"),
	SANTACATARINA("Santa Catarina"),
	SAOPAULO("São Paulo"),
	SERGIPE("Sergipe"),
	TOCANTINS("Tocantins");
	
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
