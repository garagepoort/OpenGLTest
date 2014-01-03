package be.davidcorp.component;

import static be.davidcorp.component.ComponentType.USECOMPONENT;
import be.davidcorp.domain.sprite.Sprite;

public class UseComponent implements Component{

	public int usableRange;
	public onUseImplementation implementation;
	
	public UseComponent(int usableRange, onUseImplementation implementation) {
		this.usableRange = usableRange;
		this.implementation = implementation;
	}

	@Override
	public ComponentType getType() {
		return USECOMPONENT;
	}

	public interface onUseImplementation{
		public void onUse(Sprite sprite);
	}
}
