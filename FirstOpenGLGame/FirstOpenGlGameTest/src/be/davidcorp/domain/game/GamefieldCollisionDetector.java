package be.davidcorp.domain.game;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.metric.Point;

public class GamefieldCollisionDetector {

	private Gamefield gamefield;

	public GamefieldCollisionDetector(Gamefield gamefield) {
		this.gamefield = gamefield;
	}

	public List<Sprite> getSpritesCollidingWithPoint(Point point) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(findSpritesThatCollideWithPoint(point, gamefield.getConstructionItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, gamefield.getGroundItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, gamefield.getEnemiesInWorld()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, gamefield.getLightsFromWorld()));
		return sprites;
	}
	
	private List<Sprite> findSpritesThatCollideWithPoint(Point point, List<? extends Sprite> sprites) {
		List<Sprite> result = newArrayList();
		for (Sprite cs : sprites) {
			if (SpriteCollisionChecker.doesSpriteCollideWithPoint(cs, point)) {
				result.add(cs);
			}
		}
		return result;
	}
}
