package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.component.ComponentType.USING_COMPONENT;
import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import static be.davidcorp.domain.game.GameFieldManager.setCurrentGameField;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.AmmoDTO;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.GamefieldDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.gamefield.GamefieldToGamefieldDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.AmmoToAmmoDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.SpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.component.UsingComponent;
import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.game.GamefieldCreatorUpdater;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.metric.Point;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.GamefieldRepository;
import be.davidcorp.repository.SpriteRepository;

public class GameFieldFacade {

	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();

	public void togglePause() {
		PauseManager.setGamePaused(!PauseManager.isGamePaused());
	}

	public int getWidthOfGamefield() {
		return getCurrentGameField().getWidth();
	}

	public int getHeightOfGamefield() {
		return getCurrentGameField().getHeight();
	}

	public void updateGameField(int secondsMovedInGame) {
		try {
			getCurrentGameField().update(secondsMovedInGame);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public List<GamefieldDTO> getAllGamefields() {
		List<GamefieldDTO> gamefieldDTOs = new ArrayList<GamefieldDTO>();
		GamefieldToGamefieldDTOMapper gamefieldToGamefieldDTOMapper = new GamefieldToGamefieldDTOMapper();
		for (Gamefield gamefield : gamefieldRepository.getAllGamefields()) {
			gamefieldDTOs.add(gamefieldToGamefieldDTOMapper.mapToDTO(gamefield));
		}
		return gamefieldDTOs;
	}

	public boolean isGamePaused() {
		return PauseManager.isGamePaused();
	}

	public void useInRangeOfPlayer() {
		UsingComponent component = PlayerManager.getCurrentPlayer().getComponent(USING_COMPONENT);
		component.setUsing(true);
		getCurrentGameField().checkOnUseTriggers();
	}

	public float getXPosition() {
		return getCurrentGameField().getX();
	}

	public float getYPosition() {
		return getCurrentGameField().getY();
	}

	public String getTexture() {
		return getCurrentGameField().getTexture();
	}

	public List<LightDTO> getLightsFromWorld() {
		List<LightDTO> lights = new ArrayList<LightDTO>();
		for (Light light : getCurrentGameField().getLightsFromWorld()) {
			LightDTO dto = LightToLightDTOMapper.mapLightToDTO(light);
			lights.add(dto);
		}
		return lights;
	}

	public boolean isCreationMode() {
		return getCurrentGameField().isCreationMode();
	}

	public List<ItemDTO> getItemsThatCanBePickedUpByPlayer()  {
		return ItemDTOMapper.mapItemsToItemDTOs(getCurrentGameField().getItemsThatCanBePickedUpByPlayer());
	}

	public List<ConstructionSpriteDTO> getConstructionSpritesFromWorld()  {
		return ConstructionSpriteDTOMapper.mapConstructionSpritesToDTOs(getCurrentGameField().getConstructionItems());
	}

	public List<EnemyDTO> getEnemiesInWorld()  {
		return OrganicSpriteDTOMapper.mapEnemiesToDTOs(getCurrentGameField().getEnemiesInWorld());
	}

	public List<SpriteDTO> getSpritesOnPointInGamefield(Point point)  {
		return SpriteDTOMapper.doAutoMappingForSprites(getCurrentGameField().getSpritesCollidingWithPoint(point));
	}

	public List<AmmoDTO> getAmmoInWorld() {
		return new AmmoToAmmoDTOMapper().mapSpritesToDTO(getCurrentGameField().getAmmoInWorld());
	}

	public List<ItemDTO> getItemsOnGroundInWorld()  {
		return ItemDTOMapper.mapItemsToItemDTOs(getCurrentGameField().getGroundItems());
	}

	public void initializeGameField(GamefieldDTO gamefieldDTO, boolean creator) {
		try {
			Gamefield gamefield = gamefieldRepository.getGamefield(gamefieldDTO.getId());
			if(creator) gamefield.setGamefieldUpdater(new GamefieldCreatorUpdater());
			setCurrentGameField(gamefield);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void initializeGameFieldWithName(String name, boolean creator) {
		try {
			Gamefield field = gamefieldRepository.getGamefield(name);
			if(creator) field.setGamefieldUpdater(new GamefieldCreatorUpdater());
			setCurrentGameField(field);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void createNewGamefield(String gamefieldName, int width, int height) {
		try {
			Gamefield createGamefield = gamefieldRepository.createGamefield(gamefieldName, width, height);
			new GamefieldLoaderSaver().saveEntireField(createGamefield);
			setCurrentGameField(createGamefield);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void addConstructionSpriteToWorld(int id) {
		ConstructionSprite constructionSprite = (ConstructionSprite) spriteRepository.getSprite(id);
		if(constructionSprite == null){
			throw new ModelException("A constructionSprite with this id doesn't exist.");
		}
		getCurrentGameField().addConstructionItem(constructionSprite);
	}

	public void addEnemyToWorld(int id)  {
		Enemy enemy = (Enemy) spriteRepository.getSprite(id);
		if(enemy == null){
			throw new ModelException("An enemy with this id doesn't exist.");
		}
		getCurrentGameField().addEnemyToWorld(enemy);
	}

	public void addLightToWorld(int id) {
		Light light = (Light) spriteRepository.getSprite(id);
		if(light == null){
			throw new ModelException("A light with this id doesn't exist.");
		}
		getCurrentGameField().addLight(light);
	}

	public void addGroundItemToWorld(int id) {
		Item item = (Item) spriteRepository.getSprite(id);
		getCurrentGameField().addGroundItem(item);
	}

	public GamefieldDTO getGamefieldWithName(String fieldName) {
		Gamefield gamefield = gamefieldRepository.getGamefield(fieldName);
		return new GamefieldToGamefieldDTOMapper().mapToDTO(gamefield);
	}

//	public void removeSpriteFromWorld(SpriteDTO sprite) {
//		if (sprite instanceof ConstructionSpriteDTO) {
//			removeConstructionSpriteFromWorld(sprite.getId());
//		}
//		if (sprite instanceof ItemDTO) {
//			removeGroundItemFromWorld(sprite.getId());
//		}
//		if (sprite instanceof LightDTO) {
//			removeLightFromWorld(sprite.getId());
//		}
//		if (sprite instanceof EnemyDTO) {
//			removeEnemyFromWorld(sprite.getId());
//		}
//	}

//	public void updateSpriteInGamefield(SpriteDTO spriteDTO)  {
//		try {
//			if (spriteDTO instanceof ConstructionSpriteDTO) {
//				ConstructionSprite constructionSprite = ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite((ConstructionSpriteDTO) spriteDTO);
//				getCurrentGameField().updateConstructionSprite(constructionSprite);
//			}
//			if (spriteDTO instanceof ItemDTO) {
//				Item item = ItemDTOMapper.mapItemDTOToItem((ItemDTO) spriteDTO);
//				getCurrentGameField().updateGroundItem(item);
//			}
//			if (spriteDTO instanceof LightDTO) {
//				Light light = LightToLightDTOMapper.mapLightDTOToLight((LightDTO) spriteDTO);
//				getCurrentGameField().updateLight(light);
//			}
//			if (spriteDTO instanceof EnemyDTO) {
//				Enemy enemy = OrganicSpriteDTOMapper.mapEnemyDTOToEnemy((EnemyDTO) spriteDTO);
//				getCurrentGameField().updateEnemy(enemy);
//			}
//		} catch (Exception e) {
//			throw new ModelException(e);
//		}
//	}

	public boolean isDTOCollidingWithConstructionItem(SpriteDTO selectedSprite) {
		Sprite sprite = SpriteDTOMapper.doAutoMappingForSpriteDTO(selectedSprite);
		return getCurrentGameField().spriteAgainstAnyConstructionItem(sprite);
	}

	public boolean isGamefieldInitialized() {
		return GameFieldManager.isGamefieldInitialized();
	}
}
