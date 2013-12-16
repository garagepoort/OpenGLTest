package be.davidcorp.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Ammo;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.metric.Point;
import be.davidcorp.texture.TextureBunch;

public class Gamefield {
	private Integer ID;
	private String gamefieldName;

	private ArrayList<Enemy> enemies = new ArrayList<>();
	private ArrayList<Item> groundItems = new ArrayList<>();
	private ArrayList<Ammo> worldAmmo = new ArrayList<>();
	private ArrayList<ConstructionSprite> constructionSprites = new ArrayList<>();
	private ArrayList<Light> lights = new ArrayList<Light>();

	private ArrayList<Light> lightsToAdd = new ArrayList<Light>();
	private ArrayList<Enemy> enemiesToAdd = new ArrayList<>();
	private ArrayList<Item> groundItemsToAdd = new ArrayList<Item>();
	private ArrayList<ConstructionSprite> constructionItemsToAdd = new ArrayList<>();
	
	private ArrayList<Sprite> spritesToRemove = new ArrayList<Sprite>();
//	private ArrayList<GameFieldSwitch> switches = new ArrayList<>();

	private Guide guide = new Guide();

	public GameFieldSwitch activeSwitch;
	
	private boolean creationMode = false;
//	private boolean pause = false;


	private float x = 0;
	private float y = 0;
	private int width = 64;
	private int height = 64;

	private TextureBunch textureBunch;

	public Gamefield(String name, int width, int height)
			throws GameFieldException, SpriteException {
		setWidth(width);
		setHeight(height);
		this.gamefieldName = name;
		addSurroundingWalls();
	}
	
	private void addSurroundingWalls() throws SpriteException {
		constructionSprites.add(new Wall(0, 0, width, 10));
		constructionSprites.add(new Wall(width, 0, 10, height));
		constructionSprites.add(new Wall(0, height, width, 10));
		constructionSprites.add(new Wall(0, 0, 10, height));
	}

	public void update(int secondsMovedInGame) throws GameFieldException {
		if (!PauseManager.isGamePaused()) {
			if (!creationMode) {
//				checkIfPlayerIsStandingOnSwitch();
				updateAllTheSprites(secondsMovedInGame);
				guide.checkCollisionWithGuideArea(PlayerManager.getCurrentPlayer(), secondsMovedInGame);
			}
			PlayerManager.getCurrentPlayer().updateSprite(secondsMovedInGame);
			removeSpritesFromWorld();
			addSpritesToWorld();
		}
	}

	// COLLISIONS

	public boolean doesPlayerCollideWithAnyConstructionItem() {
		return spriteAgainstAnyConstructionItem(PlayerManager.getCurrentPlayer());
	}

	public boolean doesEnemyCollideWithAnyConstructionItem(Enemy enemy) {
		return spriteAgainstAnyConstructionItem(enemy);
	}

	private boolean spriteAgainstAnyConstructionItem(Sprite sprite) {
		for (int i = 0; i < getConstructionItems().size(); i++) {
			if (SpriteCollisionChecker.doesCollisionExist(getConstructionItems().get(i), sprite)) return true;
		}
		return false;
	}

	public boolean againstOrganicSprite(Sprite sprite) {
		if (SpriteCollisionChecker.doesCollisionExist(sprite, PlayerManager.getCurrentPlayer())) return true;
		for (int i = 0; i < getEnemiesInWorld().size(); i++) {
			if(SpriteCollisionChecker.doesCollisionExist(getEnemiesInWorld().get(i), sprite)) return true;
		}
		return false;
	}

	
	// UPDATES
	private void updateAllTheSprites(int secondsMovedInGame) throws GameFieldException {
		updateSpriteList(enemies, secondsMovedInGame);
		updateSpriteList(constructionSprites, secondsMovedInGame);
		updateSpriteList(worldAmmo, secondsMovedInGame);
	}
	
	private void updateSpriteList(ArrayList<? extends Sprite> sprites, int secondsMovedInGame) {
		Iterator<? extends Sprite> iterator = (Iterator<? extends Sprite>) sprites.iterator();
		while (iterator.hasNext()) {
			Sprite sprite = iterator.next();
			if (sprite.isAlive()) {
				sprite.updateSprite(secondsMovedInGame);
			} else {
				iterator.remove();
			}
		}
	}

	public void addSpritesToWorld() {
		enemies.addAll(enemiesToAdd);
		groundItems.addAll(groundItemsToAdd);
		constructionSprites.addAll(constructionItemsToAdd);
		lights.addAll(lightsToAdd);
		
		enemiesToAdd.clear();
		groundItemsToAdd.clear();
		constructionItemsToAdd.clear();
		lightsToAdd.clear();
	}

	public void addEnemyToWorld(Enemy enemy) {
		enemiesToAdd.add(enemy);
	}
	
	public void addAmmoToWorld(Ammo a) {
		worldAmmo.add(a);
	}
	
	public void addLight(Light l) {
		lightsToAdd.add(l);
	}

	public void addConstructionItem(ConstructionSprite constructionSprite) {
		constructionItemsToAdd.add(constructionSprite);
	}

	public void addGroundItem(Item groundItem) {
		groundItemsToAdd.add(groundItem);
	}

	private void removeSpritesFromWorld() {
		enemies.removeAll(spritesToRemove);
		groundItems.removeAll(spritesToRemove);
		constructionSprites.removeAll(spritesToRemove);
		worldAmmo.removeAll(spritesToRemove);
		lights.removeAll(spritesToRemove);
		spritesToRemove.clear();
	}
	
	public void removeSpriteFromWorld(Sprite sprite){
		spritesToRemove.add(sprite);
	}


	public List<Ammo> getAmmoInWorld() {
		return Collections.unmodifiableList(worldAmmo);
	}

	public List<Enemy> getEnemiesInWorld() {
		return Collections.unmodifiableList(enemies);
	}


	public List<Item> getGroundItems() {
		return Collections.unmodifiableList(groundItems);
	}

	public List<ConstructionSprite> getConstructionItems() {
		return constructionSprites;
	}

	public List<Light> getLightsFromWorld() {
		return lights;
	}
	
	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public void useContructionItem() {
		for (ConstructionSprite s : getConstructionItems()) {
			s.doUse(PlayerManager.getCurrentPlayer());
		}
		checkAllOnUseTriggers();
	}

	private void checkAllOnUseTriggers() {
		for (ConstructionSprite constructionSprite : constructionSprites) {
			constructionSprite.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
		for (Light light : lights) {
			light.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
		for (Enemy enemy : enemies) {
			enemy.checkTriggers(TriggerWhen.ONUSE, PlayerManager.getCurrentPlayer());
		}
	}

//	private void checkIfPlayerIsStandingOnSwitch() {
//		boolean hit = false;
//		for (int i = 0; i < switches.size() && !hit; i++) {
//			hit = SpriteCollisionChecker.doesCollisionExist(switches.get(i), player);
//			if (hit) {
//				activeSwitch = switches.get(i);
//			}
//		}
//	}

	public ArrayList<Item> getItemsThatCanBePickedUpByPlayer() {
		ArrayList<Item> items = new ArrayList<>();
		for (Item i : groundItems) {
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

	public void setWidth(int width) throws GameFieldException {
		if (width < 1) {
			throw new GameFieldException("The width must be 1 or greater");
		}
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) throws GameFieldException {
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

	public void setTexture(String texture) throws GameFieldException {
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
		for (ConstructionSprite cs : GameFieldManager.getCurrentGameField().getConstructionItems()) {
			if (SpriteCollisionChecker.doesSpriteCollideWithPoint(cs, point)) {
				sprites.add(cs);
			}
		}
		for (Item item : GameFieldManager.getCurrentGameField().getGroundItems()) {
			if (SpriteCollisionChecker.doesSpriteCollideWithPoint(item, point)) {
				sprites.add(item);
			}
		}
		for (Enemy enemy : GameFieldManager.getCurrentGameField().getEnemiesInWorld()) {
			if (SpriteCollisionChecker.doesSpriteCollideWithPoint(enemy, point)) {
				sprites.add(enemy);
			}
		}
		for (Light light : GameFieldManager.getCurrentGameField().getLightsFromWorld()) {
			if (SpriteCollisionChecker.doesSpriteCollideWithPoint(light, point)) {
				sprites.add(light);
			}
		}
		return sprites;
	}

	public String toString(){
		return gamefieldName;
	}
}
