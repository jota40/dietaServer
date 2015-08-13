package jota.server.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class UsuarioDeCompartibleId implements Serializable {

	private static final long serialVersionUID = -4377475877767803342L;

	private Usuario usuario;
	private Compartible compartible;

	@ManyToOne
	@ForeignKey( name ="UsuarioDeCompartible2Usuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

	@ManyToOne
	@ForeignKey( name ="UsuarioDeCompartible2Compartible")
	public Compartible getCompartible() {
		return compartible;
	}
	public void setCompartible( Compartible compartible ) {
		this.compartible = compartible;
	}

	@Override
	public String toString() {
		return "UsuarioDeCompartibleId [usuario=" + usuario + ", compartible="
				+ compartible + "]";
	}
}