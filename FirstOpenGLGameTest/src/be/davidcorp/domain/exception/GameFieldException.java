package be.davidcorp.domain.exception;

@SuppressWarnings("serial")
public class GameFieldException extends Exception {
	private String mistake;

	public GameFieldException()
	{
		super();             // call superclass constructor
		mistake = "unknown";
	}
	
	public GameFieldException(Exception e)
	{
		super(e);             // call superclass constructor
		mistake = "unknown";
	}

	public GameFieldException(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	public String getError()
	{
		return mistake;
	}
}
