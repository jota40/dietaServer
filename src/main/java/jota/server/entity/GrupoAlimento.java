package jota.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jota.server.dto.GrupoAlimentoDto;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="grupo_alimento")
public class GrupoAlimento extends Base implements EsAuditable {

	private String nombre;
	private String descripcion;
	private String color;

	public GrupoAlimento() {}

	@Column( name="nombre", nullable = false, unique = true )
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column( name="descripcion" )
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion( String descripcion ) {
		this.descripcion = "".equals( descripcion ) ? null : descripcion;
	}

	@Column( name="color" )
	public String getColor() {
		return color;
	}
	public void setColor( String color ) {
		this.color = "".equals( color ) ? "FFFFFF" : color;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Auditable auditable;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_auditable_fk" )
	@ForeignKey( name ="GrupoAlimento2Auditable")
	@Override
	public Auditable getAuditable() {
		return auditable;
	}
	@Override
	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public GrupoAlimentoDto instanceDto() {
		return new GrupoAlimentoDto( id, nombre, descripcion, color );
	}

	public void copyFrom( GrupoAlimentoDto grupoAlimentoDto ) {
		nombre = "".equals( grupoAlimentoDto.getNombre().trim() ) ? null : grupoAlimentoDto.getNombre();
		descripcion = "".equals( grupoAlimentoDto.getDescripcion().trim() ) ? null : grupoAlimentoDto.getDescripcion();
		color = "".equals( grupoAlimentoDto.getColor().trim() ) ? null : grupoAlimentoDto.getColor();
	}

}