package be.davidcorp.applicationLayer.dto;

import be.davidcorp.applicationLayer.dto.mapper.ItemType;

public class AmmoDTO extends ItemDTO{

	private int damage = 50;

	public AmmoDTO(ItemType type) {
		super(type);
	}

	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage=damage;
	}
	
}
