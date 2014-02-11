package be.davidcorp.applicationLayer.exception;

public class ModelException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ModelException() {
		super();
	}

	public ModelException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ModelException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ModelException(String arg0) {
		super(arg0);
	}

	public ModelException(Throwable arg0) {
		super(arg0);
	}

	
}
