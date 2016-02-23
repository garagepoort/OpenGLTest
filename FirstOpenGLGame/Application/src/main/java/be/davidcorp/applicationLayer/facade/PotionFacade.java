package be.davidcorp.applicationLayer.facade;

import javax.inject.Inject;
import javax.inject.Named;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.ItemFactory;
import be.davidcorp.repository.SpriteRepository;

@Named
public class PotionFacade {

	@Inject
	private SpriteRepository spriteRepository;
	
	public ItemDTO createHealthPotion(float x, float y)  {
		try {
			Item healthPotion = ItemFactory.createHealthPotion(x, y, 20);
			spriteRepository.createSprite(healthPotion);
			return ItemDTOMapper.mapItemToItemDTO(healthPotion);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public ItemDTO createStaminaPotion(float x, float y) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
