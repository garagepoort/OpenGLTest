package be.davidcorp.domain.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import be.davidcorp.domain.attribute.Attributer;
import be.davidcorp.domain.attribute.Attributes;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Weapon;

public class Equipment extends Observable implements Attributer {
	private Weapon weapon;
	private Item accesoire1;
	private HashMap<Attributes, Float> attributes = new HashMap<>();

	public void unequipItem(Item item) {
		item.setOwner(null);
		if (item == weapon) weapon = null;
		if (item == accesoire1) accesoire1 = null;
		setChanged();
		notifyObservers();
	}

	@Override
	public void setAttributes(HashMap<Attributes, Float> attributes) {
		this.attributes = attributes;
	}

	public void setAccesoire1(Item a) {
		this.accesoire1 = a;
		setChanged();
		notifyObservers();
	}

	@Override
	public HashMap<Attributes, Float> getAttributes() {

		if (accesoire1 != null) {
			for (Map.Entry<Attributes, Float> entry : getAttributes().entrySet()) {
				if (attributes.containsKey(entry.getKey())) {
					float v = attributes.get(entry.getKey());
					v += (Float) (entry.getValue());
					attributes.put((Attributes) entry.getKey(), v);
				} else {
					attributes.put((Attributes) entry.getKey(),
							(Float) entry.getValue());
				}
				// it.remove(); // avoids a ConcurrentModificationException but
				// emptys the map
			}
		}
		return attributes;
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
