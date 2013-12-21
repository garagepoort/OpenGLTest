package be.davidcorp.domain.sprite.organic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import be.davidcorp.domain.TextureBunchFactory;
import be.davidcorp.domain.attribute.Attributes;
import be.davidcorp.domain.inventory.Equipment;
import be.davidcorp.domain.inventory.Inventory;
import be.davidcorp.domain.skill.Skill;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.weapon.Weapon;
import be.davidcorp.domain.sprite.item.weapon.WeaponType;
import be.davidcorp.metric.Vector;

public abstract class OrganicSprite extends Sprite implements Observer {

	@SuppressWarnings("rawtypes")
	private ArrayList<Skill> skills = new ArrayList<>();
	private Inventory inventory = new Inventory();
	private Equipment equipment = new Equipment();
	private int stamina = 1000;
	private int maxStamina = 1000;
	private int staminaRegen;
	private HashMap<Attributes, Float> attributes = new HashMap<>();
	
	public OrganicSprite(){}

	public OrganicSprite(float x, float y, int width, int height) {
		super(x, y, width, height);
		initialize();
	}

	public OrganicSprite(float x, float y, int health) {
		super(x, y, 64, 64);
		initialize();
		setMaxHealthPoints(health);
	}


	private void initialize(){
		initializeAtributes();
		equipment.addObserver(this);
	}
	
	private void initializeAtributes() {
		attributes = new HashMap<>();
		for (Attributes attribute : Attributes.values()) {
			attributes.put(attribute, 0f);
		}
	}


	@Override
	public void updateSprite(int secondsMovedInGame) {
		super.updateSprite(secondsMovedInGame);
		upDateSkillCooldowns();
		addStamina(staminaRegen);
		equipment.updateAllEquippeditems(secondsMovedInGame);
	}

	@SuppressWarnings("rawtypes")
	private void upDateSkillCooldowns() {
		for (Skill s : skills) {
			s.updateCooldown();
		}
	}

	private void updateAttributes() {
		initializeAtributes();
		HashMap<Attributes, Float> att = equipment.getAttributes();
		for (Attributes a : Attributes.values()) {
			if (att.containsKey(a)) {
				attributes.put(a, att.get(a));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		updateAttributes();
		setTextureBunch(TextureBunchFactory.createTextureBunch(this));
	}

	public void setX(float x) {
		super.setX(x);
		if (equipment != null && equipment.getWeapon() != null) {
			equipment.getWeapon().setX(getX());
		}
	}

	public void setY(float y) {
		super.setY(y);
		if (equipment != null && equipment.getWeapon() != null) {
			equipment.getWeapon().setY(getY());
		}
	}

	public void setRotationAngle(float angle) {
		super.setRotationAngle(angle);
		if (equipment != null && equipment.getWeapon() != null) {
			equipment.getWeapon().setRotationAngle(angle);
		}
	}

	@SuppressWarnings("unchecked")
	public void useSkill(int index, Sprite sprite) {
		skills.get(index).useSkill(this, sprite);
	}

	@SuppressWarnings("rawtypes")
	public void addSkill(Skill skill) {
		skills.add(skill);
	}

	public void useWeapon(Vector directionVector) {
		if (getEquippedWeapon() != null) {
			getEquippedWeapon().useWeapon(directionVector);
		}
	}

	public Weapon getEquippedWeapon() {
		return equipment.getWeapon();
	}
	

	public void unequipWeapon() {
		equipment.unequipItem(getEquippedWeapon());
	}

	public WeaponType getEquippedWeaponType() {
		if (getEquippedWeapon() != null)
			return getEquippedWeapon().getWeaponType();
		return null;
	}

	public void setEquippedWeapon(Weapon weapon) {
		weapon.setOwner(this);
		equipment.equipWeapon(weapon);
		equipment.getWeapon().setX(getX());
		equipment.getWeapon().setY(getY());
		setTextureBunch(TextureBunchFactory.createTextureBunch(this));
	}

	public Inventory getInventory() {
		return inventory;
	}

	public int getStamina() {
		return stamina;
	}
	
	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getMaxStamina() {
		return (int) (maxStamina + attributes.get(Attributes.STAMINA));
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	@Override
	public int getMaxHealthPoints() {
		int m = super.getMaxHealthPoints();
		return (int) (m + attributes.get(Attributes.HEALTH));
	}

	public float getSpeed() {
		float s = super.getSpeed();
		return s + attributes.get(Attributes.SPEED);
	}

	public void addStamina(int stamina) {
		if (this.stamina + stamina >= maxStamina) {
			this.stamina = maxStamina;
		} else {
			this.stamina += stamina;
		}
	}

	public void removeStamina(int stamina) {
		if (this.stamina - stamina <= 0) {
			this.stamina = 0;
		} else {
			this.stamina -= stamina;
		}
	}

	public Equipment getEquipment() {
		return equipment;
	}
	
	public void dropItem(Item item) {
		setDropPositionOfItem(item);
		inventory.dropItem(item);
		equipment.unequipItem(item);
	}

	private void setDropPositionOfItem(Item item) {
		item.setX(getX());
		item.setY(getY());
	}
	
	public void addItemToInventory(Item item) {
		inventory.addItem(item);
	}

}