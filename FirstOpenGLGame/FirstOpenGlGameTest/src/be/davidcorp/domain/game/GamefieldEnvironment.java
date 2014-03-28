package be.davidcorp.domain.game;

import static be.davidcorp.domain.sprite.construction.ConstructionSpriteFactory.createWallWithID;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Ammo;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.system.HealthRegenerationSystem;
import be.davidcorp.system.System;
import be.davidcorp.system.TimeToLiveSystem;

class GamefieldEnvironment {

	private Gamefield gamefield;
	private GamefieldUpdater gamefieldUpdater;
	
	Map<Integer, Enemy> enemies = new ConcurrentHashMap<>();
	Map<Integer, Item> groundItems = new ConcurrentHashMap<>();
	Map<Integer, Ammo> worldAmmo = new ConcurrentHashMap<>();
	Map<Integer, ConstructionSprite> constructionSprites = new ConcurrentHashMap<>();
	Map<Integer, Light> lights = new ConcurrentHashMap<>();
	
	List<System> systems = newArrayList();

	GamefieldEnvironment(Gamefield gamefield, GamefieldUpdater gamefieldUpdater) {
		this.gamefield = gamefield;
		this.gamefieldUpdater = gamefieldUpdater;
		addSystems();
		addSurroundingWalls();
	}
	
	void updateGamefieldEnvironment(float secondsMovedInTime) {
		gamefieldUpdater.updateGamefield(this, secondsMovedInTime);
		gamefield.guide.checkCollisionWithGuideArea(PlayerManager.getCurrentPlayer(), secondsMovedInTime);
	}
	
	void setGamefieldUpdater(GamefieldUpdater gamefieldUpdater) {
		this.gamefieldUpdater=gamefieldUpdater;
	}
	
	void removeConstructionSpriteFromWorld(int id) {
		constructionSprites.remove(id);
	}

	void removeLightFromWorld(int id) {
		lights.remove(id);
	}

	void removeItemFromWorld(int id) {
		groundItems.remove(id);
	}
	
	void removeEnemyFromWorld(int id) {
		enemies.remove(id);
	}
	
	void updateConstructionSprite(ConstructionSprite constructionSprite) {
		if (!constructionSprites.containsKey(constructionSprite.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + constructionSprite);
		}
		constructionSprites.put(constructionSprite.getID(), constructionSprite);
	}

	void updateGroundItem(Item item) {
		if (!groundItems.containsKey(item.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + item);
		}
		groundItems.put(item.getID(), item);
	}

	void updateLight(Light light) {
		if (!lights.containsKey(light.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + light);
		}
		lights.put(light.getID(), light);
	}

	void updateEnemy(Enemy enemy) {
		if (!enemies.containsKey(enemy.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + enemy);
		}
		enemies.put(enemy.getID(), enemy);
	}

	void addEnemyToWorld(Enemy enemy) {
		enemies.put(enemy.getID(), enemy);
	}

	void addAmmoToWorld(Ammo ammo) {
		worldAmmo.put(ammo.getID(), ammo);
	}

	void addLight(Light light) {
		lights.put(light.getID(), light);
	}

	void addConstructionItem(ConstructionSprite constructionSprite) {
		constructionSprites.put(constructionSprite.getID(), constructionSprite);
	}

	void addGroundItem(Item groundItem) {
		groundItems.put(groundItem.getID(), groundItem);
	}

	void checkOnUseTriggers() {
		for (ConstructionSprite constructionSprite : constructionSprites.values()) {
			constructionSprite.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
		for (Light light : lights.values()) {
			light.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
		for (Enemy enemy : enemies.values()) {
			enemy.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
	}
	
	ArrayList<Item> getItemsThatCanBePickedUpByPlayer() {
		ArrayList<Item> items = new ArrayList<>();
		for (Item i : groundItems.values()) {
			if (SpriteCollisionChecker.doesCollisionExist(i, PlayerManager.getCurrentPlayer())) {
				items.add(i);
			}
		}
		return items;
	}

	private void addSystems() {
		systems.add(new TimeToLiveSystem());
		systems.add(new HealthRegenerationSystem());
	}

	private void addSurroundingWalls() {
		int wallThickness = 10;
		constructionSprites.put(-1, createWallWithID(-1, wallThickness, 0, gamefield.width - wallThickness, wallThickness));
		constructionSprites.put(-2, createWallWithID(-2, gamefield.width - wallThickness, 0, wallThickness, gamefield.height));
		constructionSprites.put(-3, createWallWithID(-3, wallThickness, gamefield.height - wallThickness, gamefield.width - wallThickness, wallThickness));
		constructionSprites.put(-4, createWallWithID(-4, 0, 0, wallThickness, gamefield.height));
	}

}
