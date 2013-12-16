package be.davidcorp.view.ui.panel;

import java.util.EventObject;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.PlayerDTO;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.applicationLayer.facade.InventoryFacade;
import be.davidcorp.inputControl.MouseButton;
import be.davidcorp.metric.Point;
import be.davidcorp.view.game.PlayGamePanel;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;
public class InventoryPanel extends Panel {

	private InventorySubPanel inventorySubPanel;
	private InventoryFacade inventoryFacade = new InventoryFacade();
	private PlayerDTO playerDTO;
	
	public InventoryPanel(float x, float y, int width, int height, Color c,
			PlayGamePanel p) throws MapperException {
		super(x, y, width, height, c);
		setGamePanel(p);
		addItemPanels();
		initButtons();
	}

	public void initButtons() {
		if (UIComponentCollisionChecker.isPointInsidePanel(this, new Point(Mouse.getX(), Mouse.getY(), 0))) {
			if (Mouse.isButtonDown(1)) {

			} else if (Mouse.isButtonDown(0)) {
				setInventorySubPanel(null);
			}
		}
	}

	@Override
	public boolean checkMouseClick(int x, int y, MouseButton buttonClicked) {
		boolean result = super.checkMouseClick(x, y, buttonClicked);
		if (inventorySubPanel != null) {
			if(inventorySubPanel.checkMouseClick(x, y, buttonClicked)){
				result = true;
			}
		}
		return result;
	}

	public void drawThis() {
		super.drawThis();
		/*
		 * TODO Items worden elke keer opnieuw toegevoegd als de inventory
		 * getekend word. Anders Zien we niet direct wanneer een item verwijnt.
		 * Beter zou zijn om een boolean aan te maken om aan te duiden of de
		 * inventory verandert is. Zoja laden we de items opnieuw in.
		 */
		try {
			addItemPanels();
		} catch (MapperException e) {
			e.printStackTrace();
		}
		if (inventorySubPanel != null && !inventorySubPanel.isClosed()) {
			inventorySubPanel.draw();
		}
	}

	public void addItemPanels() throws MapperException {
		// int rijrand = 5;
		int kolomrand = 5;
		getComponents().clear();
		ItemDTO[][] itemDTOs = listToMatrix(inventoryFacade.getItemsFromPlayer(playerDTO));
		for (int i = 0; i < itemDTOs.length; i++) {
			for (int j = 0; j < itemDTOs[0].length; j++) {
				if (itemDTOs[i][j] != null) {
					Button itemButton = createItemButton(kolomrand, i, j, itemDTOs[i][j]);
					addComponent(itemButton, j * 64 + kolomrand, (i + 1) * 64);
				}
			}
		}
	}

	private Button createItemButton(int kolomrand, int i, int j, final ItemDTO itemDTO) {
		Button itemButton = new Button(j * 64 + kolomrand, (i + 1) * 64, 64, 64);
		itemButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
				setInventorySubPanel(new InventorySubPanel(Mouse.getX(), Mouse.getY(), 128, 64, new Color(0, 0, 0), itemDTO));
			}

			@Override
			public void onLeftClick(EventObject e) {
			}
		});
		itemButton.setTexture(itemDTO.getTextureBunch().getCurrentTexture());
		return itemButton;
	}

	public InventorySubPanel getInventorySubPanel() {
		return inventorySubPanel;
	}

	public void setInventorySubPanel(InventorySubPanel inventorySubPanel) {
		this.inventorySubPanel = inventorySubPanel;
		inventorySubPanel.setGamePanel(getGamePanel());
	}
	
	private ItemDTO[][] listToMatrix(List<ItemDTO> itemDTOList){
		ItemDTO[][] itemDTOMatrix = new ItemDTO[6][4];
		int rij = 0;
		int kolom = 0;
		for (ItemDTO itemDTO : itemDTOList) {
			if(rij==6){
				rij = 0;
			}
			if(kolom ==4){
				kolom = 0;
			}
			itemDTOMatrix[rij][kolom] = itemDTO;
			rij++;
			kolom++;
		}
		return itemDTOMatrix;
	}

}
