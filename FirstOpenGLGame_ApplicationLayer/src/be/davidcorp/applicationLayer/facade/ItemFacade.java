package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.repository.ItemRepository;

public class ItemFacade {

	public void useItem(ItemDTO itemDTO){
		Item item = new ItemRepository().getSprite(itemDTO.getId());
		item.useItem(PlayerManager.getCurrentPlayer());
	}
	
	public void changeItemPosition(ItemDTO itemDTO, int x, int y){
		Item item = new ItemRepository().getSprite(itemDTO.getId());
		item.setX(x);
		item.setY(y);
	}

	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		
	}
}
