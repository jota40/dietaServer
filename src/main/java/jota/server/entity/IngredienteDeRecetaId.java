package jota.server.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ForeignKey;

@Embeddable
public class IngredienteDeRecetaId implements Serializable {

	private static final long serialVersionUID = -4377475877767803342L;

	private IngredienteDeUsuario ingredienteDeUsuario;
	private Receta receta;

	@ManyToOne
	@ForeignKey( name ="IngredienteDeReceta2IngredienteDeUsuario")
	public IngredienteDeUsuario getIngredienteDeUsuario() {
		return ingredienteDeUsuario;
	}
	public void setIngredienteDeUsuario(IngredienteDeUsuario ingredienteDeUsuario) {
		this.ingredienteDeUsuario = ingredienteDeUsuario;
	}

	@ManyToOne
	@ForeignKey( name ="IngredienteDeReceta2Receta")
	public Receta getReceta() {
		return receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}
}