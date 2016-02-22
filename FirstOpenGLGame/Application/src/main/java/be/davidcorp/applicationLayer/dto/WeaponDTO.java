package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.ItemType;

public class WeaponDTO extends ItemDTO{


	private int amountOfBullets;

	public WeaponDTO(ItemType type) {
		super(type);
	}
	public int getAmountOfBullets() {
		return amountOfBullets;
	}

	public void setAmountOfBullets(int amountOfBullets) {
		this.amountOfBullets = amountOfBullets;
	}
	
	
}
