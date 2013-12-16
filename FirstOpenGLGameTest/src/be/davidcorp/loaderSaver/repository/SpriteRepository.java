package be.davidcorp.loaderSaver.repository;

import java.util.ArrayList;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.loaderSaver.LoaderException;

public interface SpriteRepository<SPRITE extends Sprite> {
	
	public void loadSprites(String type, ArrayList<String> spriteStrings) throws LoaderException;

	public SPRITE getSprite(int id);
	
	public SPRITE createSprite(SPRITE sprite) throws SpriteRepositoryException;

	void updateSprite(SPRITE spriteToUpdate) throws SpriteException;
	
	public void emptyRepository();
}
