package be.davidcorp.domain.exception;

@SuppressWarnings("serial")
public class WeaponException extends Exception {
	private String mistake;
	public WeaponException()
	{
		super();             // call superclass constructor
		mistake = "unknown";
	}

	public WeaponException(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	public String getError()
	{
		return mistake;
	}
}
