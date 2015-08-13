package jota.server.dto;

import java.math.BigDecimal;

public class IngredienteDto extends BaseDto {

	private static final long serialVersionUID = -1828712086534029437L;

	private String nombre;
	private String descripcion;
	private BigDecimal pesoPorRacion;
	private UnidadDeMedidaDto unidadDeMedida;
	private GrupoAlimentoDto grupoAlimento;

	public IngredienteDto() {}

	public IngredienteDto( Long id, String nombre, String descripcion, BigDecimal pesoPorRacion, UnidadDeMedidaDto unidadDeMedida, GrupoAlimentoDto grupoAlimento ) {
		this.id = id;
		this.nombre = nombre == null ? "" : nombre;
		this.descripcion = descripcion == null ? "" : descripcion;
		this.pesoPorRacion = pesoPorRacion == null ? BigDecimal.ZERO : pesoPorRacion;
		this.unidadDeMedida = unidadDeMedida;
		this.grupoAlimento = grupoAlimento;
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

	public BigDecimal getPesoPorRacion() {
		return pesoPorRacion;
	}

	public void setPesoPorRacion( BigDecimal pesoPorRacion ) {
		this.pesoPorRacion = pesoPorRacion;
	}

	public UnidadDeMedidaDto getUnidadDeMedida() {
		return unidadDeMedida;
	}

	public void setUnidadDeMedida( UnidadDeMedidaDto unidadDeMedida ) {
		this.unidadDeMedida = unidadDeMedida;
	}

	public GrupoAlimentoDto getGrupoAlimento() {
		return grupoAlimento;
	}

	public void setGrupoAlimento(GrupoAlimentoDto grupoAlimento) {
		this.grupoAlimento = grupoAlimento;
	}
}