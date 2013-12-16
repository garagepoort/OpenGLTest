package be.davidcorp.domain.inventory;

import be.davidcorp.domain.exception.InventoryException;
import be.davidcorp.domain.sprite.item.Item;

public class Inventory {

	private int ID;
	private int maxWeight = 1000;
	private int currentWeight;
	private Item[][] items = new Item[6][4];

	public Inventory() {
	}

	// public void switchItems(Item i1, Item i2){
	// int rij1 = 0;
	// int kolom1 = 0;
	// int rij2 = 0;
	// int kolom2 = 0;
	// for(int i = 0; i<items.length;i++){
	// for(int j = 0; j<items[0].length; j++){
	// if(items[i][j] == i1){
	// rij1=i;
	// kolom1=j;
	// }
	// }
	// }
	// for(int i = 0; i<items.length;i++){
	// for(int j = 0; j<items[0].length; j++){
	// if(items[i][j] == i2){
	// rij2=i;
	// kolom2=j;
	// }
	// }
	// }
	// items[rij1][kolom1] = i2;
	// items[rij2][kolom2] = i1;
	// }

	public void addItem(Item i, int row, int column) throws InventoryException {
		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
			throw new InventoryException("Index out of bounds");
		}
		if (currentWeight + i.getWeight() > maxWeight) {
			throw new InventoryException("You can not carry anymore items");
		}
		if (containsItem(i)) {
			throw new InventoryException("The inventory already contains this item.");
		}
		if (items[row][column] != null) {
			throw new InventoryException("There is already an item at this position.");
		}
		this.currentWeight = currentWeight + i.getWeight();
		items[row][column] = i;
	}

	public void addItem(Item item) throws InventoryException {

		if (currentWeight + item.getWeight() > maxWeight) {
			throw new InventoryException("You can not carry anymore items");
		}
		if (containsItem(item)) {
			throw new InventoryException("The inventory already contains this item.");
		}
		boolean add = false;
		for (int i = 0; i < items.length && !add; i++) {
			for (int j = 0; j < items[0].length && !add; j++) {
				if (items[i][j] == null) {
					items[i][j] = item;
					add = true;
				}
			}
		}
		if (add) {
			this.currentWeight = currentWeight + item.getWeight();
		} else {
			throw new InventoryException("Item can't be added. Inventory full");
		}

	}

	public void removeItem(int row, int column) throws InventoryException {
		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
			throw new InventoryException("Index out of bounds");
		}
		if (items[row][column] == null)
			return;

		Item i = items[row][column];
		this.currentWeight = currentWeight - i.getWeight();
		items[row][column] = null;
	}

	public void removeItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				if (items[i][j] == item) {
					items[i][j] = null;
				}
			}
		}
	}

	public Item getItem(int row, int column) throws InventoryException {
		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
			throw new InventoryException("Index out of bounds");
		}
		return items[row][column];
	}

	public void removeDeadItems() {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				if (items[i][j] != null && !items[i][j].isAlive()) {
					items[i][j] = null;
				}
			}
		}
	}

	public void dropItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < items[0].length; j++) {
				if (items[i][j] != null && items[i][j] == item) {
					items[i][j] = null;
				}
			}
		}
	}

	public boolean containsItem(Item item) {
		boolean contains = false;
		for (int i = 0; i < items.length && !contains; i++) {
			for (int j = 0; j < items[0].length && !contains; j++) {
				if (items[i][j] == item) {
					contains = true;
				}
			}
		}
		return contains;
	}

	public Item[][] getItems() {
		return items;
	}

	public int getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

}
