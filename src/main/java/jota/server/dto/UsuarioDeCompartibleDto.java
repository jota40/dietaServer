package jota.server.dto;

import java.io.Serializable;

public class UsuarioDeCompartibleDto implements Serializable {

	private static final long serialVersionUID = -3312146371468281246L;

	private Long idUsuario;
	private UsuarioDto usuarioDto;
	private boolean escritura;

	public UsuarioDeCompartibleDto() {}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

	public boolean isEscritura() {
		return escritura;
	}

	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}
}