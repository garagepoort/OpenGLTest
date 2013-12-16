package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.loaderSaver.repository.SpriteRepositoryException;
import be.davidcorp.metric.Point;

public class LightFacade {

	private LightRepository lightRepository = new LightRepository();
	
	public LightDTO createLight(Point point, ColorDTO colorDTO, int radius, boolean lighton) throws ModelException {
		try {
			Color color = new Color(colorDTO.getRed(), colorDTO.getGreen(), colorDTO.getBlue());
			Light light = new Light(point.x, point.y, color, radius, lighton);
			lightRepository.createSprite(light);
			new LightToLightDTOMapper();
			return LightToLightDTOMapper.mapLightToDTO(light);
		} catch (SpriteException | SpriteRepositoryException e) {
			throw new ModelException(e);
		}
	}

	
}
