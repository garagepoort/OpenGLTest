package main.java.be.davidcorp.view.ui.panel;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import main.java.be.davidcorp.view.ui.button.Button;
import main.java.be.davidcorp.view.ui.button.ClickListener;
import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;

public class PickupPanel extends Panel {

	private List<ItemDTO> items = new ArrayList<>();
	private int tabCounter;
	private ArrayList<ItemDTO> removeItems = new ArrayList<>();
	private int size;
	private Button closeButton = new Button(0, 0, 32, 32);
	private Button nextButton = new Button(0, 0, 62, 32);
	private Button previousButton = new Button(0, 0, 62, 32);

	public PickupPanel(float x, float y, Color c, List<ItemDTO> items) {
		super(x, y, 500, 5 * 84 + 64, c);
		if (items.size() >= 5) {
			setHeight(5 * 84 + 64 + 10 * 5);
		} else {
			setHeight(items.size() * 84 + 64 + 10 * items.size());
		}
		setX(x - 500 / 2);
		setY(y - getHeight() / 2);
		this.items = items;
		size = items.size();
		initButtons();
		drawItems();
	}

	public void drawItems() {
		getComponents().clear();
		int xp = 10;
		int yp = getHeight() - 32 - 84;
		for (int i = tabCounter * 5; i < tabCounter * 5 + 5; i++) {
			if (i < size) {
				ItemDTO item = items.get(i);
				PickupItemPanel p = new PickupItemPanel(xp, yp, 400, 84, new Color(255, 0, 0, 0.8f), item);
				p.setPickupPanel(this);
				addComponent(p, xp, yp);
				yp -= 84 + 10;
			}
		}
	}

	public void initButtons() {
		initHandlers();

		closeButton.setTexture("resources/images/buttons/closebutton.png");
		nextButton.setTexture("resources/images/buttons/next.png");
		previousButton.setTexture("resources/images/buttons/previous.png");

		addComponent(closeButton, getWidth() - 32, getHeight() - 32);
		addComponent(nextButton, getWidth() - 70, 0);
		addComponent(previousButton, 0, 0);
	}

	private void initHandlers() {
		closeButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				setClosed(true);
			}
		});

		nextButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				if (size % 5 == 0) {
					if (tabCounter < size / 5 - 1) {
						tabCounter++;
						drawItems();
					}
				} else {
					if (tabCounter < size / 5) {
						tabCounter++;
						drawItems();
					}
				}
			}
		});

		previousButton.addEventListener(new ClickListener() {

			@Override
			public void onRightClick(EventObject e) {
			}

			@Override
			public void onLeftClick(EventObject e) {
				if (tabCounter > 0) {
					tabCounter--;
					drawItems();
				}
			}
		});
	}

	public ArrayList<ItemDTO> getRemoveItems() {
		return removeItems;
	}

}
