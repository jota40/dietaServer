package jota.server.dto;

import java.util.List;

public class RecetaDto extends BaseDto {

	private static final long serialVersionUID = -3312146371468281246L;

	private String nombre;
	private String descripcion;
	private Integer pax;
	private List<IngredienteDeRecetaDto> ingredientesDeRecetaDto;

	public RecetaDto() {}

	public RecetaDto( Long id, String nombre, String descripcion, Integer pax, List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) {
		this.id = id;
		this.nombre = nombre == null ? "" : nombre;
		this.descripcion = descripcion == null ? "" : descripcion;
		this.pax = pax == null ? 0 : pax;
		this.ingredientesDeRecetaDto = ingredientesDeRecetaDto;
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

	public Integer getPax() {
		return pax;
	}
	public void setPax( Integer pax ) {
		this.pax = pax;
	}

	public List<IngredienteDeRecetaDto> getIngredientesDeRecetaDto() {
		return ingredientesDeRecetaDto;
	}
	public void setIngredientesDeRecetaDto( List<IngredienteDeRecetaDto> ingredientesDeRecetaDto ) {
		this.ingredientesDeRecetaDto = ingredientesDeRecetaDto;
	}
}