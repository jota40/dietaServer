package jota.server.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table( name="compartible" )
public class Compartible extends Base {

	private boolean publico;
	private Set<UsuarioDeCompartible> usuarios;

	@Column(name = "publico", nullable = false)
	public boolean getPublico() {
		return publico;
	}
	public void setPublico( boolean publico ) {
		this.publico = publico;
	}

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "pk.compartible", cascade = CascadeType.ALL, orphanRemoval = true )
	public Set<UsuarioDeCompartible> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios( Set<UsuarioDeCompartible> usuarios ) {
		this.usuarios = usuarios;
	}
}