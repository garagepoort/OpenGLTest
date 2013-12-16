package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.ItemType;

public class ItemDTO extends SpriteDTO{

	public ItemType type;
	private String infoTekst;

	public ItemDTO(ItemType type) {
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	public String getInfoTekst() {
		return infoTekst;
	}

	public void setInfoText(String infoText) {
		this.infoTekst=infoText;
	}

	
}
