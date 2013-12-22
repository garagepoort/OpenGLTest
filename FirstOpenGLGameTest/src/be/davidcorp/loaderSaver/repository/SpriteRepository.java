package be.davidcorp.loaderSaver.repository;

import java.util.List;

import be.davidcorp.domain.sprite.Sprite;

public interface SpriteRepository<SPRITE extends Sprite> {
	
	public SPRITE getSprite(int id);
	
	public SPRITE createSprite(SPRITE sprite);

	void updateSprite(SPRITE spriteToUpdate);
	
	public void emptyRepository();

	void loadSprites(List<SPRITE> sprites);

}
