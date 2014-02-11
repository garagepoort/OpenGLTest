package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.LightToLightDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.metric.Point;

public class LightFacade {

	private LightRepository lightRepository = new LightRepository();
	
	public LightDTO createLight(Point point, ColorDTO colorDTO, int radius, boolean lighton)  {
		try {
			Color color = new Color(colorDTO.getRed(), colorDTO.getGreen(), colorDTO.getBlue());
			Light light = new Light(point.x, point.y, color, radius, lighton);
			lightRepository.createSprite(light);
			new LightToLightDTOMapper();
			return LightToLightDTOMapper.mapLightToDTO(light);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteLight(int id) {
		lightRepository.deleteSprite(id);
		GameFieldManager.getCurrentGameField().removeLightFromWorld(id);
	}

	public void updateLight(LightDTO lightDTO) {
		try {
			Light light = LightToLightDTOMapper.mapLightDTOToLight(lightDTO);
			lightRepository.updateSprite(light);
			getCurrentGameField().updateLight(light);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
