package be.davidcorp.domain.game;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import static be.davidcorp.domain.sprite.construction.ConstructionSpriteFactory.createWallWithID;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newConcurrentMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import be.davidcorp.system.HealthRegenerationSystem;
import be.davidcorp.system.System;
import be.davidcorp.system.TimeToLiveSystem;
import be.davidcorp.texture.TextureBunch;

public class Gamefield {
	private Integer ID;
	private String gamefieldName;

	private Map<Integer, Enemy> enemies = newConcurrentMap();
	private Map<Integer, Item> groundItems = newConcurrentMap();
	private Map<Integer, Ammo> worldAmmo = newConcurrentMap();
	private Map<Integer, ConstructionSprite> constructionSprites = newConcurrentMap();
	private Map<Integer, Light> lights = newConcurrentMap();

	private List<System> systems = newArrayList();
	
	private Guide guide = new Guide();

	private boolean creationMode = false;

	private float x = 0;
	private float y = 0;
	private int width = 64;
	private int height = 64;

	private TextureBunch textureBunch;

	public Gamefield(String name, int width, int height) {
		setWidth(width);
		setHeight(height);
		this.gamefieldName = name;
		addSurroundingWalls();
		addSystems();
	}

	private void addSystems() {
		systems.add(new TimeToLiveSystem());
		systems.add(new HealthRegenerationSystem());
	}

	private void addSurroundingWalls() {
		constructionSprites.put(-1, createWallWithID(-1, 0, 0, width, 10));
		constructionSprites.put(-2, createWallWithID(-2, width, 0, 10, height));
		constructionSprites.put(-3, createWallWithID(-3, 0, height, width, 10));
		constructionSprites.put(-4, createWallWithID(-4, 0, 0, 10, height));
	}

	public void update(float secondsMovedInGame)  {
		if (!PauseManager.isGamePaused()) {
			if (!creationMode) {
				// checkIfPlayerIsStandingOnSwitch();
				updateAllTheSprites(secondsMovedInGame);
				guide.checkCollisionWithGuideArea(PlayerManager.getCurrentPlayer(), secondsMovedInGame);
			}
			PlayerManager.getCurrentPlayer().updateSprite(secondsMovedInGame);
//			addSpritesToWorld();
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

	// UPDATES
	private void updateAllTheSprites(float secondsMovedInGame){
		
		updateSpriteMap(enemies, secondsMovedInGame);
		updateSpriteMap(constructionSprites, secondsMovedInGame);
		updateSpriteMap(worldAmmo, secondsMovedInGame);
		executeSystems(PlayerManager.getCurrentPlayer(), secondsMovedInGame);
	}

	@SuppressWarnings("rawtypes")
	private void updateSpriteMap(Map<Integer, ? extends Sprite> sprites, float secondsMovedInGame) {
		Iterator iterator = sprites.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pairs = (Map.Entry) iterator.next();
			Sprite sprite = (Sprite) pairs.getValue();
			if (sprite.isAlive()) {
				sprite.updateSprite(secondsMovedInGame);
				executeSystems(sprite, secondsMovedInGame);
			} else {
				iterator.remove();
			}

		}
	}

	private void executeSystems(Sprite sprite, float secondsMovedInGame) {
		for(System system : systems){
			system.executeSystem(sprite, secondsMovedInGame);
		}
	}

	public void addEnemyToWorld(Enemy enemy) {
		enemies.put(enemy.getID(), enemy);
	}

	public void addAmmoToWorld(Ammo ammo) {
		worldAmmo.put(ammo.getID(), ammo);
	}

	public void addLight(Light light) {
		lights.put(light.getID(), light);
	}

	public void addConstructionItem(ConstructionSprite constructionSprite) {
		constructionSprites.put(constructionSprite.getID(), constructionSprite);
	}

	public void addGroundItem(Item groundItem) {
		groundItems.put(groundItem.getID(), groundItem);
	}

	public void removeSpriteFromWorld(Sprite sprite) {
		if(sprite instanceof Enemy) enemies.remove(sprite.getID());
		if(sprite instanceof Item) groundItems.remove(sprite.getID());
		if(sprite instanceof Ammo) worldAmmo.remove(sprite.getID());
		if(sprite instanceof Light) lights.remove(sprite.getID());
		if(sprite instanceof ConstructionSprite) constructionSprites.remove(sprite.getID());
	}

	public List<Ammo> getAmmoInWorld() {
		return Collections.unmodifiableList(newArrayList(worldAmmo.values()));
	}

	public List<Enemy> getEnemiesInWorld() {
		return Collections.unmodifiableList(newArrayList(enemies.values()));
	}

	public List<Item> getGroundItems() {
		return Collections.unmodifiableList(newArrayList(groundItems.values()));
	}

	public List<ConstructionSprite> getConstructionItems() {
		return Collections.unmodifiableList(newArrayList(constructionSprites.values()));
	}

	public List<Light> getLightsFromWorld() {
		return Collections.unmodifiableList(newArrayList(lights.values()));
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public void checkOnUseTriggers() {
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
		ArrayList<Item> items = new ArrayList<>();
		for (Item i : groundItems.values()) {
			if (SpriteCollisionChecker.doesCollisionExist(i, PlayerManager.getCurrentPlayer())) {
				items.add(i);
			}
		}
		return items;
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

	public void setWidth(int width)  {
		if (width < 1) {
			throw new GameFieldException("The width must be 1 or greater");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height)  {
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

	public void setTexture(String texture)  {
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

	public List<Sprite> getSpritesCollidingWithPoint(Point point) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(findSpritesThatCollideWithPoint(point, getCurrentGameField().getConstructionItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getCurrentGameField().getGroundItems()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getCurrentGameField().getEnemiesInWorld()));
		sprites.addAll(findSpritesThatCollideWithPoint(point, getCurrentGameField().getLightsFromWorld()));
		return sprites;
	}

	public String toString() {
		return gamefieldName;
	}

	public void updateConstructionSprite(ConstructionSprite constructionSprite)  {
		if (!constructionSprites.containsKey(constructionSprite.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + constructionSprite);
		}
		constructionSprites.put(constructionSprite.getID(), constructionSprite);
	}

	public void updateGroundItem(Item item)  {
		if (!groundItems.containsKey(item.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + item);
		}
		groundItems.put(item.getID(), item);
	}
	
	public void updateLight(Light light)  {
		if (!lights.containsKey(light.getID())) {
			throw new GameFieldException("The gamefield does not contain this sprite: " + light);
		}
		lights.put(light.getID(), light);
	}

	public void updateEnemy(Enemy enemy)  {
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

}
