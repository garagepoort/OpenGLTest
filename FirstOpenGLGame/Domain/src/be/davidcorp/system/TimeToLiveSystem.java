package be.davidcorp.system;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.TimeToLiveComponent;
import be.davidcorp.domain.sprite.Sprite;

public class TimeToLiveSystem implements System{

	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		TimeToLiveComponent timeToLiveComponent = sprite.<TimeToLiveComponent> getComponent(ComponentType.TIME_TO_LIVE);
		if (timeToLiveComponent != null) {
			timeToLiveComponent.TimeToLiveTime--;
			if (timeToLiveComponent.TimeToLiveTime <= 0) {
				sprite.kill();
			}
		}
	}
}
