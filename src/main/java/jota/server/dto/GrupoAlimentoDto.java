package jota.server.dto;


public class GrupoAlimentoDto extends BaseDto {

	private static final long serialVersionUID = -8849911361132816547L;

	private String nombre;
	private String descripcion;
	private String color;

	public GrupoAlimentoDto() {}

	public GrupoAlimentoDto( Long id, String nombre, String descripcion, String color ) {
		this.id = id;
		this.nombre = nombre == null ? "" : nombre;
		this.descripcion = descripcion == null ? "" : descripcion;
		this.color = color == null ? "" : color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}