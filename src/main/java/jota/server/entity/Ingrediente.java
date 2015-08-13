package jota.server.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jota.server.dto.FiltroServer;
import jota.server.dto.IngredienteDto;
import jota.server.enumerations.RolEnum;
import jota.server.exceptions.PermisoAccesoException;
import jota.server.utils.SecurityUtils;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="ingrediente")
public class Ingrediente extends Base implements EsAuditable, ICheckPermisos {

	private String nombre;
	private String descripcion;
	private BigDecimal pesoPorRacion;
	private byte[] foto;
	private UnidadDeMedida unidadDeMedida;
	private GrupoAlimento grupoAlimento;

	public Ingrediente() {}

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

	@Column( name="peso_por_racion", precision = 25, scale = 8 )
	public BigDecimal getPesoPorRacion() {
		return pesoPorRacion;
	}
	public void setPesoPorRacion( BigDecimal pesoPorRacion) {
		this.pesoPorRacion = pesoPorRacion == null ? BigDecimal.ZERO : pesoPorRacion;
	}

	@Lob 
	@Column( name="foto" )
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	@ManyToOne
	@JoinColumn( name = "id_unidad_de_medida_fk" )
	@ForeignKey( name ="Ingrediente2UnidadDeMedida")
	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}

	@ManyToOne
	@JoinColumn( name = "id_grupo_alimento_fk", nullable = false )
	@ForeignKey( name ="Ingrediente2GrupoAlimento")
	public GrupoAlimento getGrupoAlimento() {
		return grupoAlimento;
	}
	public void setGrupoAlimento(GrupoAlimento grupoAlimento) {
		this.grupoAlimento = grupoAlimento;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Auditable auditable;

	@OneToOne( fetch = FetchType.LAZY )
	@JoinColumn( name = "id_auditable_fk" )
	@ForeignKey( name ="Ingrediente2Auditable")
	@Override
	public Auditable getAuditable() {
		return auditable;
	}
	@Override
	public void setAuditable(Auditable auditable) {
		this.auditable = auditable;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public IngredienteDto instanceDto() {
		return new IngredienteDto( id, nombre, descripcion, pesoPorRacion, unidadDeMedida == null ? null : unidadDeMedida.instanceDto(), grupoAlimento.instanceDto() );
	}

	public void copyFrom( IngredienteDto ingredienteDto ) {
		nombre = "".equals( ingredienteDto.getNombre().trim() ) ? null : ingredienteDto.getNombre();
		descripcion = "".equals( ingredienteDto.getDescripcion().trim() ) ? null : ingredienteDto.getDescripcion();
		pesoPorRacion = ingredienteDto.getPesoPorRacion();
	}

	@Override
	public void checkPermisoGet() throws PermisoAccesoException {
	}

	@Override
	public void checkPermisoUpdate() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return;
		}
		if ( SecurityUtils.hasRol( RolEnum.NUTRICIONISTA ) ) {
			if ( auditable.getCreadoPor().equals( SecurityUtils.getIdUsuario() ) ) {
				return;
			}
		}
		throw new PermisoAccesoException("No tienes permiso para modificar este ingrediente");
	}

	@Override
	public void checkPermisoSave() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) || SecurityUtils.hasRol( RolEnum.NUTRICIONISTA ) ) {
			return;
		}
		throw new PermisoAccesoException("No tienes permiso para añadir ingredientes");
	}

	@Override
	public void checkPermisoDelete() throws PermisoAccesoException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return;
		}
		if ( SecurityUtils.hasRol( RolEnum.NUTRICIONISTA ) ) {
			if ( auditable.getCreadoPor().equals( SecurityUtils.getIdUsuario() ) ) {
				return;
			}
		}
		throw new PermisoAccesoException("No tienes permiso para borrar este ingrediente");
	}

	@Override
	public FiltroServer getFiltroFind() {
		FiltroServer dev = new FiltroServer();
		return dev;
	}
}