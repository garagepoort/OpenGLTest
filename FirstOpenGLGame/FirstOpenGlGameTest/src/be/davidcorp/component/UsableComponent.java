package be.davidcorp.component;

import static be.davidcorp.component.ComponentType.USABLE_COMPONENT;
import be.davidcorp.domain.sprite.Sprite;

public class UsableComponent implements Component{

	private int usableRange;
	private OnUseImplementation implementation;
	
	public UsableComponent(int usableRange, OnUseImplementation implementation) {
		this.usableRange = usableRange;
		this.implementation = implementation;
	}

	@Override
	public ComponentType getType() {
		return USABLE_COMPONENT;
	}

	public interface OnUseImplementation{
		public void onUse(Sprite sprite, Sprite usingSprite);
	}
	
	public OnUseImplementation getImplementation() {
		return implementation;
	}
	
	public int getUsableRange() {
		return usableRange;
	}
}
