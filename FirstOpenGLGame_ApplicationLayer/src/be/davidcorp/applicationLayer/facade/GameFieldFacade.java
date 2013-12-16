package be.davidcorp.applicationLayer.facade;

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
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.exception.GameFieldException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.loaderSaver.LoaderException;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.GamefieldRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.metric.Point;

public class GameFieldFacade {

	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	private EnemyRepository enemyRepository = new EnemyRepository();
	private LightRepository lightRepository = new LightRepository();
	private ItemRepository itemRepository = new ItemRepository();
	private ConstructionSpriteRepository constructionSpriteRepository = new ConstructionSpriteRepository();

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
		} catch (GameFieldException e) {
			e.printStackTrace();
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

	public void useContructionInRangeOfPlayer() {
		getCurrentGameField().useContructionItem();
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

	public List<ItemDTO> getItemsThatCanBePickedUpByPlayer() throws MapperException {
		return ItemDTOMapper.doAutoMappingForItems(getCurrentGameField().getItemsThatCanBePickedUpByPlayer());
	}

	public List<ConstructionSpriteDTO> getConstructionSpritesFromWorld() throws MapperException {
		return ConstructionSpriteDTOMapper.doAutoMappingForConstructionSprites(getCurrentGameField().getConstructionItems());
	}

	public void removeConstructionSpriteFromWorld(int id) {
		Sprite sprite = constructionSpriteRepository.getSprite(id);
		getCurrentGameField().removeSpriteFromWorld(sprite);
	}

	public void removeEnemyFromWorld(int id) {
		Sprite sprite = enemyRepository.getSprite(id);
		getCurrentGameField().removeSpriteFromWorld(sprite);
	}

	public void removeLightFromWorld(int id) {
		Sprite sprite = lightRepository.getSprite(id);
		getCurrentGameField().removeSpriteFromWorld(sprite);
	}

	public void removeGroundItemFromWorld(int id) {
		Sprite sprite = itemRepository.getSprite(id);
		getCurrentGameField().removeSpriteFromWorld(sprite);
	}

	public List<EnemyDTO> getEnemiesInWorld() throws MapperException {
		return OrganicSpriteDTOMapper.doAutoMappingForEnemies(getCurrentGameField().getEnemiesInWorld());
	}

	public List<SpriteDTO> getSpritesOnPointInGamefield(Point point) throws MapperException {
		return SpriteDTOMapper.doAutoMappingForSprites(getCurrentGameField().getSpritesCollidingWithPoint(point));
	}

	public List<AmmoDTO> getAmmoInWorld() {
		return new AmmoToAmmoDTOMapper().mapSpritesToDTO(getCurrentGameField().getAmmoInWorld());
	}

	public List<ItemDTO> getItemsOnGroundInWorld() throws MapperException {
		return ItemDTOMapper.doAutoMappingForItems(getCurrentGameField().getGroundItems());
	}

	public void initializeGameField(GamefieldDTO gamefieldDTO) throws ModelException {
		try {
			Gamefield gamefield = gamefieldRepository.getGamefield(gamefieldDTO.getId());
			setCurrentGameField(gamefield);
		} catch (LoaderException e) {
			throw new ModelException(e);
		}
	}

	public void initializeGameFieldWithName(String name) throws ModelException {
		try {
			Gamefield field = gamefieldRepository.getGamefield(name);
			setCurrentGameField(field);
		} catch (LoaderException e) {
			throw new ModelException(e);
		}
	}

	public void createNewGamefield(String gamefieldName, int width, int height) throws ModelException {
		try {
			gamefieldRepository.createGamefield(gamefieldName, width, height);
		} catch (GameFieldException | SpriteException e) {
			throw new ModelException(e);
		}
	}

	public void addConstructionSpriteToWorld(int id) {
		ConstructionSprite constructionSprite = constructionSpriteRepository.getSprite(id);
		getCurrentGameField().addConstructionItem(constructionSprite);
	}

	public void addEnemyToWorld(int id) throws ModelException {
		Enemy enemy = enemyRepository.getSprite(id);
		getCurrentGameField().addEnemyToWorld(enemy);
	}

	public void addLightToWorld(int id) {
		Light light = lightRepository.getSprite(id);
		getCurrentGameField().addLight(light);
	}

	public void addGroundItemToWorld(int id) {
		Item item = itemRepository.getSprite(id);
		getCurrentGameField().addGroundItem(item);
	}

	public GamefieldDTO getGamefieldWithName(String fieldName) {
		Gamefield gamefield = gamefieldRepository.getGamefield(fieldName);
		return new GamefieldToGamefieldDTOMapper().mapToDTO(gamefield);
	}

	public void removeSpriteFromWorld(SpriteDTO sprite) throws ModelException {
		if (sprite instanceof ConstructionSpriteDTO) {
			removeConstructionSpriteFromWorld(sprite.getId());
		}
		if (sprite instanceof ItemDTO) {
			removeGroundItemFromWorld(sprite.getId());
		}
		if (sprite instanceof LightDTO) {
			removeLightFromWorld(sprite.getId());
		}
		if (sprite instanceof EnemyDTO) {
			removeEnemyFromWorld(sprite.getId());
		}
	}

	public void updateSpriteInGamefield(SpriteDTO sprite) throws ModelException {
		try {
			if (sprite instanceof ConstructionSpriteDTO) {
				ConstructionSprite constructionSprite = ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToWall((ConstructionSpriteDTO) sprite);
				constructionSpriteRepository.updateSprite(constructionSprite);
			}
			if (sprite instanceof ItemDTO) {
				removeGroundItemFromWorld(sprite.getId());
			}
			if (sprite instanceof LightDTO) {
				removeLightFromWorld(sprite.getId());
			}
			if (sprite instanceof EnemyDTO) {
				removeEnemyFromWorld(sprite.getId());
			}
		} catch (SpriteException | MapperException e) {
			throw new ModelException(e);
		}
	}
}