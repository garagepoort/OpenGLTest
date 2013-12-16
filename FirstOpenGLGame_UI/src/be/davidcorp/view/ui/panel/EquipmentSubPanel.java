package be.davidcorp.view.ui.panel;

import java.util.EventObject;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.facade.PlayerFacade;
import be.davidcorp.view.game.PlayGamePanel;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;

public class EquipmentSubPanel extends Panel {
	private ItemDTO item;
	private Button useButton = new Button(0, 0, 128, 16);
	private Button dropButton = new Button(0, 0, 128, 16);
	private Button infoButton = new Button(0, 0, 128, 16);
	private PlayerFacade playerFacade = new PlayerFacade();

	public EquipmentSubPanel(float x, float y, int width, int height, Color c, ItemDTO item, PlayGamePanel g) {
		super(x, y, width, height, c);
		setGamePanel(g);
		this.item = item;
		initButtons();
	}

	public void initButtons() {
		initHandlers();
		useButton.setTexture("resources/images/buttons/unequipButton.png");
		dropButton.setTexture("resources/images/buttons/dropButton.png");
		infoButton.setTexture("resources/images/buttons/infoButton.png");
		addComponent(useButton, 0, getHeight() - useButton.getHeight());
		addComponent(dropButton, 0, getHeight() - dropButton.getHeight() * 2 - 2);
		addComponent(infoButton, 0, getHeight() - infoButton.getHeight() * 3 - 4);
	}

	private void initHandlers() {

		useButton.addEventListener(new ClickListener() {
			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				playerFacade.unequipWeapon();
				setClosed(true);
			}
		});

		dropButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				playerFacade.dropItemFromEquipment(item);
				setClosed(true);
			}
		});

		infoButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				getGamePanel().addPanel(new ItemInfoPanel(200, 100, 400, 84, new Color(255, 0, 0), item));
				setClosed(true);
			}
		});
	}
}
