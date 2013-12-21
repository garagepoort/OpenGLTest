package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import static be.davidcorp.domain.sprite.organic.player.PlayerManager.getCurrentPlayer;
import be.davidcorp.WindDirection;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.domain.exception.InventoryException;
import be.davidcorp.domain.exception.SkillException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.PauseManager;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.domain.utilities.sprite.SpriteRotator;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.metric.Point;
import be.davidcorp.metric.Vector;
import be.davidcorp.texture.TextureBunch;

public class PlayerFacade {

	private ItemRepository itemRepository = new ItemRepository();
	
	public void letPlayerAttackInDirection(Vector vector) {
		getCurrentPlayer().useWeapon(vector);
	}

	public void turnFlaslightOnOff() {
		getCurrentPlayer().turnFlaslightOnOff();
	}

	public float getX() {
		return getCurrentPlayer().getX();
	}

	public float getY() {
		return getCurrentPlayer().getY();
	}
	
	public void lookInDirection(Vector v) {
		getCurrentPlayer().setDirectionVector(v);
		getCurrentPlayer().setRotationAngle(SpriteRotator.calcRotationAngle(getCurrentPlayer().getDirectionVector()));
	}

	
	public int getHeight() {
		return getCurrentPlayer().getHeight();
	}

	
	public int getWidth() {
		return getCurrentPlayer().getWidth();
	}

	
	public void movePlayer(WindDirection direction, float distance) {
		if (!PauseManager.isGamePaused()) {
			SpriteMovingUtility.movePlayerInGameField(direction, distance);
		}
	}

	
	public boolean isPlayerMoving() {
		return getCurrentPlayer().isMoving();
	}

	
	public void useSkill(int i) {
		try {
			PlayerManager.getCurrentPlayer().useSkill(i, PlayerManager.getCurrentPlayer());
		} catch (SkillException e) {
			e.printStackTrace();
		}
	}

	
	public void dropItemFromEquipment(ItemDTO itemDTO) {
		Item item = itemRepository.getSprite(itemDTO.getId());
		item.setX(getCurrentPlayer().getX());
		item.setY(getCurrentPlayer().getY());
		getCurrentGameField().addGroundItem(item);
		getCurrentPlayer().dropItem(item);
		getCurrentPlayer().getEquipment().unequipItem(item);
	}

	
	public float getSpeed() {
		return getCurrentPlayer().getSpeed();
	}

	
	public Point getCenter() {
		return getCurrentPlayer().getCenter();
	}

	
	public void unequipWeapon() {
		getCurrentPlayer().unequipWeapon();
	}

	public void putItemInInventory(ItemDTO itemDTO) {
		try {
			Item item = itemRepository.getSprite(itemDTO.getId());
			getCurrentPlayer().addItemToInventory(item);
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}

	
	public boolean isFlashLightOn() {
		return getCurrentPlayer().isFlashLightOn();
	}

	
	public float getRotationAngle() {
		return getCurrentPlayer().getRotationAngle();
	}

	
	public TextureBunch getTextureBunch() {
		return getCurrentPlayer().getTextureBunch();
	}

	
	public LightDTO getFlashLight() {
		new LightToLightDTOMapper();
		return LightToLightDTOMapper.mapLightToDTO(PlayerManager.getCurrentPlayer().getFlashLight());
	}

	
	public LightDTO getLineOfSight() {
		new LightToLightDTOMapper();
		return LightToLightDTOMapper.mapLightToDTO(PlayerManager.getCurrentPlayer().getLineOfSight());
	}

	
	public void pickUpitem(ItemDTO itemDTO) {
		Item item = itemRepository.getSprite(itemDTO.getId());
		try {
			GameFieldManager.getCurrentGameField().removeSpriteFromWorld(item);
			getCurrentPlayer().addItemToInventory(item);
		} catch (InventoryException e) {
			e.printStackTrace();
		}
	}

	
	public double getStamina() {
		return getCurrentPlayer().getStamina();
	}

	
	public double getHealthPoints() {
		return getCurrentPlayer().getHealthPoints();
	}

	
	public double getMaxStamina() {
		return getCurrentPlayer().getMaxStamina();
	}

	
	public double getMaxHealthPoints() {
		return getCurrentPlayer().getMaxHealthPoints();
	}

	
	public void dropItem(ItemDTO itemDTO) {
		Item item = itemRepository.getSprite(itemDTO.getId());
		getCurrentPlayer().dropItem(item);
		getCurrentGameField().addGroundItem(item);
	}

	
	public void useItem(ItemDTO itemDTO) {
		Item item = itemRepository.getSprite(itemDTO.getId());
		item.useItem(getCurrentPlayer());
	}

	
	public ItemDTO getPlayerWeapon() {
		if(getCurrentPlayer().getEquippedWeapon() == null) return null;
		return ItemDTOMapper.doAutoMappingForItem(getCurrentPlayer().getEquippedWeapon());
	}

}
