package jota.server.exceptions;

public class ServiceException extends Exception {

	private static final long serialVersionUID = -9133609298388318024L;
	private String lastCause;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
		Throwable e = cause;
		while ( e.getCause() != null ){
			e = e.getCause();
		}
		lastCause = e.getLocalizedMessage();
	}
	
	public String getLastCause() {
		return lastCause;
	}
}