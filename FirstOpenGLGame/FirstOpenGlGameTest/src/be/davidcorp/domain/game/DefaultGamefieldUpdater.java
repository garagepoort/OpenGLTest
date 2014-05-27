package be.davidcorp.domain.game;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Iterator;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.system.System;

public class DefaultGamefieldUpdater implements GamefieldUpdater{

	private GamefieldEnvironment environment;
	
	@Override
	public void updateGamefield(GamefieldEnvironment gamefieldEnvironment, float secondsMovedInGame) {
		this.environment = gamefieldEnvironment;
		updateAllTheSprites(secondsMovedInGame);
	}
	
	private void updateAllTheSprites(float secondsMovedInGame) {
		updateSpriteMap(newArrayList(environment.getSpritesInWorld()), secondsMovedInGame);
		executeSystems(PlayerManager.getCurrentPlayer(), secondsMovedInGame);
	}
	
	private void updateSpriteMap(ArrayList<? extends Sprite> sprites, float secondsMovedInGame) {
		Iterator<? extends Sprite> iterator = sprites.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (sprite.isAlive()) {
				sprite.updateSprite(secondsMovedInGame);
				executeSystems(sprite, secondsMovedInGame);
			} else {
				iterator.remove();
			}
		}
	}

	private void executeSystems(Sprite sprite, float secondsMovedInGame) {
		for (System system : environment.systems) {
			system.executeSystem(sprite, secondsMovedInGame);
		}
	}
}
