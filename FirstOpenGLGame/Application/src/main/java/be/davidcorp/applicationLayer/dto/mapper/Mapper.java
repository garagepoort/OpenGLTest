package be.davidcorp.applicationLayer.dto.mapper;

import java.util.List;

import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.domain.sprite.Sprite;

public interface Mapper<DTO extends SpriteDTO, SPRITE extends Sprite> {

	public DTO mapSpriteToDTO(SPRITE sprite);
	
	public List<DTO> mapSpritesToDTO(List<SPRITE> sprites);
}
