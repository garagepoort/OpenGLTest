package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.ItemFactory;

public class ItemDTOMapper {

	public static ItemDTO mapItemToItemDTO(Item item) {
		ItemType itemType = ItemType.valueOf(item.getType().toString());
		ItemDTO itemDTO = new ItemDTO(itemType);
		SpriteDTOMapper.mapSpriteToSpriteDTO(itemDTO, item);
		itemDTO.setInfoText(item.getInfoText());
		return itemDTO;
	}
	
	public static Item mapItemDTOToItem(ItemDTO itemDTO)  {
		if (itemDTO == null) {
			throw new MapperException("Not the right type: " + itemDTO);
		}
		Item item = null;
		if(itemDTO.getType() == ItemType.HEALTHPOTION){
			item = ItemFactory.createHealthPotion(0, 0, 20);
		}
		if(itemDTO.getType() == ItemType.HEALTNECKLACE){
			item = ItemFactory.createHealthNecklace(0, 0, 20);
		}
		SpriteDTOMapper.mapSpriteDTOToSprite(item, itemDTO);
		item.setInfoText(itemDTO.getInfoTekst());
		return item;
	}

	public static List<ItemDTO> mapItemsToItemDTOs(List<Item> list) {
		List<ItemDTO> itemDTOs = new ArrayList<>();
		for(Item item : list){
			itemDTOs.add(mapItemToItemDTO(item));
		}
		return itemDTOs;
	}

	// public List<ItemDTO> mapSpritesToDTO(List<Item> sprites) {
	// ArrayList<ItemDTO> dtos = new ArrayList<>();
	// for(Item item : sprites){
	// dtos.add(mapSpriteToDTO(item));
	// }
	// return dtos;
	// }

}
