package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.metric.Point;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class LightFacade {

	private SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();
	
	public LightDTO createLight(Point point, ColorDTO colorDTO, int radius, boolean lighton)  {
		try {
			Color color = new Color(colorDTO.getRed(), colorDTO.getGreen(), colorDTO.getBlue());
			Light light = new Light(point.x, point.y, color, radius, lighton);
			spriteRepository.createSprite(light);
			new LightToLightDTOMapper();
			return LightToLightDTOMapper.mapLightToDTO(light);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteLight(int id) {
		spriteRepository.deleteSprite(id);
	}

	public void updateLight(LightDTO lightDTO) {
		try {
			Light light = LightToLightDTOMapper.mapLightDTOToLight(lightDTO);
			spriteRepository.updateSprite(light);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
