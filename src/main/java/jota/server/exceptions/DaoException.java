package jota.server.exceptions;

public class DaoException extends ServiceException {

	private static final long serialVersionUID = -7441680755825749667L;

	public DaoException() {
		super();
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}