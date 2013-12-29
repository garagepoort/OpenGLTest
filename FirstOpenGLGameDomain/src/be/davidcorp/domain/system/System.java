package be.davidcorp.domain.system;

import be.davidcorp.domain.entity.Sprite;

public interface System {

	
	public void executeSystem(Sprite sprite, float secondsMovedInGame);

}
