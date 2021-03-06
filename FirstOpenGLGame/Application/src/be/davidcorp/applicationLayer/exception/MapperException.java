package be.davidcorp.applicationLayer.exception;

public class MapperException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MapperException() {
		super();
	}

	public MapperException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapperException(String message) {
		super(message);
	}

	public MapperException(Throwable cause) {
		super(cause);
	}
	
	

}
