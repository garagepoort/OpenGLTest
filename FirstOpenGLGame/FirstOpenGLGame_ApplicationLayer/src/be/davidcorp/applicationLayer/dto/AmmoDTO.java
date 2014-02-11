package be.davidcorp.applicationLayer.dto;

public class AmmoDTO extends SpriteDTO{

	private int damage = 50;
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage=damage;
	}
	
}
