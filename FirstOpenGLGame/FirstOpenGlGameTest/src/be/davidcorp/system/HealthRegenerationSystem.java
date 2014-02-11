package be.davidcorp.system;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.HealthRegenerationComponent;
import be.davidcorp.domain.sprite.Sprite;

public class HealthRegenerationSystem implements System{

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		HealthRegenerationComponent component = sprite.getComponent(ComponentType.HEALTHREGENERATION);
		if(component != null){
			sprite.addHealth((int) (component.healthRegeneratedPerSecond*secondsMovedInGame));
		}
	}

}
