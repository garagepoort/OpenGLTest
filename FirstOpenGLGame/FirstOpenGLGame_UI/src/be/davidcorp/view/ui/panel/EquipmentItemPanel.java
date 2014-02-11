package be.davidcorp.view.ui.panel;

import java.util.EventObject;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.view.ui.button.Button;
import be.davidcorp.view.ui.button.ClickListener;

public class EquipmentItemPanel extends Panel {
	private EquipmentPanel equipmentPanel;
	private ItemDTO itemDTO;
	public EquipmentItemPanel(float x, float y, String t, EquipmentPanel p,
			ItemDTO itemDTO) {
		super(x, y, 64, 64, t);
		this.itemDTO = itemDTO;
		setEquipmentPanel(p);
		initButtons();
	}

	public void initButtons() {
		Button b2 = new Button(0, 0, 64, 64);
		b2.addEventListener(new ClickListener() {

			@Override
			public void onLeftClick(EventObject e) {
				// do nothing
			}

			@Override
			public void onRightClick(EventObject e) {
				equipmentPanel.setEquipmentSubPanel(new EquipmentSubPanel(Mouse.getX(), Mouse.getY() - 64, 128, 64, new Color(0, 0, 0), itemDTO, getGamePanel()));
			}
		});
		b2.setTexture(itemDTO.getTexture());
		addComponent(b2, 0, 0);

	}

	public EquipmentPanel getEquipmentPanel() {
		return equipmentPanel;
	}

	public void setEquipmentPanel(EquipmentPanel equipmentPanel) {
		this.equipmentPanel = equipmentPanel;
	}

}
