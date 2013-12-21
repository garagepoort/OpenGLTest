package be.davidcorp.view.game;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.inputControl.GamePanelInputController;
import be.davidcorp.view.drawer.PlayerDrawer;
import be.davidcorp.view.ui.panel.EquipmentPanel;
import be.davidcorp.view.ui.panel.GuidePanel;
import be.davidcorp.view.ui.panel.InventoryPanel;
import be.davidcorp.view.ui.panel.PickupPanel;

public class PlayGamePanel extends GamePanel {

	private GameFieldFacade gamefieldFacade = new GameFieldFacade();

	private InventoryPanel inventoryMenu;
	private EquipmentPanel equipmentPanel;
	private PickupPanel pickupPanel;
	private GuidePanel guidePanel;

	// private boolean stepSound = false;

	public PlayGamePanel() throws IOException {
		super();
		setInputController(new GamePanelInputController(this));
		initializePanels();
	}

	@Override
	public void render() throws IOException {
		super.render();
		PlayerDrawer.drawHealth();
		PlayerDrawer.drawStamina();
	}

	private void initializePanels() throws IOException {
		inventoryMenu = new InventoryPanel(519f, 100f, 281, 519, new Color(0, 255, 0), this);
		equipmentPanel = new EquipmentPanel(0, 88, this);
		guidePanel = new GuidePanel(10, 480, 400, 100, new Color(255, 0, 0, 0.8f));
		addPanel(inventoryMenu);
		addPanel(equipmentPanel);
		addPanel(guidePanel);
	}

	public void togglePickupPanel()  {
		List<ItemDTO> items = gamefieldFacade.getItemsThatCanBePickedUpByPlayer();
		if (items.size() > 0) {
			if (pickupPanel == null || pickupPanel.isClosed()) {
				pickupPanel = new PickupPanel(400, 300, new Color(0, 0, 0, 0.9f), items);
				pickupPanel.setGamePanel(this);
				addPanel(pickupPanel);
			} else {
				pickupPanel.setClosed(true);
			}

		}
	}
	

	public void toggleInventoryMenu() {
		inventoryMenu.setVisible(!inventoryMenu.isVisible());
	}

	public void toggleEquipmentPanel() {
		equipmentPanel.setVisible(!equipmentPanel.isVisible());
	}

}
