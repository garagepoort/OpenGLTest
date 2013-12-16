package be.davidcorp.loaderSaver.repository;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;

public class IDGenerator {

	public static int generateIdForSprites(Map<Integer, ? extends Sprite> sprites) {
		if(sprites.size()==0) return 1;
		SortedSet<Integer> keys = new TreeSet<Integer>(sprites.keySet());
		return keys.last()+1;
	}

	public static int generateIdForGamefields(Map<Integer, Gamefield> gamefields) {
		if(gamefields.size()==0) return 1;
		SortedSet<Integer> keys = new TreeSet<Integer>(gamefields.keySet());
		return keys.last()+1;
	}
}
