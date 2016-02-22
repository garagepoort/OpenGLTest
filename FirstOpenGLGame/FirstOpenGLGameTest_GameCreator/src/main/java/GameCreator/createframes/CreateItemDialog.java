package GameCreator.createframes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.ErrorHandler;
import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PotionFacade;
import be.davidcorp.applicationLayer.facade.WeaponFacade;

@SuppressWarnings("serial")
public class CreateItemDialog extends CreateDialog implements MouseListener {

	public ItemDTO itemDTO;

	private JPanel labelFieldPanel;

	private JTextField fieldX;
	private JTextField fieldY;

	private JLabel xLabel;
	private JLabel yLabel;

	private JButton createButton;
	private JButton colorPickerButton;

	private WeaponFacade weaponFacade = new WeaponFacade();
	private PotionFacade potionFacade = new PotionFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	private ItemType itemType;

	public CreateItemDialog(ItemType itemType) {
		super("Create Item", 400, 400);
		setName("CreateItemDialog");
		addMouseListeners();
		this.itemType = itemType;
	}
	
	protected void initComponents() {

		getMainPanel().setLayout(new BoxLayout(getMainPanel(), BoxLayout.Y_AXIS));
		
		labelFieldPanel = new JPanel(new SpringLayout());
		createButton = new JButton("create Item");
		colorPickerButton = new JButton("pick color");
		
		fieldX = new JTextField(10);
		fieldY = new JTextField(10);

		xLabel = new JLabel("x value: ", JLabel.TRAILING);
		yLabel = new JLabel("y value: ", JLabel.TRAILING);

		xLabel.setLabelFor(fieldX);
		yLabel.setLabelFor(fieldY);

		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(fieldX);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(fieldY);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad


		getMainPanel().add(labelFieldPanel);
		getMainPanel().add(createButton);
	}


	private void addMouseListeners() {
		createButton.addMouseListener(this);
		colorPickerButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == createButton) {
			try {
				switch (itemType) {
					case PISTOL :
						itemDTO = weaponFacade.createPistol(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()));
						break;
					case HEALTHPOTION :
						itemDTO = potionFacade.createHealthPotion(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()));
						break;
					case STAMINAPOTION :
						itemDTO = potionFacade.createStaminaPotion(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()));
						break;
					default :
						break;
				}
				if (itemDTO == null) {
					JOptionPane.showMessageDialog(getMainPanel(), "You have to select an item.");
				} else {
					gameFieldFacade.addSpriteToWorld(itemDTO.getId());
					this.dispose();
//					FrameFacade.closeCreateDialog(CreateItemDialog.this);
				}
			} catch (Exception e) {
				ErrorHandler.handleError(getMainPanel(), e);
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
