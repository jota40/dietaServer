package jota.server.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import jota.server.utils.SecurityUtils;

@Entity
@Table(name="auditable")
public class Auditable extends Base {

	private Long creadoPor;
	private Date creado;
	private Long modificadoPor;
	private Date modificado;

	public Auditable() {}

	@Column(name = "creado_por", nullable = false)
	public Long getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(Long creadoPor) {
		this.creadoPor = creadoPor;
	}

	@Column(name = "creado", nullable = false)
	public Date getCreado() {
		return creado;
	}
	public void setCreado(Date creado) {
		this.creado = creado;		
	}

	@Column(name = "modificado_por")
	public Long getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	@Column(name = "modificado")
	public Date getModificado() {
		return modificado;
	}
	public void setModificado(Date modificado) {
		this.modificado = modificado;
	}

	public void auditUpdate() {
		modificado = new Date();
		modificadoPor = SecurityUtils.getIdUsuario();
	}
	
	public void auditInsert() {
		creado = new Date();
		creadoPor = SecurityUtils.getIdUsuario();
	}
}