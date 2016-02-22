package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.ItemType;

public class ItemDTO extends SpriteDTO{

	private ItemType type;
	private String infoTekst;
	private boolean onGround;
	
	public ItemDTO(ItemType type) {
		this.type=type;
	}

	public String getInfoTekst() {
		return infoTekst;
	}

	public void setInfoText(String infoText) {
		this.infoTekst=infoText;
	}

	public ItemType getType() {
		return type;
	}
	
	public boolean isOnGround() {
		return onGround;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}
}
