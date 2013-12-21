package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.Wall;

public class ConstructionSpriteDTOMapper {

	public static ConstructionSpriteDTO mapWallToConstructionSpriteDTO(Wall wall){
		ConstructionSpriteDTO constructionSpriteDTO = new ConstructionSpriteDTO(ConstructionType.WALL);
		mapConstructionSprite(constructionSpriteDTO, wall);
		return constructionSpriteDTO;
	}
	
	public static Wall mapConstructionSpriteDTOToWall(ConstructionSpriteDTO constructionSpriteDTO) {
		if(constructionSpriteDTO.getConstructionSpriteType() != ConstructionType.WALL){
			throw new MapperException("The type does not equal Wall");
		}
		Wall wall = new Wall();
		mapConstructionSpriteDTO(wall, constructionSpriteDTO);
		return wall;
	}

	private static void mapConstructionSprite(ConstructionSpriteDTO spriteDTO, ConstructionSprite sprite) {
		SpriteDTOMapper.mapSpriteToSpriteDTO(spriteDTO, sprite);
		spriteDTO.initializeSegments();
	}
	
	private static void mapConstructionSpriteDTO(ConstructionSprite constructionSprite, ConstructionSpriteDTO spriteDTO)  {
		SpriteDTOMapper.mapSpriteDTOToSprite(constructionSprite, spriteDTO);
	}

	public List<ConstructionSpriteDTO> mapWallsToConstructionSpriteDTOs(List<Wall> sprites) {
		ArrayList<ConstructionSpriteDTO> dtos = new ArrayList<>();
		for(Wall wall : sprites){
			dtos.add(mapWallToConstructionSpriteDTO(wall));
		}
		return dtos;
	}
	
	public static List<ConstructionSpriteDTO> doAutoMappingForConstructionSprites(List<ConstructionSprite> constructionSprites)  {
		ArrayList<ConstructionSpriteDTO> result = new ArrayList<ConstructionSpriteDTO>();
		for (ConstructionSprite constructionSprite : constructionSprites) {
			ConstructionSpriteDTO constructionSpriteDTO = doAutoMappingForConstructionSprite(constructionSprite);
			result.add(constructionSpriteDTO);
		}
		return result;
	}
	
	
	
	public static ConstructionSpriteDTO doAutoMappingForConstructionSprite(ConstructionSprite constructionSprite)  {
		ConstructionSpriteDTO constructionSpriteDTO = null;
		if(constructionSprite instanceof Wall){
			constructionSpriteDTO = ConstructionSpriteDTOMapper.mapWallToConstructionSpriteDTO((Wall) constructionSprite);
		}
		if(constructionSpriteDTO == null){
			throw new MapperException("No mapping found for: " + constructionSprite.getClass().getCanonicalName());
		}
		return constructionSpriteDTO;
	}
	
}
