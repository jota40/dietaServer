package jota.server.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jota.server.dto.EscalaDeUnidadDeMedidaDto;
import jota.server.dto.UnidadDeMedidaDto;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="unidad_de_medida")
public class UnidadDeMedida extends Base implements EsAuditable {

	private String nombre;
	private String abreviacion;
	private String descripcion;
	private Set<EscalaDeUnidadDeMedida> escalasDeUnidadDeMedida;

	public UnidadDeMedida() {}

	@Column( name="nombre", nullable = false, unique = true )
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column( name="abreviacion", nullable = false, unique = true )
	public String getAbreviacion() {
		return abreviacion;
	}
	public void setAbreviacion(String abreviacion) {
		this.abreviacion = abreviacion;
	}

	@Column( name="descripcion" )
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion( String descripcion ) {
		this.descripcion = "".equals( descripcion ) ? null : descripcion;
	}

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "unidadDeMedida", cascade = CascadeType.ALL, orphanRemoval = true )
	public Set<EscalaDeUnidadDeMedida> getEscalasDeUnidadDeMedida() {
		return escalasDeUnidadDeMedida;
	}
	public void setEscalasDeUnidadDeMedida( Set<EscalaDeUnidadDeMedida> escalaDeUnidadDeMedida ) {
		this.escalasDeUnidadDeMedida = escalaDeUnidadDeMedida;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Auditable auditable;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_auditable_fk" )
	@ForeignKey( name ="UnidadDeMedida2Auditable")
	@Override
	public Auditable getAuditable() {
		return auditable;
	}
	@Override
	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public UnidadDeMedidaDto instanceDto() {
		List<EscalaDeUnidadDeMedidaDto> escalasDeUnidadDeMedidaDto = new ArrayList<EscalaDeUnidadDeMedidaDto>();
		for ( EscalaDeUnidadDeMedida escalaDeUnidadDeMedida : escalasDeUnidadDeMedida) {
			escalasDeUnidadDeMedidaDto.add( escalaDeUnidadDeMedida.instanceDto() );
		}
		return new UnidadDeMedidaDto( id, abreviacion, nombre, descripcion, escalasDeUnidadDeMedidaDto );
	}

	public void copyFrom( UnidadDeMedidaDto unidadDeMedidaDto ) {
		abreviacion = "".equals( unidadDeMedidaDto.getAbreviacion().trim() ) ? null : unidadDeMedidaDto.getAbreviacion();
		nombre = "".equals( unidadDeMedidaDto.getNombre().trim() ) ? null : unidadDeMedidaDto.getNombre();
		descripcion = "".equals( unidadDeMedidaDto.getDescripcion().trim() ) ? null : unidadDeMedidaDto.getDescripcion();
	}
}