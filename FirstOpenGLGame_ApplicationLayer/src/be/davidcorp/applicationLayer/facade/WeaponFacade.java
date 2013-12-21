package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.loaderSaver.repository.WeaponRepository;

public class WeaponFacade {
	private WeaponRepository  weaponRepository = new WeaponRepository();

	public ItemDTO createPistol(float x, float y, int ammo)  {
		Pistol pistol;
		try {
			pistol = new Pistol(x, y, ammo);
			pistol = (Pistol) weaponRepository.createSprite(pistol);
			return ItemDTOMapper.mapPistolToItemDTO(pistol);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
	
	
}
