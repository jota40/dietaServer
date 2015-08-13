package jota.server.entity;

import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jota.server.dto.IngredienteDeRecetaDto;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="ingrediente_de_receta")
@AssociationOverrides({
	@AssociationOverride( name = "pk.receta", joinColumns = @JoinColumn( name = "id_receta_fk" ) ),
	@AssociationOverride( name = "pk.ingrediente_de_usuario", joinColumns = @JoinColumn( name = "id_ingrediente_de_usuario_fk" ) )
})

public class IngredienteDeReceta {
	private IngredienteDeRecetaId pk = new IngredienteDeRecetaId();
	private BigDecimal cantidadPorComensal;
	private EscalaDeUnidadDeMedida unidad;

	public IngredienteDeReceta() {}

	public IngredienteDeReceta( IngredienteDeUsuario ingredienteDeUsuario, Receta receta, IngredienteDeRecetaDto ingredienteDeRecetaDto ) {
		this.pk.setIngredienteDeUsuario( ingredienteDeUsuario );
		this.pk.setReceta( receta );
		this.cantidadPorComensal = ingredienteDeRecetaDto.getCantidad().divide( new BigDecimal( receta.getPax() ) );
	}

	@EmbeddedId
	public IngredienteDeRecetaId getPk() {
		return pk;
	}
	public void setPk(IngredienteDeRecetaId pk) {
		this.pk = pk;
	}

	@Column( name="cantidad_por_comensal", nullable = false, precision = 25, scale = 8 )
	public BigDecimal getCantidadPorComensal() {
		return cantidadPorComensal;
	}
	public void setCantidadPorComensal(BigDecimal cantidadPorComensal) {
		this.cantidadPorComensal = cantidadPorComensal;
	}


	public IngredienteDeRecetaDto instanceDto() {
		return new IngredienteDeRecetaDto( pk.getIngredienteDeUsuario().instanceDto(), pk.getReceta().getPax(), cantidadPorComensal, unidad.instanceDto() );
	}

	@ManyToOne
	@JoinColumn( name = "id_escala_unidad_medida_fk", nullable = false )
	@ForeignKey( name ="IngredienteDeReceta2EscalaDeUnidadDeMedida")
	public EscalaDeUnidadDeMedida getUnidad() {
		return unidad;
	}
	public void setUnidad(EscalaDeUnidadDeMedida unidad) {
		this.unidad = unidad;
	}
}