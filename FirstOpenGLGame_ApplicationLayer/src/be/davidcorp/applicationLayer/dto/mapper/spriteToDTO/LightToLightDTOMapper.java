package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.light.Light;

public class LightToLightDTOMapper {

	public static LightDTO mapLightToDTO(Light light) {
		LightDTO lightDTO = new LightDTO();
		SpriteDTOMapper.mapSpriteToSpriteDTO(lightDTO, light);
		lightDTO.setRadius(light.getRadius());
		lightDTO.setLightOn(light.isLightOn());
		return lightDTO;
	}

	public static Light mapDTOToLight(LightDTO lightDTO) throws MapperException {
		try {
			Light light = new Light(0, 0, null, 0, false);
			SpriteDTOMapper.mapSpriteToSpriteDTO(lightDTO, light);
			light.setRadius(lightDTO.getRadius());
			light.setLightOn(lightDTO.isLightOn());
			return light;
		} catch (SpriteException e) {
			throw new MapperException(e);
		}
	}

}
