package be.davidcorp.applicationLayer.facade;

import java.util.ArrayList;
import java.util.List;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.PlayerDTO;

public class InventoryFacade {

	public List<ItemDTO> getItemsFromPlayer(PlayerDTO playerDTO) {
//		List<Item> items = PlayerManager.getCurrentPlayer().getInventory().getItems();
		List<ItemDTO> itemdtos = new ArrayList<>();
//		for (Item[] items2 : items) {
//			List<Item> temp = new ArrayList<>(Arrays.asList(items2));
//			temp.removeAll(Collections.singleton(null));
//			itemdtos.addAll(ItemDTOMapper.mapItemsToItemDTOs(temp));
//		}
		return itemdtos;
	}
	
}
