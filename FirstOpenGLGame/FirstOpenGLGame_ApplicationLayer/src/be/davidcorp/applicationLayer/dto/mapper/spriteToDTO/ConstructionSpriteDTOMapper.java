package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.ConstructionSpriteFactory;

public class ConstructionSpriteDTOMapper {

	public static ConstructionSpriteDTO mapConstructionSpriteToConstructionSpriteDTO(ConstructionSprite constructionSprite){
		ConstructionType constructionType = ConstructionType.valueOf(constructionSprite.getType().toString());
		ConstructionSpriteDTO constructionSpriteDTO = new ConstructionSpriteDTO(constructionType);
		mapConstructionSprite(constructionSpriteDTO, constructionSprite);
		return constructionSpriteDTO;
	}
	
	public static ConstructionSprite mapConstructionSpriteDTOToConstructionSprite(ConstructionSpriteDTO constructionSpriteDTO) {
		ConstructionSprite constructionSprite = null;
		if(constructionSpriteDTO.getConstructionSpriteType() == ConstructionType.WALL){
			constructionSprite = ConstructionSpriteFactory.createWall(0, 0, 1, 1);
		}
		mapConstructionSpriteDTO(constructionSprite, constructionSpriteDTO);
		return constructionSprite;
	}

	private static void mapConstructionSprite(ConstructionSpriteDTO spriteDTO, ConstructionSprite sprite) {
		SpriteDTOMapper.mapSpriteToSpriteDTO(spriteDTO, sprite);
		spriteDTO.initializeSegments();
	}
	
	private static void mapConstructionSpriteDTO(ConstructionSprite constructionSprite, ConstructionSpriteDTO spriteDTO)  {
		SpriteDTOMapper.mapSpriteDTOToSprite(constructionSprite, spriteDTO);
	}
	
	public static List<ConstructionSpriteDTO> mapConstructionSpritesToDTOs(List<ConstructionSprite> constructionItems) {
		ArrayList<ConstructionSpriteDTO> dtos = new ArrayList<>();
		for(ConstructionSprite constructionSprite : constructionItems){
			dtos.add(mapConstructionSpriteToConstructionSpriteDTO(constructionSprite));
		}
		return dtos;
	}
	
}
