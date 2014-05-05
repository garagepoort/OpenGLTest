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
		itemDTO.setOnGround(item.isOnGround());
		return itemDTO;
	}
	
	public static Item mapItemDTOToItem(ItemDTO itemDTO)  {
		if (itemDTO == null) {
			throw new MapperException("Not the right type: " + itemDTO);
		}
		Item item = getItemFromType(itemDTO);
		SpriteDTOMapper.mapSpriteDTOToSprite(item, itemDTO);
		item.setInfoText(itemDTO.getInfoTekst());
		item.setOnGround(itemDTO.isOnGround());
		return item;
	}

	private static Item getItemFromType(ItemDTO itemDTO) {
		if(itemDTO.getType() == ItemType.HEALTHPOTION){
			return ItemFactory.createHealthPotion(0, 0, 20);
		}
		if(itemDTO.getType() == ItemType.HEALTNECKLACE){
			return ItemFactory.createHealthNecklace(0, 0, 20);
		}
		if(itemDTO.getType() == ItemType.PISTOL){
			return ItemFactory.createPistol(0,0);
		}
		throw new MapperException("No item found for this: " + itemDTO.getType());
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
