package be.davidcorp.component;

import static be.davidcorp.component.ComponentType.USABLE_COMPONENT;
import be.davidcorp.domain.sprite.Sprite;

public class UsableComponent implements Component{

	private int usableRange;
	private onUseImplementation implementation;
	
	public UsableComponent(int usableRange, onUseImplementation implementation) {
		this.usableRange = usableRange;
		this.implementation = implementation;
	}

	@Override
	public ComponentType getType() {
		return USABLE_COMPONENT;
	}

	public interface onUseImplementation{
		public void onUse(Sprite sprite, Sprite usingSprite);
	}
	
	public onUseImplementation getImplementation() {
		return implementation;
	}
	
	public int getUsableRange() {
		return usableRange;
	}
}
