package be.davidcorp.component;

public class UsingComponent implements Component{

	private boolean isUsing;

	@Override
	public ComponentType getType() {
		return ComponentType.USING_COMPONENT;
	}
	
	public boolean isUsing() {
		return isUsing;
	}
	
	public void setUsing(boolean isUsing) {
		this.isUsing = isUsing;
	}
}
