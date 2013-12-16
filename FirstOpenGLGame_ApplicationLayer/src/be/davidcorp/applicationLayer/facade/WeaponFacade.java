package be.davidcorp.applicationLayer.facade;

import java.io.IOException;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.loaderSaver.repository.SpriteRepositoryException;
import be.davidcorp.loaderSaver.repository.WeaponRepository;

public class WeaponFacade {
	private WeaponRepository  weaponRepository = new WeaponRepository();

	public ItemDTO createPistol(float x, float y, int ammo) throws ModelException {
		Pistol pistol;
		try {
			pistol = new Pistol(x, y, ammo);
			pistol = (Pistol) weaponRepository.createSprite(pistol);
			return ItemDTOMapper.mapPistolToItemDTO(pistol);
		} catch (SpriteException | IOException | SpriteRepositoryException e) {
			throw new ModelException(e);
		}
	}
	
	
}
