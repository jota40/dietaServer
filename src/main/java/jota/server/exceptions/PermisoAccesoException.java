package jota.server.exceptions;

public class PermisoAccesoException extends ServiceException {

	private static final long serialVersionUID = -7441680755825749667L;

	public PermisoAccesoException() {
		super();
	}

	public PermisoAccesoException(String message) {
		super(message);
	}

	public PermisoAccesoException(Throwable cause) {
		super(cause);
	}
}