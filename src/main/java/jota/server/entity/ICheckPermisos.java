package jota.server.entity;

import jota.server.dto.FiltroServer;
import jota.server.exceptions.PermisoAccesoException;

public interface ICheckPermisos {
	void checkPermisoGet() throws PermisoAccesoException;
	void checkPermisoUpdate() throws PermisoAccesoException;
	void checkPermisoSave() throws PermisoAccesoException;
	void checkPermisoDelete() throws PermisoAccesoException;
	FiltroServer getFiltroFind();
}