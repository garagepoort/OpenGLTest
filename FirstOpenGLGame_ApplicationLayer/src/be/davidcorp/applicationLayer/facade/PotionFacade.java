package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.loaderSaver.repository.ItemRepository;

public class PotionFacade {

	private ItemRepository itemRepository = new ItemRepository();
	
	public ItemDTO createHealthPotion(float x, float y)  {
		try {
			HealthPotion healthPotion = new HealthPotion(x, y);
			itemRepository.createSprite(healthPotion);
			return ItemDTOMapper.mapHealthPotionToItemDTO(healthPotion);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public ItemDTO createStaminaPotion(float x, float y) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
