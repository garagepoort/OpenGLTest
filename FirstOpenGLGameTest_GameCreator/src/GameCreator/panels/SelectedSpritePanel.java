package GameCreator.panels;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.SpringUtilities;
import GameCreator.createframes.EditGameFrame;
import be.davidcorp.applicationLayer.dto.SpriteDTO;

public class SelectedSpritePanel extends Observable implements KeyListener, MouseListener{

	private SpriteDTO sprite;
	
	public JPanel mainpanel = new JPanel();
	
	private JLabel classLabel;
	private JLabel xLabel;
	private JLabel yLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;

	private JLabel classNameLabel;
	private JTextField xField;
	private JTextField yField;
	private JTextField widthField;
	private JTextField heightField;

	private JPanel labelFieldPanel;
	
	private JButton addTriggerButton;
	private EditGameFrame editGameFrame;
	
	public SelectedSpritePanel(EditGameFrame frame){
		editGameFrame=frame;
		labelFieldPanel = new JPanel(new SpringLayout());
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		labelFieldPanel.setLayout(new SpringLayout());
		mainpanel.setVisible(true);
		mainpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//setPreferredSize(new Dimension(200, 200));
		addComponents();
	}

	private void addComponents() {
		classLabel = new JLabel("classname:", JLabel.TRAILING);
		xLabel = new JLabel("x: ", JLabel.TRAILING);
		yLabel = new JLabel("y: ", JLabel.TRAILING);
		widthLabel = new JLabel("width: ", JLabel.TRAILING);
		heightLabel = new JLabel("height: ", JLabel.TRAILING);

		classNameLabel = new JLabel("classname:", JLabel.TRAILING);
		xField = new JTextField(10);
		yField = new JTextField(10);
		widthField = new JTextField(10);
		heightField = new JTextField(10);
		
		addTriggerButton = new JButton("add trigger");
		addTriggerButton.addMouseListener(this);
		
		xField.addKeyListener(this);
		yField.addKeyListener(this);
		widthField.addKeyListener(this);
		heightField.addKeyListener(this);

		classLabel.setLabelFor(classNameLabel);
		xLabel.setLabelFor(xField);
		yLabel.setLabelFor(yField);
		widthLabel.setLabelFor(widthField);
		heightLabel.setLabelFor(heightField);

		labelFieldPanel.add(classLabel);
		labelFieldPanel.add(classNameLabel);
		
		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(xField);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(yField);

		labelFieldPanel.add(widthLabel);
		labelFieldPanel.add(widthField);

		labelFieldPanel.add(heightLabel);
		labelFieldPanel.add(heightField);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 5, 2, //rows, cols
				6, 6,        //initX, initY
				6, 6);       //xPad, yPad

		mainpanel.add(labelFieldPanel);
		mainpanel.add(addTriggerButton);
	}

	public void setSprite(SpriteDTO s) {
		sprite =s;
		classNameLabel.setText(sprite.getClass().getSimpleName());
		xField.setText(s.getX()+"");
		yField.setText(s.getY()+"");
		widthField.setText(s.getWidth()+"");
		heightField.setText(s.getHeight()+"");
		setChanged();
		notifyObservers(s);
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				sprite.setX(Float.parseFloat(xField.getText()));
				sprite.setY(Float.parseFloat(yField.getText()));
				sprite.setWidth(Integer.parseInt(widthField.getText()));
				sprite.setHeight(Integer.parseInt(heightField.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == addTriggerButton){
			//TODO david implement trigger en shizzle
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	
}
