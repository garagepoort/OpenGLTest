package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.AmmoDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.domain.sprite.item.weapon.Ammo;

public class AmmoToAmmoDTOMapper {


	protected void mapAmmo(AmmoDTO ammoDTO, Ammo ammo) {
		SpriteDTOMapper.mapSpriteToSpriteDTO(ammoDTO, ammo);
		ammoDTO.setDamage(ammo.getDamage());
		ammoDTO.setOnGround(ammo.isOnGround());
	}

	public AmmoDTO mapSpriteToDTO(Ammo ammo) {
		ItemType itemType = ItemType.valueOf(ammo.getType().toString());
		AmmoDTO ammoDTO = new AmmoDTO(itemType);
		mapAmmo(ammoDTO, ammo);
		return ammoDTO;
	}

	public List<AmmoDTO> mapSpritesToDTO(List<Ammo> sprites) {
		ArrayList<AmmoDTO> dtos = new ArrayList<>();
		for(Ammo ammo : sprites){
			dtos.add(mapSpriteToDTO(ammo));
		}
		return dtos;
	}
	
	

}
