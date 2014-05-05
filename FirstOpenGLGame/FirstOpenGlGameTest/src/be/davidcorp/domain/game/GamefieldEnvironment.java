package be.davidcorp.domain.game;

import static be.davidcorp.domain.sprite.construction.ConstructionSpriteFactory.createWallWithID;
import static be.davidcorp.domain.sprite.organic.player.PlayerManager.getCurrentPlayer;
import static be.davidcorp.domain.trigger.TriggerWhen.ONUSE;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;
import be.davidcorp.system.HealthRegenerationSystem;
import be.davidcorp.system.System;
import be.davidcorp.system.TimeToLiveSystem;

class GamefieldEnvironment {

	private GamefieldUpdater gamefieldUpdater;
	private Guide guide = new Guide();
	private int width = 64;
	private int height = 64;
	SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();

	List<System> systems = newArrayList();

	GamefieldEnvironment(GamefieldUpdater gamefieldUpdater, int width,
			int height) {
		this.gamefieldUpdater = gamefieldUpdater;
		this.width = width;
		this.height = height;
		addSystems();
		addSurroundingWalls();
	}
	
	List<Sprite> getSpritesInWorld() {
		List<Sprite> sprites = newArrayList();
		for(Sprite sprite : spriteRepository.getAllSprites()){
			if(sprite.isInWorld()){
				sprites.add(sprite);
			}
		}
		return sprites;
	}

	void updateGamefieldEnvironment(float secondsMovedInTime) {
		gamefieldUpdater.updateGamefield(this, secondsMovedInTime);
		guide.checkCollisionWithGuideArea(PlayerManager.getCurrentPlayer(),
				secondsMovedInTime);
	}

	void setGamefieldUpdater(GamefieldUpdater gamefieldUpdater) {
		this.gamefieldUpdater = gamefieldUpdater;
	}

	void removeSpriteFromWorld(int id) {
		spriteRepository.getSprite(id).setInWorld(false);
	}

	void updateSprite(Sprite sprite) {
		spriteRepository.updateSprite(sprite);
	}

	void addSpriteToWorld(Sprite sprite) {
		spriteRepository.getSprite(sprite.getID()).setInWorld(true);
	}

	void checkOnUseTriggers() {
		for (Sprite sprite : DefaultSpriteRepository.getInstance()
				.getAllSprites()) {
			sprite.checkTriggers(ONUSE, getCurrentPlayer());
		}
	}

	Guide getGuide() {
		return guide;
	}

	void setGuide(Guide guide) {
		this.guide = guide;
	}

	int getWidth() {
		return width;
	}

	void setWidth(int width) {
		this.width = width;
	}

	int getHeight() {
		return height;
	}

	void setHeight(int height) {
		this.height = height;
	}

	ArrayList<Item> getItemsThatCanBePickedUpByPlayer() {
		ArrayList<Item> items = new ArrayList<>();
		for (Sprite sprite : spriteRepository.getAllSprites()) {
			if (sprite instanceof Item) {
				Item item = (Item) sprite;
				if (SpriteCollisionChecker.doesCollisionExist(item, PlayerManager.getCurrentPlayer())) {
					items.add(item);
				}
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
		spriteRepository.createSprite(createWallWithID(-1, wallThickness, 0, width - wallThickness, wallThickness));
		spriteRepository.createSprite(createWallWithID(-2, width - wallThickness, 0, wallThickness, height));
		spriteRepository.createSprite(createWallWithID(-3, wallThickness, height - wallThickness, width - wallThickness, wallThickness));
		spriteRepository.createSprite(createWallWithID(-4, 0, 0, wallThickness, height));
	}
}
