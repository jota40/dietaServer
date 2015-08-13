package jota.server.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class IngredienteDeUsuarioId implements Serializable {

	private static final long serialVersionUID = -4377475877767803342L;

	private Ingrediente ingrediente;
	private Usuario usuario;

	@ManyToOne
	@ForeignKey( name ="IngredienteDeUsuario2Ingrediente")
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	@ManyToOne
	@ForeignKey( name ="IngredienteDeUsuario2Usuario")
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}