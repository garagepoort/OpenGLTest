package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.component.ComponentType.USING_COMPONENT;
import static be.davidcorp.domain.game.CurrentGameFieldManager.getCurrentGameField;
import static be.davidcorp.domain.game.CurrentGameFieldManager.loadGamefieldFromExistingGamefield;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import be.davidcorp.applicationLayer.dto.AmmoDTO;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.AmmoToAmmoDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.OrganicSpriteDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.SpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.component.UsingComponent;
import be.davidcorp.domain.game.CurrentGameFieldManager;
import be.davidcorp.domain.game.GamefieldCreatorUpdater;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Ammo;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.metric.Point;
import be.davidcorp.repository.SpriteRepository;

@Named
public class GameFieldFacade {

	@Inject
	private SpriteRepository spriteRepository;

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
		for (Sprite sprite : getCurrentGameField().getSpritesInWorld()) {
			if(sprite instanceof Light){
				LightDTO dto = LightToLightDTOMapper.mapLightToDTO((Light) sprite);
				lights.add(dto);
			}
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
		List<ConstructionSprite> constructionSprites = newArrayList();
		for (Sprite sprite : getCurrentGameField().getSpritesInWorld()) {
			if(sprite instanceof ConstructionSprite) constructionSprites.add((ConstructionSprite) sprite);
		}
		return ConstructionSpriteDTOMapper.mapConstructionSpritesToDTOs(constructionSprites);
	}

	public List<EnemyDTO> getEnemiesInWorld()  {
		List<Enemy> enemies = newArrayList();
		for (Sprite sprite : getCurrentGameField().getSpritesInWorld()) {
			if(sprite instanceof Enemy) enemies.add((Enemy) sprite);
		}
		return OrganicSpriteDTOMapper.mapEnemiesToDTOs(enemies);
	}

	public List<SpriteDTO> getSpritesOnPointInGamefield(Point point)  {
		return SpriteDTOMapper.doAutoMappingForSprites(getCurrentGameField().getSpritesCollidingWithPoint(point));
	}

	public List<AmmoDTO> getAmmoInWorld() {
		List<Ammo> ammo = newArrayList();
		for (Sprite sprite : getCurrentGameField().getSpritesInWorld()) {
			if(sprite instanceof Ammo && ((Ammo) sprite).isOnGround() == false) ammo.add((Ammo) sprite);
		}
		return new AmmoToAmmoDTOMapper().mapSpritesToDTO(ammo);
	}

	public List<ItemDTO> getItemsOnGroundInWorld()  {
		List<Item> items = newArrayList();
		for (Sprite sprite : getCurrentGameField().getSpritesInWorld()) {
			if(sprite instanceof Item && ((Item) sprite).isOnGround()) items.add((Item) sprite);
		}
		return ItemDTOMapper.mapItemsToItemDTOs(items);
	}

	public void initializeGameField(String name, boolean creator) {
		try {
			loadGamefieldFromExistingGamefield(name);
			if(creator) getCurrentGameField().setGamefieldUpdater(new GamefieldCreatorUpdater());
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void addSpriteToWorld(int id){
		Sprite sprite = spriteRepository.getSprite(id);
		if(sprite == null){
			throw new ModelException("A sprite with this id doesn't exist.");
		}
		getCurrentGameField().addSpriteToWorld(sprite);
	}
	
	public boolean isDTOCollidingWithConstructionItem(SpriteDTO selectedSprite) {
		Sprite sprite = SpriteDTOMapper.doAutoMappingForSpriteDTO(selectedSprite);
		return getCurrentGameField().spriteAgainstAnyConstructionItem(sprite);
	}

	public boolean isGamefieldInitialized() {
		return CurrentGameFieldManager.isGamefieldInitialized();
	}

}
