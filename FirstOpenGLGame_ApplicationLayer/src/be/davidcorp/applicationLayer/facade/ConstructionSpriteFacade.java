package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;

public class ConstructionSpriteFacade {

	private ConstructionSpriteRepository constructionSpriteRepository  = new ConstructionSpriteRepository();
	
	public ConstructionSpriteDTO createWall(ConstructionSpriteDTO constructionSpriteDTO) {
		try {
			Wall wall = ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToWall(constructionSpriteDTO);
			constructionSpriteRepository.createSprite(wall);
			constructionSpriteDTO.setId(wall.getID());
			return constructionSpriteDTO;
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
