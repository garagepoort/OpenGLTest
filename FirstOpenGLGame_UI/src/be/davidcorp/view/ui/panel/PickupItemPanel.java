package be.davidcorp.view.ui.panel;

import java.util.EventObject;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;

public class PickupItemPanel extends Panel {
	private ItemDTO itemDTO;
	private PickupPanel panel;
	private Button takeButton = new Button(0, 0, 62, 32);
	private PlayerFacade playerFacade = new PlayerFacade();

	public PickupItemPanel(float x, float y, int width, int height, Color c, ItemDTO itemDTO) {
		super(x, y, width, height, c);
		this.itemDTO = itemDTO;
		initButtons();
		String t = itemDTO.getTexture();
		setText(itemDTO.getInfoTekst(), 100, 50);
		Panel p = new Panel(10, 10, 64, 64, t);
		addComponent(p, 10, 10);
	}

	public void initButtons() {
		takeButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {}

			@Override
			public void onLeftClick(EventObject e) {
				new PlayerFacade().putItemInInventory(itemDTO);
				playerFacade.pickUpitem(itemDTO);
				panel.getRemoveItems().add(itemDTO);
				setClosed(true);
				setClosed(true);
			}
		});

		takeButton.setTexture("resources/images/buttons/takeButton.png");

		addComponent(takeButton, getWidth() - 62, getHeight() - 32);
	}

	public void setPickupPanel(PickupPanel panel) {
		this.panel = panel;
	}

}
