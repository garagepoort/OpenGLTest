package be.davidcorp.domain.inventory;

import java.io.Serializable;
import java.util.Observable;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Weapon;

public class Equipment extends Observable  implements Serializable{
	private static final long serialVersionUID = 6817168358166879037L;
	private Weapon weapon;
	private Item accesoire1;
//	private HashMap<AttributeType, Float> attributeType = new HashMap<>();

	public void unequipItem(Item item) {
		item.setOwner(null);
		if (item == weapon) weapon = null;
		if (item == accesoire1) accesoire1 = null;
		setChanged();
		notifyObservers();
	}

//	@Override
//	public void setAttributes(HashMap<AttributeType, Float> attributeType) {
//		this.attributeType = attributeType;
//	}

	public void setAccesoire1(Item a) {
		this.accesoire1 = a;
		setChanged();
		notifyObservers();
	}


	public Item getAccesoire1() {
		return accesoire1;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void equipWeapon(Weapon weapon) {
		this.weapon = weapon;
		setChanged();
		notifyObservers();
	}

	public void updateAllEquippeditems(float secondsMovedInGame) {
		//TODO david this is not ok updated. See update in sprite
		if (weapon != null) weapon.updateSprite(secondsMovedInGame);
	}


}
