package be.davidcorp.domain.exception;

@SuppressWarnings("serial")
public class SkillException extends Exception{
	private String mistake;

	public SkillException()
	{
		super();             // call superclass constructor
		mistake = "unknown";
	}

	public SkillException(String err)
	{
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	public String getError()
	{
		return mistake;
	}
}
