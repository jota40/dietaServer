package jota.server.dto;

import java.math.BigDecimal;

import jota.server.entity.EscalaDeUnidadDeMedida;

public class IngredienteDeUsuarioDto extends IngredienteDto {

	private static final long serialVersionUID = -3312146371468281246L;

	private Long idUsuario;
	private BigDecimal coste;
	private EscalaDeUnidadDeMedida unidadDeCoste;

	public IngredienteDeUsuarioDto() {}

	public IngredienteDeUsuarioDto( IngredienteDto ingredienteDto, BigDecimal coste, EscalaDeUnidadDeMedidaDto unidadDeCoste ) {
	}
	
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}