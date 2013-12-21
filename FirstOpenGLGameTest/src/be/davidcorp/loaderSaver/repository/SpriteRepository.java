package be.davidcorp.loaderSaver.repository;

import java.util.ArrayList;

import be.davidcorp.domain.sprite.Sprite;

public interface SpriteRepository<SPRITE extends Sprite> {
	
	public void loadSprites(String type, ArrayList<String> spriteStrings);

	public SPRITE getSprite(int id);
	
	public SPRITE createSprite(SPRITE sprite);

	void updateSprite(SPRITE spriteToUpdate);
	
	public void emptyRepository();
}
