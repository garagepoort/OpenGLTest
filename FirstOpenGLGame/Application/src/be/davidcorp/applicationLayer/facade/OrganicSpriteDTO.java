package be.davidcorp.applicationLayer.facade;

import be.davidcorp.applicationLayer.dto.SpriteDTO;

public class OrganicSpriteDTO extends SpriteDTO{

	private int stamina;
	private int maxStamina;
	
	public int getStamina() {
		return stamina;
	}
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	public int getMaxStamina() {
		return maxStamina;
	}
	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	
	
}
