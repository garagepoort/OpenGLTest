package be.davidcorp.repository;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import be.davidcorp.domain.game.Gamefield;

public class IDGenerator {

	public static int generateIdForSprites() {
		SortedSet<Integer> keys = new TreeSet<Integer>(DefaultSpriteRepository.getInstance().getAllSpritesMap().keySet());
		if(keys.isEmpty()) return 1;
		return keys.last()+1;
	}
	
	public static int generateId() {
		SortedSet<Integer> keys = new TreeSet<Integer>(DefaultSpriteRepository.getInstance().getAllSpritesMap().keySet());
		if(keys.isEmpty()) return 1;
		return keys.last()+1;
	}

	public static int generateIdForGamefields(Map<Integer, Gamefield> gamefields) {
		if(gamefields.size()==0) return 1;
		SortedSet<Integer> keys = new TreeSet<Integer>(gamefields.keySet());
		return keys.last()+1;
	}
}
