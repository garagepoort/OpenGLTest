package be.davidcorp.domain.game;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Iterator;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;

public class GamefieldCreatorUpdater implements GamefieldUpdater{

	private GamefieldEnvironment environment;

	@Override
	public void updateGamefield(GamefieldEnvironment gamefieldEnvironment, float secondsMovedInGame) {
		this.environment = gamefieldEnvironment;
		updateAllTheSprites(secondsMovedInGame);
	}
	
	private void updateAllTheSprites(float secondsMovedInGame) {
		updateSpriteMap(newArrayList(environment.getSpritesInWorld()), secondsMovedInGame);
	}
	
	private void updateSpriteMap(List<? extends Sprite> sprites, float secondsMovedInGame) {
		Iterator<? extends Sprite> iterator = sprites.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (!sprite.isAlive()) {
				iterator.remove();
			}
		}
	}	

}
