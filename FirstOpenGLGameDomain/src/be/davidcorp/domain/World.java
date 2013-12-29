package be.davidcorp.domain;

import java.util.List;

import com.google.common.collect.Lists;

import be.davidcorp.domain.entity.Sprite;

public class World {

	private static List<Sprite> sprites = Lists.newArrayList();
	private static List<Sprite> spritesToAdd = Lists.newArrayList();
	private static List<Sprite> spritesToRemove = Lists.newArrayList();
	
	public static void update(){
		for (Sprite sprite : spritesToAdd) {
			sprites.add(sprite);
		}
		for (Sprite sprite : spritesToRemove) {
			sprites.remove(sprite);
		}
		spritesToAdd.clear();
		spritesToRemove.clear();
	}
	
	public static List<Sprite> getSprites() {
		return sprites;
	}
	
	public static void addSprite(Sprite sprite) {
		spritesToAdd.add(sprite);
	}
	
	public static void removeSprite(Sprite sprite) {
		spritesToRemove.add(sprite);
	}
}
