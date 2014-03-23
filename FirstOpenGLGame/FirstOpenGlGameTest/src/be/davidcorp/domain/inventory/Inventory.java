package be.davidcorp.domain.inventory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.ItemFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Inventory implements Serializable{

	private static final long serialVersionUID = -138952561842418910L;
	private int ID;
//	private int maxWeight = 1000;
//	private int currentWeight;

	private Map<Integer, Item> items = Maps.newHashMap();
	
	public Inventory() {
		Item item = ItemFactory.createHealthPotion(10, 10, 100);
		Item item2 = ItemFactory.createHealthPotion(10, 10, 100);
		addItem(item);
		addItem(item2);
	}

	public void addItem(Item item)  {
//		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
//			throw new InventoryException("Index out of bounds");
//		}
//		if (currentWeight + i.getWeight() > maxWeight) {
//			throw new InventoryException("You can not carry anymore items");
//		}
//		if (containsItem(i)) {
//			throw new InventoryException("The inventory already contains this item.");
//		}
//		if (items[row][column] != null) {
//			throw new InventoryException("There is already an item at this position.");
//		}
//		this.currentWeight = currentWeight + i.getWeight();
//		items[row][column] = i;
		items.put(item.getID(), item);
	}

//	public void addItem(Item item)  {
//
//		if (currentWeight + item.getWeight() > maxWeight) {
//			throw new InventoryException("You can not carry anymore items");
//		}
//		if (containsItem(item)) {
//			throw new InventoryException("The inventory already contains this item.");
//		}
//		boolean add = false;
//		for (int i = 0; i < items.length && !add; i++) {
//			for (int j = 0; j < items[0].length && !add; j++) {
//				if (items[i][j] == null) {
//					items[i][j] = item;
//					add = true;
//				}
//			}
//		}
//		if (add) {
//			this.currentWeight = currentWeight + item.getWeight();
//		} else {
//			throw new InventoryException("Item can't be added. Inventory full");
//		}
//
//	}

	public void removeItem(int id)  {
//		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
//			throw new InventoryException("Index out of bounds");
//		}
//		if (items[row][column] == null)
//			return;
//
//		Item i = items[row][column];
//		this.currentWeight = currentWeight - i.getWeight();
//		items[row][column] = null;
		items.remove(id);
	}

//	public void removeItem(Item item) {
//		for (int i = 0; i < items.length; i++) {
//			for (int j = 0; j < items[0].length; j++) {
//				if (items[i][j] == item) {
//					items[i][j] = null;
//				}
//			}
//		}
//	}

	public Item getItem(int id)  {
//		if (row < 0 || row > items.length - 1 || column < 0 || column > items[0].length) {
//			throw new InventoryException("Index out of bounds");
//		}
//		return items[row][column];
		return items.get(id);
	}

//	public void removeDeadItems() {
//		for (int i = 0; i < items.length; i++) {
//			for (int j = 0; j < items[0].length; j++) {
//				if (items[i][j] != null && !items[i][j].isAlive()) {
//					items[i][j] = null;
//				}
//			}
//		}
//	}

	public boolean containsItem(Item item) {
		return items.containsValue(item);
	}

	public List<Item> getItems() {
		return Lists.newArrayList(items.values());
	}

	public int getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public void removeItem(Item item) {
		items.remove(item.getID());
	}

}
