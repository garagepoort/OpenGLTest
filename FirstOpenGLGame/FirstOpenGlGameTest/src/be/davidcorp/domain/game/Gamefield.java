package be.davidcorp.domain.game;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Ammo;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.metric.Point;
import be.davidcorp.system.System;
import be.davidcorp.texture.TextureBunch;

public class Gamefield {
	private Integer ID;
	private String gamefieldName;
	private GamefieldEnvironment environment;

	Guide guide = new Guide();

	private boolean creationMode = false;

	float x = 0;
	float y = 0;
	int width = 64;
	int height = 64;

	private TextureBunch textureBunch;
	private GamefieldUpdater gamefieldUpdater;

	public Gamefield(String name, int width, int height) {
		this(name, width, height, new DefaultGamefieldUpdater());
	}
	
	public Gamefield(String name, int width, int height, GamefieldUpdater gamefieldUpdater) {
		this.gamefieldUpdater = gamefieldUpdater;
		setWidth(width);
		setHeight(height);
		this.gamefieldName = name;
		environment = new GamefieldEnvironment(this, gamefieldUpdater);
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
		for (int i = 0; i < getConstructionItems().size(); i++) {
			ConstructionSprite constructionSprite = getConstructionItems().get(i);
			if (constructionSprite.getID() != sprite.getID() && SpriteCollisionChecker.doesCollisionExist(constructionSprite, sprite))
				return true;
		}
		return false;
	}

	public boolean againstOrganicSprite(Sprite sprite) {
		if (SpriteCollisionChecker.doesCollisionExist(sprite, PlayerManager.getCurrentPlayer()))
			return true;
		for (int i = 0; i < getEnemiesInWorld().size(); i++) {
			if (SpriteCollisionChecker.doesCollisionExist(getEnemiesInWorld().get(i), sprite))
				return true;
		}
		return false;
	}

	public void addEnemyToWorld(Enemy enemy) {
		environment.addEnemyToWorld(enemy);
	}

	public void addAmmoToWorld(Ammo ammo) {
		environment.addAmmoToWorld(ammo);
	}

	public void addLight(Light light) {
		environment.addLight(light);
	}

	public void addConstructionItem(ConstructionSprite constructionSprite) {
		environment.addConstructionItem(constructionSprite);
	}

	public void addGroundItem(Item groundItem) {
		environment.addGroundItem(groundItem);
	}

	public List<Ammo> getAmmoInWorld() {
		return environment.getAmmoInWorld();
	}

	public List<Enemy> getEnemiesInWorld() {
		return environment.getEnemiesInWorld();
	}

	public List<Item> getGroundItems() {
		return environment.getGroundItems();
	}

	public List<ConstructionSprite> getConstructionItems() {
		return environment.getConstructionItems();
	}

	public List<Light> getLightsFromWorld() {
		return environment.getLightsFromWorld();
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public void checkOnUseTriggers() {
		environment.checkOnUseTriggers();
	}

	// private void checkIfPlayerIsStandingOnSwitch() {
	// boolean hit = false;
	// for (int i = 0; i < switches.size() && !hit; i++) {
	// hit = SpriteCollisionChecker.doesCollisionExist(switches.get(i), player);
	// if (hit) {
	// activeSwitch = switches.get(i);
	// }
	// }
	// }

	public ArrayList<Item> getItemsThatCanBePickedUpByPlayer() {
		return environment.getItemsThatCanBePickedUpByPlayer();
	}

	// GAMEFIELD SPECIFIC

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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
		return width;
	}

	private void setWidth(int width) {
		if (width < 1) {
			throw new GameFieldException("The width must be 1 or greater");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		if (height < 1) {
			throw new GameFieldException("The height must be 1 or greater");
		}
		this.height = height;
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
		this.gamefieldUpdater = gamefieldUpdater;
	}
	
	public List<Sprite> getSpritesCollidingWithPoint(Point point) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(findSpritesThatCollideWithPoint(point, getConstructionItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getGroundItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getEnemiesInWorld()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getLightsFromWorld()));
		return sprites;
	}

	public String toString() {
		return gamefieldName;
	}

	public void updateConstructionSprite(ConstructionSprite constructionSprite) {
		if (!constructionSprites.containsKey(constructionSprite.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + constructionSprite);
		}
		constructionSprites.put(constructionSprite.getID(), constructionSprite);
	}

	public void updateGroundItem(Item item) {
		if (!groundItems.containsKey(item.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + item);
		}
		groundItems.put(item.getID(), item);
	}

	public void updateLight(Light light) {
		if (!lights.containsKey(light.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + light);
		}
		lights.put(light.getID(), light);
	}

	public void updateEnemy(Enemy enemy) {
		if (!enemies.containsKey(enemy.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + enemy);
		}
		enemies.put(enemy.getID(), enemy);
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

	public void removeConstructionSpriteFromWorld(int id) {
		constructionSprites.remove(id);
	}

	public void removeLightFromWorld(int id) {
		lights.remove(id);
	}

	public void removeItemFromWorld(int id) {
		groundItems.remove(id);
	}
	public void removeEnemyFromWorld(int id) {
		enemies.remove(id);
	}

}
