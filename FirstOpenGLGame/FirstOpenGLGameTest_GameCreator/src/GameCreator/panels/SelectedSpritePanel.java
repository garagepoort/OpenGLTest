package GameCreator.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.ConstructionSpriteDTO;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.applicationLayer.facade.ConstructionSpriteFacade;
import be.davidcorp.applicationLayer.facade.EnemyFacade;
import be.davidcorp.applicationLayer.facade.ItemFacade;
import be.davidcorp.applicationLayer.facade.LightFacade;

@SuppressWarnings("serial")
public class SelectedSpritePanel extends JFrame implements KeyListener, MouseListener{

	private static final EnemyFacade enemyFacade = new EnemyFacade();
	private static final LightFacade lightFacade = new LightFacade();
	private static final ConstructionSpriteFacade constructionSpriteFacade = new ConstructionSpriteFacade();
	private static final ItemFacade itemFacade = new ItemFacade();
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
	
	public SelectedSpritePanel(){
		
		setSize(new Dimension(400, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		labelFieldPanel = new JPanel(new SpringLayout());
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		labelFieldPanel.setLayout(new SpringLayout());
		mainpanel.setVisible(true);
		mainpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainpanel.setPreferredSize(new Dimension(200, 600));
		//setPreferredSize(new Dimension(200, 200));
		addComponents();
		getContentPane().add(mainpanel);
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
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				sprite.setX(Float.parseFloat(xField.getText()));
				sprite.setY(Float.parseFloat(yField.getText()));
				sprite.setWidth(Integer.parseInt(widthField.getText()));
				sprite.setHeight(Integer.parseInt(heightField.getText()));
				
				if(sprite instanceof ItemDTO){
					itemFacade.updateItem((ItemDTO) sprite);
				}
				if(sprite instanceof ConstructionSpriteDTO){
					constructionSpriteFacade.updateConstructionSprite((ConstructionSpriteDTO) sprite);
				}
				if(sprite instanceof LightDTO){
					lightFacade.updateLight((LightDTO) sprite);
				}
				if(sprite instanceof EnemyDTO){
					enemyFacade.updateEnemy((EnemyDTO) sprite);
				}
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
