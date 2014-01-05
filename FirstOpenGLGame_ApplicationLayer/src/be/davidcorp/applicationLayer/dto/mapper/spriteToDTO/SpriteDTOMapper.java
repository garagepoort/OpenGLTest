package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.Color;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;

public class SpriteDTOMapper{
	
	public static SpriteDTO doAutoMappingForSprite(Sprite sprite) {
		SpriteDTO spriteDTO = null;
		if(sprite instanceof Item){
			spriteDTO = ItemDTOMapper.mapItemToItemDTO((Item) sprite);
		}
		if(sprite instanceof Enemy){
			spriteDTO = OrganicSpriteDTOMapper.mapEnemyToEnemyDTO((Enemy) sprite);
		}
		if(sprite instanceof Light){
			spriteDTO = LightToLightDTOMapper.mapLightToDTO((Light) sprite);
		}
		if(sprite instanceof ConstructionSprite){
			spriteDTO = ConstructionSpriteDTOMapper.mapConstructionSpriteToConstructionSpriteDTO((ConstructionSprite) sprite);
		}
		if(spriteDTO == null){
			throw new MapperException("No mapping found for sprite: "+ sprite.getClass().getCanonicalName());
		}
		return spriteDTO;
	}
	
	public static List<SpriteDTO> doAutoMappingForSprites(List<Sprite> sprites)  {
		ArrayList<SpriteDTO> result = new ArrayList<SpriteDTO>();
		for (Sprite sprite : sprites) {
			result.add(doAutoMappingForSprite(sprite));
		}
		return result;
	}
	
	public static void mapSpriteToSpriteDTO(SpriteDTO spriteDTO, Sprite sprite){
		spriteDTO.setId(sprite.getID());
		
		spriteDTO.setX(sprite.getX());
		spriteDTO.setY(sprite.getY());
		spriteDTO.setWidth(sprite.getWidth());
		spriteDTO.setHeight(sprite.getHeight());
		
		spriteDTO.setDownLeftPoint(sprite.getHitBox().getDownLeftPoint());
		spriteDTO.setDownRightPoint(sprite.getHitBox().getDownRightPoint());
		spriteDTO.setUpperRightPoint(sprite.getHitBox().getUpperRightPoint());
		spriteDTO.setUpperLeftPoint(sprite.getHitBox().getUpperLeftPoint());
		
		spriteDTO.setCenter(sprite.getCenter());
		
		spriteDTO.setTextureBunch(sprite.getTextureBunch());
		spriteDTO.setRotationAngle(sprite.getRotationAngle());
		
		if(sprite.getColor() != null){
			ColorDTO colorDTO = new ColorDTO(sprite.getColor().getRed(), sprite.getColor().getGreen(), sprite.getColor().getBlue());
			spriteDTO.setColor(colorDTO);
		}
	}
	
	public static void mapSpriteDTOToSprite(Sprite sprite, SpriteDTO spriteDTO) {
		try {
		sprite.setID(spriteDTO.getId());
		
		sprite.setX(spriteDTO.getX());
		sprite.setY(spriteDTO.getY());
			sprite.setWidth(spriteDTO.getWidth());
		sprite.setHeight(spriteDTO.getHeight());
		
		sprite.getHitBox().setDownLeftPoint(spriteDTO.getDownLeftPoint());
		sprite.getHitBox().setDownRightPoint(spriteDTO.getDownRightPoint());
		sprite.getHitBox().setUpperRightPoint(spriteDTO.getUpperRightPoint());
		sprite.getHitBox().setUpperLeftPoint(spriteDTO.getUpperLeftPoint());
		
		sprite.setTextureBunch(spriteDTO.getTextureBunch());
		sprite.setRotationAngle(spriteDTO.getRotationAngle());
		
		if(spriteDTO.getColor() != null){
			Color color = new Color(spriteDTO.getColor().getRed(), spriteDTO.getColor().getGreen(), spriteDTO.getColor().getBlue());
			sprite.setColor(color);
		}
		} catch (Exception e) {
			throw new MapperException(e);
		}
	}

}
