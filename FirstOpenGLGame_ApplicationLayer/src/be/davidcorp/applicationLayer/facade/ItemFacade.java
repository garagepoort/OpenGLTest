package be.davidcorp.applicationLayer.facade;

import static be.davidcorp.domain.game.GameFieldManager.getCurrentGameField;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.loaderSaver.repository.ItemRepository;

public class ItemFacade {
	
	private ItemRepository itemRepository = new ItemRepository();

	public void useItem(ItemDTO itemDTO){
		Item item = itemRepository.getSprite(itemDTO.getId());
		item.useItem(PlayerManager.getCurrentPlayer());
	}
	
	public void changeItemPosition(ItemDTO itemDTO, int x, int y){
		Item item = itemRepository.getSprite(itemDTO.getId());
		item.setX(x);
		item.setY(y);
	}

	public void deleteItem(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateItem(ItemDTO itemDTO) {
		try {
			Item item = ItemDTOMapper.mapItemDTOToItem(itemDTO);
			itemRepository.updateSprite(item);
			getCurrentGameField().updateGroundItem(item);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
