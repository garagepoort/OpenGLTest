package be.davidcorp.domain.system;

import be.davidcorp.domain.components.Component;
import be.davidcorp.domain.entity.Sprite;

public abstract class System {

	
	public abstract void executeSystem(Sprite sprite, float secondsMovedInGame);

	protected boolean containsNecessaryComponents(Component... components) {
		for(Component component : components){
			if(component == null) return false;
		}
		return true;
	}
}
