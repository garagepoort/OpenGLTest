package be.davidcorp.domain.exception;

@SuppressWarnings("serial")
public class InventoryException extends RuntimeException{
	
	private String mistake;

	public InventoryException()
	{
		super();             // call superclass constructor
		mistake = "unknown";
	}

	public InventoryException(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	public String getError()
	{
		return mistake;
	}
}
