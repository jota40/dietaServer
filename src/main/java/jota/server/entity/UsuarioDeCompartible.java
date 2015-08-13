package jota.server.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="usuario_de_compartible")
@AssociationOverrides({
	@AssociationOverride( name = "pk.compartible", joinColumns = @JoinColumn( name = "id_compartible_fk" ) ),
	@AssociationOverride( name = "pk.usuario", joinColumns = @JoinColumn( name = "id_usuario_fk" ) )
})

public class UsuarioDeCompartible {
	private UsuarioDeCompartibleId pk = new UsuarioDeCompartibleId();
	private boolean escritura = false;

	public UsuarioDeCompartible() {}

	@EmbeddedId
	public UsuarioDeCompartibleId getPk() {
		return pk;
	}
	public void setPk( UsuarioDeCompartibleId pk ) {
		this.pk = pk;
	}

	@Column( name="escritura", nullable = false )
	public boolean getEscritura() {
		return escritura;
	}
	public void setEscritura( boolean escritura ) {
		this.escritura = escritura;
	}
}