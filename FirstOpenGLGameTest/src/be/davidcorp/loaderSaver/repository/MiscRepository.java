package be.davidcorp.loaderSaver.repository;

import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;

import com.google.common.collect.Lists;

public class MiscRepository implements SpriteRepository<Sprite>{

	private static Map<Integer, Sprite> sprites = newHashMap();
	
	@Override
	public Sprite getSprite(int id) {
		return sprites.get(id);
	}

	@Override
	public Sprite createSprite(Sprite sprite) {
		int generateIdForSprites = IDGenerator.generateIdForSprites(sprites);
		sprite.setID(generateIdForSprites);
		sprites.put(generateIdForSprites, sprite);
		return sprite;
	}

	@Override
	public void updateSprite(Sprite spriteToUpdate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void emptyRepository() {
		sprites.clear();
	}

	@Override
	public void loadSprites(List<Sprite> sprites) {
		for(Sprite sprite : sprites){
			this.sprites.put(sprite.getID(), sprite);
		}
	}
	
	@Override
	public List<Sprite> getAllSprites() {
		return Lists.newArrayList(sprites.values());
	}

}
