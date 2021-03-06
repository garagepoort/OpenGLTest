package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.spriteToDTO.ItemDTOMapper;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.repository.DefaultSpriteRepository;
import be.davidcorp.repository.SpriteRepository;

public class ItemFacade {
	
	private SpriteRepository spriteRepository = DefaultSpriteRepository.getInstance();

	public void useItem(ItemDTO itemDTO){
		Item item = (Item) spriteRepository.getSprite(itemDTO.getId());
		item.useItem(PlayerManager.getCurrentPlayer());
	}
	
	public void changeItemPosition(ItemDTO itemDTO, int x, int y){
		Item item = (Item) spriteRepository.getSprite(itemDTO.getId());
		item.setX(x);
		item.setY(y);
	}

	public void deleteItem(int id) {
		spriteRepository.deleteSprite(id);
	}
	
	public void updateItem(ItemDTO itemDTO) {
		try {
			Item item = ItemDTOMapper.mapItemDTOToItem(itemDTO);
			spriteRepository.updateSprite(item);
		} catch (Exception e) {
			throw new ModelException(e);
		}
	}
}
