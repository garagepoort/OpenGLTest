package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import static be.davidcorp.applicationLayer.dto.mapper.ItemType.HEALTHPOTION;
import static be.davidcorp.applicationLayer.dto.mapper.ItemType.PISTOL;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.weapon.Pistol;

public class ItemDTOMapper {

	public static ItemDTO mapHealthPotionToItemDTO(HealthPotion healthPotion) {
		ItemDTO itemDTO = new ItemDTO(ItemType.HEALTHPOTION);
		mapItemToItemDTO(itemDTO, healthPotion);
		return itemDTO;
	}

	public static HealthPotion mapItemDTOToHealthPotion(ItemDTO itemDTO)  {
		if (itemDTO == null || itemDTO.getType() != HEALTHPOTION) {
			throw new MapperException("Not the right type: " + itemDTO);
		}
		HealthPotion healthPotion = new HealthPotion(0, 0);
		mapItemDTOToItem(healthPotion, itemDTO);
		return healthPotion;
	}

	public static ItemDTO mapPistolToItemDTO(Pistol pistol) {
		ItemDTO itemDTO = new ItemDTO(ItemType.PISTOL);
		mapItemToItemDTO(itemDTO, pistol);
		return itemDTO;
	}
	public static Pistol mapItemDTOToPistol(ItemDTO itemDTO)  {
		if (itemDTO == null || itemDTO.getType() != PISTOL) {
			throw new MapperException("Not the right type: " + itemDTO);
		}
		Pistol pistol = new Pistol(0, 0, 0);
		mapItemDTOToItem(pistol, itemDTO);
		return pistol;
	}

	public static List<ItemDTO> doAutoMappingForItems(List<Item> items)  {
		ArrayList<ItemDTO> result = new ArrayList<ItemDTO>();
		for (Item item : items) {
			ItemDTO itemDTO = doAutoMappingForItem(item);
			result.add(itemDTO);
		}
		return result;
	}

	public static ItemDTO doAutoMappingForItem(Item item)  {
		if (item instanceof HealthPotion) {
			return ItemDTOMapper.mapHealthPotionToItemDTO((HealthPotion) item);
		}
		if (item instanceof Pistol) {
			return ItemDTOMapper.mapPistolToItemDTO((Pistol) item);
		}
		throw new MapperException("No mapping found for " + item.getClass().getCanonicalName());
	}

	public static Item doAutoMappingForItemDTO(ItemDTO itemDTO)  {
		if (itemDTO.getType() == HEALTHPOTION) {
			return mapItemDTOToHealthPotion(itemDTO);
		}
		if (itemDTO.getType() == PISTOL) {
			return mapItemDTOToHealthPotion(itemDTO);
		}
		throw new MapperException("No mapping found for type: " + itemDTO.getType());
	}

	private static void mapItemToItemDTO(ItemDTO itemDTO, Item item) {
		SpriteDTOMapper.mapSpriteToSpriteDTO(itemDTO, item);
		itemDTO.setInfoText(item.getInfoText());
	}

	private static void mapItemDTOToItem(Item item, ItemDTO itemDTO)  {
		SpriteDTOMapper.mapSpriteDTOToSprite(item, itemDTO);
		item.setInfoText(itemDTO.getInfoTekst());
	}

	// public List<ItemDTO> mapSpritesToDTO(List<Item> sprites) {
	// ArrayList<ItemDTO> dtos = new ArrayList<>();
	// for(Item item : sprites){
	// dtos.add(mapSpriteToDTO(item));
	// }
	// return dtos;
	// }

}
