package be.davidcorp.repository;

import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.Sprite;

public interface SpriteRepository {
	
	public Sprite getSprite(int id);
	
	public Sprite createSprite(Sprite sprite);

	void updateSprite(Sprite spriteToUpdate);
	
	public void emptyRepository();
	
	public void deleteSprite(int id);

	List<Sprite> getAllSprites();

	Map<Integer, Sprite> getAllSpritesMap();
	
}
