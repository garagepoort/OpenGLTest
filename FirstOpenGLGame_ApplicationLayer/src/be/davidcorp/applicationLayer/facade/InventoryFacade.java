package be.davidcorp.applicationLayer.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.PlayerDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;

public class InventoryFacade {

	public List<ItemDTO> getItemsFromPlayer(PlayerDTO playerDTO) {
		Item[][] items = PlayerManager.getCurrentPlayer().getInventory().getItems();
		List<ItemDTO> itemdtos = new ArrayList<>();
		for (Item[] items2 : items) {
			List<Item> temp = new ArrayList<>(Arrays.asList(items2));
			temp.removeAll(Collections.singleton(null));
			itemdtos.addAll(ItemDTOMapper.mapItemsToItemDTOs(temp));
		}
		return itemdtos;
	}
	
}
