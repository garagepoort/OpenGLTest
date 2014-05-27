package be.davidcorp.domain.game;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.metric.Point;
import be.davidcorp.texture.TextureBunch;

public class Gamefield {
	private String gamefieldName;
	private GamefieldEnvironment environment;



	private boolean creationMode = false;

	private float x = 0;
	private float y = 0;
	
	private TextureBunch textureBunch;

	public Gamefield(String name, int width, int height) {
		this(name, width, height, new DefaultGamefieldUpdater());
	}
	
	public Gamefield(String name, int width, int height, GamefieldUpdater gamefieldUpdater) {
		environment = new GamefieldEnvironment(gamefieldUpdater, width, height);
		setWidth(width);
		setHeight(height);
		this.gamefieldName = name;
	}
	
	public void update(float secondsMovedInGame) {
		if (!PauseManager.isGamePaused()) {
			if (!creationMode) {
				environment.updateGamefieldEnvironment(secondsMovedInGame);
			}
			PlayerManager.getCurrentPlayer().updateSprite(secondsMovedInGame);
			// addSpritesToWorld();
		}
	}

	// COLLISIONS

	public boolean doesPlayerCollideWithAnyConstructionItem() {
		return spriteAgainstAnyConstructionItem(PlayerManager.getCurrentPlayer());
	}

	public boolean doesEnemyCollideWithAnyConstructionItem(Enemy enemy) {
		return spriteAgainstAnyConstructionItem(enemy);
	}

	public boolean spriteAgainstAnyConstructionItem(Sprite sprite) {
		for (Sprite spriteAgainst : getSpritesInWorld()) {
			if(spriteAgainst instanceof ConstructionSprite){
				ConstructionSprite constructionSprite = (ConstructionSprite) spriteAgainst;
				if (constructionSprite.getID() != sprite.getID() && SpriteCollisionChecker.doesCollisionExist(constructionSprite, sprite))
					return true;
			}
		}
		return false;
	}

	public boolean againstOrganicSprite(Sprite sprite) {
		if (SpriteCollisionChecker.doesCollisionExist(sprite, PlayerManager.getCurrentPlayer()))
			return true;
		
		for (Sprite spriteAgainst : getSpritesInWorld()) {
			if(spriteAgainst instanceof Enemy){
				Enemy enemy = (Enemy) spriteAgainst;
				if (enemy.getID() != sprite.getID() && SpriteCollisionChecker.doesCollisionExist(enemy, sprite))
					return true;
			}
		}
		return false;
	}

	public void addSpriteToWorld(Sprite sprite){
		environment.addSpriteToWorld(sprite);
	}

	public List<Sprite> getSpritesInWorld(){
		return environment.getSpritesInWorld();
	}

	public Guide getGuide() {
		return environment.getGuide();
	}

	public void setGuide(Guide guide) {
		environment.setGuide(guide);
	}

	public void checkOnUseTriggers() {
		environment.checkOnUseTriggers();
	}

	public ArrayList<Item> getItemsThatCanBePickedUpByPlayer() {
		return environment.getItemsThatCanBePickedUpByPlayer();
	}

	public String getName() {
		return gamefieldName;
	}

	public void setName(String name) {
		this.gamefieldName = name;
	}

	public boolean isCreationMode() {
		return creationMode;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return environment.getWidth();
	}

	private void setWidth(int width) {
		if (width < 1) {
			throw new GameFieldException("The width must be 1 or greater");
		}
		environment.setWidth(width);
	}

	public int getHeight() {
		return environment.getHeight();
	}

	private void setHeight(int height) {
		if (height < 1) {
			throw new GameFieldException("The height must be 1 or greater");
		}
		environment.setHeight(height);
	}

	public String getTexture() {
		if (textureBunch == null) {
			return null;
		}
		return textureBunch.getCurrentTexture();
	}

	public void setTexture(String texture) {
		if (getTextureBunch() == null) {
			setTextureBunch(new TextureBunch());
		}
		textureBunch.withDefaultTexture(texture);
	}

	public TextureBunch getTextureBunch() {
		return textureBunch;
	}

	public void setTextureBunch(TextureBunch textureBunch) {
		this.textureBunch = textureBunch;
	}

	public void setGamefieldUpdater(GamefieldUpdater gamefieldUpdater) {
		environment.setGamefieldUpdater(gamefieldUpdater);
	}
	
	public List<Sprite> getSpritesCollidingWithPoint(Point point) {
		return findSpritesThatCollideWithPoint(point, environment.getSpritesInWorld());
	}

	public String toString() {
		return gamefieldName;
	}
	
	public void updateSprite(Sprite sprite){
		environment.updateSprite(sprite);
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
	
	public void removeSpriteFromWorld(int id){
		environment.removeSpriteFromWorld(id);
	}

}
