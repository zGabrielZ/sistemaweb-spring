package com.gabrielferreira.projeto.modelo.to;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import com.gabrielferreira.projeto.modelo.TipoTelefone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TipoTelefoneTo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "Descrição não pode ser vazio")
	@Size(min=5, max = 150, message="O campo descrição deve ter no mínimo 5 até 150 caracteres")
	private String descricao;
	
	public TipoTelefoneTo(TipoTelefone tipoTelefone) {
		this.id = tipoTelefone.getId();
		this.descricao = tipoTelefone.getDescricao();
	}
	
	public TipoTelefone getTipoTelefone(TipoTelefoneTo tipoTelefoneTo) {
		TipoTelefone tipoTelefone = new TipoTelefone();
		tipoTelefone.setId(tipoTelefoneTo.getId());
		tipoTelefone.setDescricao(tipoTelefoneTo.getDescricao());
		return tipoTelefone;
	}
}
