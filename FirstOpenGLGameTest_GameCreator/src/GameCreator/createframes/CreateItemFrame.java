package GameCreator.createframes;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.ErrorHandler;
import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.PotionFacade;
import be.davidcorp.applicationLayer.facade.WeaponFacade;

public class CreateItemFrame implements MouseListener {

	private JFrame frame = new JFrame();
	public ItemDTO itemDTO;

	private JPanel mainPanel;
	private JPanel labelFieldPanel;

	private JTextField fieldX;
	private JTextField fieldY;

	private JLabel xLabel;
	private JLabel yLabel;

	private JButton createButton = new JButton("create Item");
	private JButton colorPickerButton = new JButton("pick color");

	private WeaponFacade weaponFacade = new WeaponFacade();
	private PotionFacade potionFacade = new PotionFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	private ItemType itemType;

	public CreateItemFrame(ItemType itemType) {
		this.itemType = itemType;
		initComponents();
		frame.getContentPane().add(mainPanel);
		frame.setResizable(false);
		frame.setSize(new Dimension(400, 300));
		frame.setVisible(true);
	}

	private void initComponents() {

		// Create and populate the panel.
		labelFieldPanel = new JPanel(new SpringLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


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

		// mainPanel.add(createButton);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		createButton.addMouseListener(this);
		colorPickerButton.addMouseListener(this);

		mainPanel.add(labelFieldPanel);
		mainPanel.add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == createButton) {
			try {
				switch (itemType) {
					case PISTOL :
						itemDTO = weaponFacade.createPistol(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()), 54);
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
					JOptionPane.showMessageDialog(mainPanel, "You have to select an item.");
				} else {
					gameFieldFacade.addGroundItemToWorld(itemDTO.getId());
					frame.setVisible(false);
				}
			} catch (NumberFormatException | ModelException e) {
				ErrorHandler.handleError(frame, e);
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
