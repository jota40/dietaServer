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

import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.dto.IngredienteDeUsuarioDto;
import jota.server.enumerations.RolEnum;
import jota.server.exceptions.PermisoAccesoException;
import jota.server.utils.SecurityUtils;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="ingrediente_de_usuario")
@AssociationOverrides({
	@AssociationOverride( name = "pk.usuario", joinColumns = @JoinColumn( name = "id_usuario_fk" ) ),
	@AssociationOverride( name = "pk.ingrediente", joinColumns = @JoinColumn( name = "id_ingrediente_fk" ) )
})

public class IngredienteDeUsuario implements ICheckPermisos {
	private IngredienteDeUsuarioId pk = new IngredienteDeUsuarioId();
	private EscalaDeUnidadDeMedida unidad;
	private BigDecimal coste;

	public IngredienteDeUsuario() {}

	@EmbeddedId
	public IngredienteDeUsuarioId getPk() {
		return pk;
	}
	public void setPk(IngredienteDeUsuarioId pk) {
		this.pk = pk;
	}

	@Column( name="coste", precision = 25, scale = 8 )
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste( BigDecimal coste ) {
		this.coste = coste == null ? BigDecimal.ZERO : coste;
	}

	@ManyToOne
	@JoinColumn( name = "id_escala_unidad_medida_fk", nullable = false )
	@ForeignKey( name ="IngredienteDeUsuario2EscalaDeUnidadDeMedida")
	public EscalaDeUnidadDeMedida getUnidad() {
		return unidad;
	}
	public void setUnidad(EscalaDeUnidadDeMedida unidad) {
		this.unidad = unidad;
	}

	public IngredienteDeUsuarioDto instanceDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkPermisoGet() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return;
		}
		if ( SecurityUtils.hasRol( RolEnum.COCINERO ) ) {
			if ( pk.getUsuario().getId().equals( SecurityUtils.getIdUsuario() ) ) {
				return;
			}
		}
		throw new PermisoAccesoException("No tienes permiso para ver este ingrediente de usuario");
	}

	@Override
	public void checkPermisoUpdate() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return;
		}
		if ( SecurityUtils.hasRol( RolEnum.COCINERO ) ) {
			if ( pk.getUsuario().getId().equals( SecurityUtils.getIdUsuario() ) ) {
				return;
			}
		}
		throw new PermisoAccesoException("No tienes permiso para modificar este ingrediente de usuario");
	}

	@Override
	public void checkPermisoSave() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) || SecurityUtils.hasRol( RolEnum.COCINERO ) ) {
			return;
		}
		throw new PermisoAccesoException("No tienes permiso para añadir ingredientes");
	}

	@Override
	public void checkPermisoDelete() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return;
		}
		if ( SecurityUtils.hasRol( RolEnum.COCINERO ) ) {
			if ( pk.getUsuario().getId().equals( SecurityUtils.getIdUsuario() ) ) {
				return;
			}
		}
		throw new PermisoAccesoException("No tienes permiso para borrar este ingredienteDeUsuario");
	}

	@Override
	public FiltroServer getFiltroFind() {
		FiltroServer dev = new FiltroServer();
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return dev;
		}
		if ( SecurityUtils.hasRol( RolEnum.COCINERO ) ) {
			dev.add( new FiltroWhere( " and pk.usuario.id = :idUsuario", "idUsuario", SecurityUtils.getIdUsuario() ) );
			return dev;
		}
		dev.add( new FiltroWhere( " and 1 != 1", "void", null ) );
		return dev;
	}
}