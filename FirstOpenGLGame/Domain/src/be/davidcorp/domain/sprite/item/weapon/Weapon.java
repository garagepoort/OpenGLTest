package be.davidcorp.domain.sprite.item.weapon;


import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.domain.sprite.organic.player.Player;
import be.davidcorp.domain.utilities.Cooldowner;
import be.davidcorp.metric.Vector;

public abstract class Weapon extends Item{
	
	private static final long serialVersionUID = 6888053320335941087L;
	private Cooldowner attackCooldowner = new Cooldowner(200);

	public Weapon(float x, float y, int width, int height) {
		super(x, y);
		setWidth(width);
		setHeight(height);
	}
	
	public abstract WeaponType getWeaponType();
	
	protected abstract void attack(Vector vector);
	
	public void useWeapon(Vector vector){
		if(attackCooldowner.isCoolDowned()){
			attack(vector);
			attackCooldowner.resetCooldownProgress();
		}
	}
	
	public void updateSprite(float secondsMovedInGame){
		super.updateSprite(secondsMovedInGame);
		if(!attackCooldowner.isCoolDowned()){
			attackCooldowner.progressCooldown();
		}
	}
	

	public void useItem(OrganicSprite organicSprite) {
		if(organicSprite instanceof Player){
			Player p = (Player) organicSprite;
			p.setEquippedWeapon(this);
			this.setOwner(p);
		}
	}
	
	protected void setAttackCooldown(int attackCooldown) {
		attackCooldowner = new Cooldowner(attackCooldown);
	}
	
	public boolean isReadyToAttack() {
		return attackCooldowner.isCoolDowned();
	}
	
	
}
