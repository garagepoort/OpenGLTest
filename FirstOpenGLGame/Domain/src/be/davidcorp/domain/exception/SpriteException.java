package be.davidcorp.domain.exception;

@SuppressWarnings("serial")
public class SpriteException extends RuntimeException{
	private String mistake;

	public SpriteException()
	{
		super();             // call superclass constructor
		mistake = "unknown";
	}

	public SpriteException(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	public String getError()
	{
		return mistake;
	}
}
