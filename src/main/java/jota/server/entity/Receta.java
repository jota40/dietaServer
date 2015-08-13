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

import jota.server.dto.IngredienteDeRecetaDto;
import jota.server.dto.RecetaDto;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table( name="receta" )
public class Receta extends Base implements EsCompartible {
	private String nombre;
	private String descripcion;
	private Integer pax;
	private Set<IngredienteDeReceta> ingredientesDeReceta;
	
	public Receta() {}

	public Receta( RecetaDto recetaDto ) {
		copyFrom( recetaDto );
	}

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

	@Column( name="pax" )
	public Integer getPax() {
		return pax;
	}
	public void setPax( Integer pax ) {
		this.pax = pax == null ? 0 : pax;
	}

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "pk.receta", cascade = CascadeType.ALL, orphanRemoval = true )
  public Set<IngredienteDeReceta> getIngredientesDeReceta() {
		return ingredientesDeReceta;
	}
	public void setIngredientesDeReceta( Set<IngredienteDeReceta> ingredientesDeReceta ) {
		this.ingredientesDeReceta = ingredientesDeReceta;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Auditable auditable;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_auditable_fk" )
	@ForeignKey( name ="Receta2Auditable")
	@Override
	public Auditable getAuditable() {
		return auditable;
	}
	@Override
	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Compartible compartible;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_compartible_fk" )
	@ForeignKey( name ="Receta2Compartible")
	@Override
	public Compartible getCompartible() {
		return compartible;
	}
	@Override
	public void setCompartible(Compartible compartible) {
		this.compartible = compartible;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public RecetaDto instanceDto() {
		List<IngredienteDeRecetaDto> ingredientesDeRecetaDto = new ArrayList<IngredienteDeRecetaDto>();
		for ( IngredienteDeReceta ingredienteDeReceta : ingredientesDeReceta) {
			ingredientesDeRecetaDto.add( ingredienteDeReceta.instanceDto() );
		}
		return new RecetaDto( id, nombre, descripcion, pax, ingredientesDeRecetaDto );
	}

	public void copyFrom( RecetaDto recetaDto ) {
		nombre = "".equals( recetaDto.getNombre().trim() ) ? null : recetaDto.getNombre();
		descripcion = "".equals( recetaDto.getDescripcion().trim() ) ? null : recetaDto.getDescripcion();
		pax = recetaDto.getPax();
	}
}