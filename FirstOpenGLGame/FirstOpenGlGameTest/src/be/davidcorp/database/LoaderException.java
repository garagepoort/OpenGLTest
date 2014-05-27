package be.davidcorp.database;

public class LoaderException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LoaderException() {
		super();
	}

	public LoaderException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoaderException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoaderException(String message) {
		super(message);
	}

	public LoaderException(Throwable cause) {
		super(cause);
	}

	
}
