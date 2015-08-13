package jota.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jota.server.dto.EscalaDeUnidadDeMedidaDto;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="escala_de_unidad_de_medida")
public class EscalaDeUnidadDeMedida extends Base implements EsAuditable {

	private String nombre;
	private String abreviacion;
	private BigDecimal multiplicador = BigDecimal.ONE;
	private UnidadDeMedida unidadDeMedida;;

	public EscalaDeUnidadDeMedida() {}

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

	@Column( name="multiplicador", nullable = false )
	public BigDecimal getMultiplicador() {
		return multiplicador;
	}
	public void setMultiplicador( BigDecimal multiplicador ) {
		this.multiplicador = multiplicador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad_de_medida_fk", nullable = false)
	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Auditable auditable;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_auditable_fk" )
	@ForeignKey( name ="EscalaDeUnidadDeMedida2Auditable")
	@Override
	public Auditable getAuditable() {
		return auditable;
	}
	@Override
	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public EscalaDeUnidadDeMedidaDto instanceDto() {
		return new EscalaDeUnidadDeMedidaDto( id, abreviacion, nombre, multiplicador );
	}

	public void copyFrom( EscalaDeUnidadDeMedida escalaDeUnidadDeMedida ) {
		abreviacion = "".equals( escalaDeUnidadDeMedida.getAbreviacion().trim() ) ? null : escalaDeUnidadDeMedida.getAbreviacion();
		nombre = "".equals( escalaDeUnidadDeMedida.getNombre().trim() ) ? null : escalaDeUnidadDeMedida.getNombre();
		multiplicador = escalaDeUnidadDeMedida.getMultiplicador();
	}
}