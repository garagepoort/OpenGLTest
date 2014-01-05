package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;

public class ConstructionSpriteFacade {

	private ConstructionSpriteRepository constructionSpriteRepository  = new ConstructionSpriteRepository();
	
	public ConstructionSpriteDTO createConstructionSprite(ConstructionSpriteDTO constructionSpriteDTO) {
		try {
			ConstructionSprite constructionSprite = mapConstructionSpriteDTOToConstructionSprite(constructionSpriteDTO);
			constructionSpriteRepository.createSprite(constructionSprite);
			constructionSpriteDTO.setId(constructionSprite.getID());
			return constructionSpriteDTO;
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteConstructionSprite(int id) {
		throw new RuntimeException("Not yet implemented");
	}
}
