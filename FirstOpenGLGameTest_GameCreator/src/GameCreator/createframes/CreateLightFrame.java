package GameCreator.createframes;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.color.ColorDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.applicationLayer.facade.LightFacade;
import be.davidcorp.metric.Point;

public class CreateLightFrame extends Observable implements MouseListener {

	public LightDTO lightDTO;

	private JPanel mainPanel;
	private JPanel labelFieldPanel;
	private JFrame frame = new JFrame();
	private JTextField fieldX;
	private JTextField fieldY;
	private JTextField fieldRadius;

	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel radiusLabel;

	private JButton createButton = new JButton("create light");
	private JButton colorPickerButton = new JButton("pick color");

	private JComboBox<Boolean> lightOnList;
	private Boolean[] lightOnString = {true, false};

	private JColorChooser colorChooser = new JColorChooser();

	private LightFacade lightFacade = new LightFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();

	public CreateLightFrame() {
		initComponents();
		frame.getContentPane().add(mainPanel);
		frame.setResizable(false);
		frame.setSize(new Dimension(600, 600));
		frame.setVisible(true);
	}

	private void initComponents() {

		// Create and populate the panel.
		labelFieldPanel = new JPanel(new SpringLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		lightOnList = new JComboBox<Boolean>(lightOnString);
		lightOnList.setSelectedIndex(0);

		mainPanel.add(lightOnList);
		// enemyList.addActionListener(this);

		fieldX = new JTextField(10);
		fieldY = new JTextField(10);
		fieldRadius = new JTextField(10);

		xLabel = new JLabel("x value: ", JLabel.TRAILING);
		yLabel = new JLabel("y value: ", JLabel.TRAILING);
		radiusLabel = new JLabel("radius: ", JLabel.TRAILING);

		xLabel.setLabelFor(fieldX);
		yLabel.setLabelFor(fieldY);
		radiusLabel.setLabelFor(fieldRadius);

		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(fieldX);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(fieldY);

		labelFieldPanel.add(radiusLabel);
		labelFieldPanel.add(fieldRadius);
		// mainPanel.add(createButton);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 3, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		createButton.addMouseListener(this);
		colorPickerButton.addMouseListener(this);

		mainPanel.add(labelFieldPanel);
		mainPanel.add(colorChooser);
		mainPanel.add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == createButton) {
			try {
				boolean lighton = (boolean) lightOnList.getSelectedItem();
				ColorDTO color = new ColorDTO(255, 0, 0);
				color = new ColorDTO(colorChooser.getColor().getRed(), colorChooser.getColor().getGreen(), colorChooser.getColor().getBlue());
				lightDTO = lightFacade.createLight(new Point(parseFloat(fieldX.getText()), parseFloat(fieldY.getText()), 0), color, parseInt(fieldRadius.getText()), lighton);
				gameFieldFacade.addLightToWorld(lightDTO.getId());
				frame.setVisible(false);
				setChanged();
				notifyObservers();
			} catch (NumberFormatException | ModelException e) {
				e.printStackTrace();
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
