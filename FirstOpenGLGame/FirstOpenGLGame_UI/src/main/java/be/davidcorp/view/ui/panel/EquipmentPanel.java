package be.davidcorp.view.ui.panel;

import java.io.IOException;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.applicationLayer.uiModels.EquipmentModel;
import be.davidcorp.inputControl.MouseButton;
import be.davidcorp.view.MousePosition;
import be.davidcorp.view.game.PlayGamePanel;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;

public class EquipmentPanel extends Panel implements Observer {
	private EquipmentSubPanel equipmentSubPanel;
	private PlayerFacade playerFacade = new PlayerFacade();

	public EquipmentPanel(float x, float y, PlayGamePanel gamepanel)
			throws IOException {

		super(x, y, 256, 512, "resources/images/equipment.png");
		EquipmentModel.getInstance().addObserver(this);
		setGamePanel(gamepanel);
		addItemPanels();
	}

	public boolean checkMouseClick(int x, int y, MouseButton buttonClicked) {
		boolean result = super.checkMouseClick(x, y, buttonClicked);
		if (equipmentSubPanel != null) {
			if(equipmentSubPanel.checkMouseClick(x, y, buttonClicked)){
				result = true;
			}
		}
		return result;
	}

	public void drawThis() {
		super.drawThis();
		/*
		 * TODO Items worden elke keer opnieuw toegevoegd als de equipment
		 * getekend word. Anders zien we niet direct wanneer een item verwijderd
		 * word. Beter zou zijn om een boolean aan te maken om aan te duiden of
		 * de equipment verandert is. Zoja laden we de items opnieuw in. Nog
		 * beter is een EquipmentModel waarop je luisterd naar veranderingen.
		 */
		// addItemPanels();
		// if (equipmentSubPanel != null && !equipmentSubPanel.isClosed()) {
		// equipmentSubPanel.draw();
		// }
	}

	public void addItemPanels()  {

		// getComponents().clear();
		ItemDTO weaponDTO = playerFacade.getPlayerWeapon();
		if (weaponDTO != null) {
			Button button = createItemButton(weaponDTO);
			addComponent(button, 0, getHeight() - 64);
		}

//		itemDTO = equipment.getAccesoire1();
//		if (itemDTO != null) {
//			Button button = createItemButton(itemDTO);
//			addComponent(button, getWidth() - 64, getHeight() - 64);
//		}
	}

	public void setEquipmentSubPanel(EquipmentSubPanel equipmentSubPanel) {
		this.equipmentSubPanel = equipmentSubPanel;
		equipmentSubPanel.setGamePanel(getGamePanel());
	}

	private Button createItemButton(final ItemDTO itemDTO) {
		final Button itemButton = new Button(this.getX(), 0, 64, 64);
		itemButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
				try {
					itemButton.getComponents().clear();
					itemButton.addComponent(new EquipmentSubPanel(MousePosition.X, MousePosition.Y - 64, 128, 64, new Color(0, 0, 0), itemDTO, getGamePanel()), MousePosition.X, MousePosition.Y - 64);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void onLeftClick(EventObject e) {
			}
		});
		itemButton.setTexture(itemDTO.getTexture());
		return itemButton;
	}

	@Override
	public void update(Observable equipmentModel, Object item) {
		getComponents().clear();
		Button button = createItemButton((ItemDTO) item);
		addComponent(button, 0, getHeight() - 64);
	}

}
