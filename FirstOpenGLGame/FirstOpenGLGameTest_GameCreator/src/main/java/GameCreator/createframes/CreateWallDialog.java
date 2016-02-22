package GameCreator.createframes;

import static be.davidcorp.applicationLayer.dto.mapper.ConstructionType.WALL;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.ColorPickerFrame;
import GameCreator.ErrorHandler;
import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

@SuppressWarnings("serial")
public class CreateWallDialog extends CreateDialog implements MouseListener {

	private JDialog dialog = new JDialog();
	private JPanel labelFieldPanel;

	private JTextField fieldX;
	private JTextField fieldY;
	private JTextField fieldWidth;
	private JTextField fieldHeight;

	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JLabel colorLabel;

	private static JButton createButton = new JButton("create wall");
	private JButton colorPickerButton = new JButton("pick color");

	private ColorPickerFrame colorPickerFrame = null;

	private GameFieldFacade gameFieldFacade;
	public ConstructionSpriteDTO constructionSpriteDTO;

	public CreateWallDialog() {
		super("Create a new Wall", 400, 300);
		setName("CreateWallDialog");
	}

	protected void initComponents() {
		createButton = new JButton("create wall");
		colorPickerButton = new JButton("pick color");
		gameFieldFacade = new GameFieldFacade();
		
		// Create and populate the panel.
		labelFieldPanel = new JPanel(new SpringLayout());
		getMainPanel().setLayout(new BoxLayout(getMainPanel(), BoxLayout.Y_AXIS));

		fieldX = new JTextField(10);
		fieldY = new JTextField(10);
		fieldWidth = new JTextField(10);
		fieldHeight = new JTextField(10);

		xLabel = new JLabel("x value: ", JLabel.TRAILING);
		yLabel = new JLabel("y value: ", JLabel.TRAILING);
		widthLabel = new JLabel("width: ", JLabel.TRAILING);
		heightLabel = new JLabel("height: ", JLabel.TRAILING);
		colorLabel = new JLabel("pick color", JLabel.TRAILING);

		xLabel.setLabelFor(fieldX);
		yLabel.setLabelFor(fieldY);
		widthLabel.setLabelFor(fieldWidth);
		heightLabel.setLabelFor(fieldHeight);
		colorLabel.setLabelFor(colorPickerButton);

		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(fieldX);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(fieldY);

		labelFieldPanel.add(widthLabel);
		labelFieldPanel.add(fieldWidth);

		labelFieldPanel.add(heightLabel);
		labelFieldPanel.add(fieldHeight);

		labelFieldPanel.add(colorLabel);
		labelFieldPanel.add(colorPickerButton);

		// mainPanel.add(createButton);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 5, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		createButton.addMouseListener(this);
		colorPickerButton.addMouseListener(this);

		getMainPanel().add(labelFieldPanel);
		getMainPanel().add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		try {
			if (event.getSource() == createButton) {
				ColorDTO color = getPickedColor();
				initializeWallDTO(color);
				constructionSpriteDTO = new ConstructionSpriteFacade().createConstructionSprite(constructionSpriteDTO);
				gameFieldFacade.addSpriteToWorld(constructionSpriteDTO.getId());
				CreateWallDialog.this.dispose();
//				FrameFacade.closeCreateDialog(CreateWallDialog.this);
			} else if (event.getSource() == colorPickerButton) {
				colorPickerFrame = new ColorPickerFrame();
			}
		} catch (Exception e) {
			ErrorHandler.handleError(dialog, e);
		}
	}

	private ColorDTO getPickedColor() {
		ColorDTO color = new ColorDTO(255, 0, 0);
		if (colorPickerFrame != null) {
			color = new ColorDTO(colorPickerFrame.currentColor.getRed(), colorPickerFrame.currentColor.getGreen(), colorPickerFrame.currentColor.getRed());
		}
		return color;
	}

	private void initializeWallDTO(ColorDTO color) {
		constructionSpriteDTO = new ConstructionSpriteDTO(WALL);
		float x = parseFloat(fieldX.getText());
		float y = parseFloat(fieldY.getText());
		int width = parseInt(fieldWidth.getText());
		int height = parseInt(fieldHeight.getText());

		constructionSpriteDTO.setX(x);
		constructionSpriteDTO.setY(y);
		constructionSpriteDTO.setWidth(width);
		constructionSpriteDTO.setHeight(height);
		constructionSpriteDTO.setColor(color);
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
