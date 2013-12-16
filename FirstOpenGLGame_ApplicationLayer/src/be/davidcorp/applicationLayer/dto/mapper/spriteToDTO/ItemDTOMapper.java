package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.weapon.Pistol;

public class ItemDTOMapper {

	private static void mapItem(ItemDTO itemDTO, Item item) {
		SpriteDTOMapper.mapSpriteToSpriteDTO(itemDTO, item);
		itemDTO.setInfoText(item.getInfoText());
	}

	public static ItemDTO mapHealthPotionToItemDTO(HealthPotion healthPotion) {
		ItemDTO itemDTO = new ItemDTO(ItemType.HEALTHPOTION);
		mapItem(itemDTO, healthPotion);
		return itemDTO;
	}
	
	public static ItemDTO mapPistolToItemDTO(Pistol pistol) {
		ItemDTO itemDTO = new ItemDTO(ItemType.PISTOL);
		mapItem(itemDTO, pistol);
		return itemDTO;
	}
	
	public static List<ItemDTO> doAutoMappingForItems(List<Item> items) throws MapperException{
		ArrayList<ItemDTO> result = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDTO = doAutoMappingForItem(item);
			result.add(itemDTO);
		}
		return result;
	}
	
	public static ItemDTO doAutoMappingForItem(Item item) throws MapperException {
		ItemDTO itemDTO = null;
		if (item instanceof HealthPotion) {
			itemDTO = ItemDTOMapper.mapHealthPotionToItemDTO((HealthPotion) item);
		}
		if (item instanceof Pistol) {
			itemDTO = ItemDTOMapper.mapPistolToItemDTO((Pistol) item);
		}
		if(itemDTO == null){
			throw new MapperException("No mapping found for "+ item.getClass().getCanonicalName());
		}
		return itemDTO;
	}

//	public List<ItemDTO> mapSpritesToDTO(List<Item> sprites) {
//		ArrayList<ItemDTO> dtos = new ArrayList<>();
//		for(Item item : sprites){
//			dtos.add(mapSpriteToDTO(item));
//		}
//		return dtos;
//	}

}
