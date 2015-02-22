package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.weapon.RangedWeapon;
import be.davidcorp.domain.sprite.item.weapon.WeaponFactory;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class WeaponFacade {
	private SpriteRepository  spriteRepository = DefaultSpriteRepository.getInstance();

	public ItemDTO createPistol(float x, float y)  {
		RangedWeapon pistol;
		try {
			pistol = WeaponFactory.createPistol(x, y);
			spriteRepository.createSprite(pistol);
			return ItemDTOMapper.mapItemToItemDTO(pistol);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
	
	
}
