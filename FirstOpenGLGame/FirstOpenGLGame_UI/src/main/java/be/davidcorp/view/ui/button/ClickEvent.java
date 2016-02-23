package be.davidcorp.view.ui.button;

import java.util.EventObject;

public class ClickEvent extends EventObject{

	private static final long serialVersionUID = 1L;
	
	public int x;
	public int y;
	
	public ClickEvent(Object source, int x, int y) {
		super(source);
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}

}
