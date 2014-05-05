package GameCreator.createframes;

import static java.lang.Float.parseFloat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.ErrorHandler;
import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

@SuppressWarnings("serial")
public class CreatePropDialog extends CreateDialog implements MouseListener {

	private JDialog dialog = new JDialog();
	private JPanel labelFieldPanel;

	private JTextField fieldX;
	private JTextField fieldY;

	private JLabel xLabel;
	private JLabel yLabel;

	private static JButton createButton = new JButton("create prop");
	
	private JComboBox<ConstructionType> propType;

	private GameFieldFacade gameFieldFacade;
	public ConstructionSpriteDTO constructionSpriteDTO;
	private JLabel propLabel;

	public CreatePropDialog() {
		super("Create a new prop", 400, 300);
		setName("CreatePropDialog");
	}

	protected void initComponents() {
		createButton = new JButton("create wall");
		gameFieldFacade = new GameFieldFacade();
		
		// Create and populate the panel.
		labelFieldPanel = new JPanel(new SpringLayout());
		getMainPanel().setLayout(new BoxLayout(getMainPanel(), BoxLayout.Y_AXIS));

		propType = new JComboBox<ConstructionType>(ConstructionType.values());
		fieldX = new JTextField(10);
		fieldY = new JTextField(10);

		propLabel = new JLabel("proptype: ", JLabel.TRAILING);
		xLabel = new JLabel("x value: ", JLabel.TRAILING);
		yLabel = new JLabel("y value: ", JLabel.TRAILING);

		propLabel.setLabelFor(propType);
		xLabel.setLabelFor(fieldX);
		yLabel.setLabelFor(fieldY);

		labelFieldPanel.add(propType);
		labelFieldPanel.add(propLabel);
		
		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(fieldX);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(fieldY);

		// mainPanel.add(createButton);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 3, 2, // rows, cols
				6, 6, // initX, initY
				3, 3); // xPad, yPad

		createButton.addMouseListener(this);

		getMainPanel().add(labelFieldPanel);
		getMainPanel().add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		try {
			if (event.getSource() == createButton) {
				initializePropDTO();
				constructionSpriteDTO = new ConstructionSpriteFacade().createConstructionSprite(constructionSpriteDTO);
				gameFieldFacade.addSpriteToWorld(constructionSpriteDTO.getId());
				CreatePropDialog.this.dispose();
			}
		} catch (Exception e) {
			ErrorHandler.handleError(dialog, e);
		}
	}

	private void initializePropDTO() {
		constructionSpriteDTO = new ConstructionSpriteDTO((ConstructionType) propType.getSelectedItem());
		float x = parseFloat(fieldX.getText());
		float y = parseFloat(fieldY.getText());

		constructionSpriteDTO.setX(x);
		constructionSpriteDTO.setY(y);
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
