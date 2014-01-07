package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite;
import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ConstructionSpriteDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;

public class ConstructionSpriteFacade {

	private ConstructionSpriteRepository constructionSpriteRepository = new ConstructionSpriteRepository();

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

	public void updateConstructionSprite(ConstructionSpriteDTO constructionSpriteDTO) {
		try {
			ConstructionSprite constructionSprite = ConstructionSpriteDTOMapper.mapConstructionSpriteDTOToConstructionSprite(constructionSpriteDTO);
			constructionSpriteRepository.updateSprite(constructionSprite);
			getCurrentGameField().updateConstructionSprite(constructionSprite);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}

}
