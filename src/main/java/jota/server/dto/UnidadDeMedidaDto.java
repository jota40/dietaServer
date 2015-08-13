package jota.server.dto;

import java.util.List;


public class UnidadDeMedidaDto extends BaseDto {

	private static final long serialVersionUID = -3312146371468281246L;

	private String abreviacion;
	private String nombre;
	private String descripcion;
	private List<EscalaDeUnidadDeMedidaDto> escalasDeUnidadDeMedidaDto;

	public UnidadDeMedidaDto() {}

	public UnidadDeMedidaDto( Long id, String abreviacion, String nombre, String descripcion, List<EscalaDeUnidadDeMedidaDto> escalasDeUnidadDeMedidaDto ) {
		super();
		this.id = id;
		this.abreviacion = abreviacion == null ? "" : abreviacion;
		this.nombre = nombre == null ? "" : nombre;
		this.descripcion = descripcion == null ? "" : descripcion;
		this.escalasDeUnidadDeMedidaDto = escalasDeUnidadDeMedidaDto;
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

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EscalaDeUnidadDeMedidaDto> getEscalasDeUnidadDeMedidaDto() {
		return escalasDeUnidadDeMedidaDto;
	}
	public void setEscalasDeUnidadDeMedidaDto( List<EscalaDeUnidadDeMedidaDto> escalasDeUnidadDeMedidaDto ) {
		this.escalasDeUnidadDeMedidaDto = escalasDeUnidadDeMedidaDto;
	}
}