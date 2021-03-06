package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class ConstructionSpriteFacade {

	private SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();
	
	public ConstructionSpriteDTO createConstructionSprite(ConstructionSpriteDTO constructionSpriteDTO) {
		try {
			ConstructionSprite constructionSprite = mapConstructionSpriteDTOToConstructionSprite(constructionSpriteDTO);
			spriteRepository.createSprite(constructionSprite);
			constructionSpriteDTO.setId(constructionSprite.getID());
			return constructionSpriteDTO;
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

	public void deleteConstructionSprite(int id) {
		spriteRepository.deleteSprite(id);
	}

	public void updateConstructionSprite(ConstructionSpriteDTO constructionSpriteDTO) {
		try {
			ConstructionSprite constructionSprite = ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite(constructionSpriteDTO);
			spriteRepository.updateSprite(constructionSprite);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

}
