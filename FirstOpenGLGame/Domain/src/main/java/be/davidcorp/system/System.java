package be.davidcorp.system;

import be.davidcorp.domain.sprite.Sprite;

public interface System {

	public void executeSystem(Sprite sprite, float secondsMovedInGame);
}
