package GameCreator.createframes;

import static GameCreator.createframes.FrameFacade.closeCreateWallFrame;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;

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
import be.davidcorp.applicationLayer.dto.mapper.ConstructionType;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

public class CreateWallFrame extends Observable implements MouseListener {

	private JDialog dialog = new JDialog();
	private JPanel mainPanel;
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

	private JButton createButton = new JButton("create wall");
	private JButton colorPickerButton = new JButton("pick color");

	private ColorPickerFrame colorPickerFrame = null;

	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	public ConstructionSpriteDTO constructionSpriteDTO;

	public CreateWallFrame() {
		initComponents();
		dialog.setTitle("Create a new Wall");
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.getContentPane().add(mainPanel);
		// addComponents();
		dialog.setResizable(false);
		dialog.setSize(new Dimension(400, 300));
		dialog.setVisible(true);
		dialog.addWindowListener(new NewWindowListener());
	}

	private void initComponents() {

		// Create and populate the panel.
		labelFieldPanel = new JPanel(new SpringLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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

		mainPanel.add(labelFieldPanel);
		mainPanel.add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		try {
			if (event.getSource() == createButton) {
				ColorDTO color = getPickedColor();
				initializeWallDTO(color);

				constructionSpriteDTO = new ConstructionSpriteFacade().createWall(constructionSpriteDTO);
				gameFieldFacade.addConstructionSpriteToWorld(constructionSpriteDTO.getId());

				dialog.setVisible(false);
				setChanged();
				notifyObservers();
			} else if (event.getSource() == colorPickerButton) {
				colorPickerFrame = new ColorPickerFrame();
			}
		} catch (ModelException e) {
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
		constructionSpriteDTO = new ConstructionSpriteDTO(ConstructionType.WALL);
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

	private class NewWindowListener implements WindowListener {

		@Override
		public void windowClosing(WindowEvent e) {
			closeCreateWallFrame();
		}

		@Override
		public void windowActivated(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowOpened(WindowEvent e) {
		}

	}
}
