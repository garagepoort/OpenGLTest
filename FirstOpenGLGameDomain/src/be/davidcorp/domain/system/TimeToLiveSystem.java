package be.davidcorp.domain.system;

import be.davidcorp.domain.World;
import be.davidcorp.domain.components.TimeToLiveComponent;
import be.davidcorp.domain.entity.Sprite;

public class TimeToLiveSystem implements System {

	private static TimeToLiveSystem instance = new TimeToLiveSystem();

	private TimeToLiveSystem() {
	}

	public static TimeToLiveSystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite sprite, float secondsMovedInGame) {
		TimeToLiveComponent timeToLiveComponent = sprite.getComponent(TimeToLiveComponent.class);
		
		if (timeToLiveComponent != null) {
			if(timeToLiveComponent.timeToLive <= 0){
				World.removeSprite(sprite);
			}else{
				timeToLiveComponent.timeToLive--;
			}
		}
	}

}
