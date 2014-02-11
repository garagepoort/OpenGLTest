package be.davidcorp.applicationLayer.uiModels;

import java.util.Observable;

import be.davidcorp.domain.sprite.item.weapon.Weapon;

public class EquipmentModel extends Observable{

	private Weapon weapon;
	private static final EquipmentModel EQUIPMENT_MODEL = new EquipmentModel();
	
	private EquipmentModel(){}
	
	public static EquipmentModel getInstance(){
		return EQUIPMENT_MODEL;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	
}
