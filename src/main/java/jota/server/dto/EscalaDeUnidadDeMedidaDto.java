package jota.server.dto;

import java.math.BigDecimal;


public class EscalaDeUnidadDeMedidaDto extends BaseDto {

	private static final long serialVersionUID = -3312146371468281246L;

	private String abreviacion;
	private String nombre;
	private BigDecimal multiplicador;

	public EscalaDeUnidadDeMedidaDto() {}

	public EscalaDeUnidadDeMedidaDto( Long id, String abreviacion, String nombre, BigDecimal multiplicador ) {
		super();
		this.id = id;
		this.abreviacion = abreviacion == null ? "" : abreviacion;
		this.nombre = nombre == null ? "" : nombre;
		this.multiplicador = multiplicador;
	}

	public String getAbreviacion() {
		return abreviacion;
	}
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getMultiplicador() {
		return multiplicador;
	}
	public void setMultiplicador(BigDecimal multiplicador) {
		this.multiplicador = multiplicador;
	}
}