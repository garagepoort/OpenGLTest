package GameCreator.createframes;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.opengl.Display;

import GameCreator.ErrorHandler;
import GameCreator.GameCreatorPanel;
import GameCreator.panels.SpriteTreePanel;
import be.davidcorp.applicationLayer.dto.GamefieldDTO;
import be.davidcorp.applicationLayer.facade.GameFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.view.game.GameLoop;

@SuppressWarnings("serial")
public class EditGameFrame extends JFrame implements MouseListener{

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton loadButton;
	private GamefieldDTO field;
	private JCheckBox shadowsCheckBox;
//	private SelectedSpritePanel selectedSpritePanel;
	private JPanel openGLPanel;
	
	private JTextField testTextField = new JTextField("test");

	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	private SpriteTreePanel spriteTreePanel;

	public EditGameFrame(String fieldName, boolean loadingAnAlreadyExistingGamefield) {
		if (!loadingAnAlreadyExistingGamefield) {
			createNewGamefield(fieldName);
		}
		field = gameFieldFacade.getGamefieldWithName(fieldName);

		setLocationRelativeTo(null);
		setSize(new Dimension(1000, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		initComponents();
		addComponents();
		initHandlers();

		createDisplay(field);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(mainPanel);
		splitPane.setRightComponent(spriteTreePanel);
		getContentPane().add(splitPane);
		revalidate();
	}

	private void createNewGamefield(String fieldName) {
		try {
			gameFieldFacade.createNewGamefield(fieldName, 1800, 1800);
		} catch (Exception e) {
			ErrorHandler.handleError(this, e);
		}
	}

	private void initComponents() {
		mainPanel = new JPanel();
		openGLPanel = new JPanel();
		openGLPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		buttonPanel = new JPanel();

		saveButton = new JButton("save gamefield");
		loadButton = new JButton("load gamefield");
		shadowsCheckBox = new JCheckBox("Shadows on", true);
//		selectedSpritePanel = new SelectedSpritePanel();

		spriteTreePanel = new SpriteTreePanel();
		spriteTreePanel.setName("spriteTreePanel");
	}

	private void addComponents() {
		mainPanel.setLayout(new BorderLayout());
		buttonPanel.add(saveButton);
		// buttonPanel.add(loadButton);
		buttonPanel.add(shadowsCheckBox);
		mainPanel.add(buttonPanel, NORTH);
//		mainPanel.add(selectedSpritePanel.mainpanel, WEST);
		mainPanel.add(openGLPanel, CENTER);
		mainPanel.add(testTextField, SOUTH);
	}

	private void initHandlers() {
		saveButton.addMouseListener(this);
		loadButton.addMouseListener(this);
		shadowsCheckBox.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				field.setShadowsOn(shadowsCheckBox.isSelected());
			}
		});
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == saveButton) {
			new GameFacade().saveTheGame();
		}

		// if (e.getSource() == loadButton) {
		// loadGameField();
		// createDisplay(field);
		// }
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

	private void createDisplay(final GamefieldDTO field) {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					gameFieldFacade.initializeGameField(field, true);
					
					GameCreatorPanel gamePanel = new GameCreatorPanel();
					GameLoop gameLoop = new GameLoop(gamePanel, WIDTH, HEIGHT, true);
					
					Canvas canvas = new Canvas();
					canvas.setFocusable(true);
					canvas.setIgnoreRepaint(true);
					
					Display.setParent(canvas);
					
					canvas.setSize(WIDTH, HEIGHT);
					openGLPanel.add(canvas);
					gameLoop.start();
					
				} catch (Exception e) {
					ErrorHandler.handleError(EditGameFrame.this, e);
				}
				
			}
		});
		thread.start();
	}

}
