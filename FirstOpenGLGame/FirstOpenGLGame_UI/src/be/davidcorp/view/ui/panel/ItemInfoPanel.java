package be.davidcorp.view.ui.panel;

import java.util.EventObject;

import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;

public class ItemInfoPanel extends Panel {
	private ItemDTO itemDTO;
	private Button closeButton = new Button(0, 0, 32, 32);
	public ItemInfoPanel(float x, float y, int width, int height, Color c, ItemDTO itemDTO) {
		super(x, y, width, height, c);
		this.itemDTO = itemDTO; 
		initButtons();
		String t = this.itemDTO.getTexture();
		setText(itemDTO.getInfoTekst(), 100, 50);
		Panel p = new Panel(10, 10, 64, 64, t);
		addComponent(p, 10, 10);

	}

	public void initButtons() {
		closeButton.addEventListener(new ClickListener() {
			@Override
			public void onRightClick(EventObject e) {
			}
			@Override
			public void onLeftClick(EventObject e) {
				setClosed(true);
			}
		});

		closeButton.setTexture("resources/images/buttons/closebutton.png");
		addComponent(closeButton, getWidth() - 32, getHeight() - 32);
	}

}
