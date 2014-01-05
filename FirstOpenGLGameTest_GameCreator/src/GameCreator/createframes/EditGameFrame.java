package GameCreator.createframes;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.WEST;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.lwjgl.opengl.Display;

import GameCreator.ErrorHandler;
import GameCreator.GameCreatorPanel;
import GameCreator.panels.SelectedSpritePanel;
import GameCreator.panels.SpriteTreePanel;
import be.davidcorp.applicationLayer.dto.GamefieldDTO;
import be.davidcorp.applicationLayer.dto.SpriteDTO;
import be.davidcorp.applicationLayer.facade.GameFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;
import be.davidcorp.view.game.GameLoop;

@SuppressWarnings("serial")
public class EditGameFrame extends JFrame implements MouseListener, Observer {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton openGLFrameButton;
	private JButton saveButton;
	private JButton loadButton;
	private GamefieldDTO field;
	private JCheckBox shadowsCheckBox;
	private SelectedSpritePanel selectedSpritePanel;
	private JPanel openGLPanel;

	private GameFieldFacade gameFieldFacade = new GameFieldFacade();
	private SpriteTreePanel spriteTreePanel;

	public EditGameFrame(String fieldName,
			boolean loadingAnAlreadyExistingGamefield) {
		if (!loadingAnAlreadyExistingGamefield) {
			createNewGamefield(fieldName);
		}
		field = gameFieldFacade.getGamefieldWithName(fieldName);

		setSize(new Dimension(1600, 800));
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

		openGLFrameButton = new JButton("Show GamePanel");
		saveButton = new JButton("save gamefield");
		loadButton = new JButton("load gamefield");
		shadowsCheckBox = new JCheckBox("Shadows on", true);
		selectedSpritePanel = new SelectedSpritePanel(this);

		spriteTreePanel = new SpriteTreePanel();
		spriteTreePanel.setName("spriteTreePanel");
	}

	private void addComponents() {
		mainPanel.setLayout(new BorderLayout());
		buttonPanel.add(openGLFrameButton);
		buttonPanel.add(saveButton);
		// buttonPanel.add(loadButton);
		buttonPanel.add(shadowsCheckBox);
		mainPanel.add(buttonPanel, NORTH);
		mainPanel.add(selectedSpritePanel.mainpanel, WEST);
		mainPanel.add(openGLPanel, CENTER);
	}

	private void initHandlers() {
		openGLFrameButton.addMouseListener(this);
		saveButton.addMouseListener(this);
		loadButton.addMouseListener(this);
		shadowsCheckBox.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				field.setShadowsOn(shadowsCheckBox.isSelected());
			}
		});
	}

	private void createDisplay(final GamefieldDTO field) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					gameFieldFacade.initializeGameField(field);

					GameCreatorPanel gamePanel = new GameCreatorPanel();
					gamePanel.addObserver(EditGameFrame.this);
					GameLoop gameLoop = new GameLoop(gamePanel, WIDTH, HEIGHT);

					Canvas canvas = new Canvas();
					canvas.setFocusable(true);
					canvas.setIgnoreRepaint(true);

					Display.setParent(canvas);

					canvas.setSize(WIDTH, HEIGHT);
					openGLPanel.add(canvas);
					gameLoop.start();

					openGLFrameButton.setEnabled(true);
				} catch (Exception e) {
					ErrorHandler.handleError(EditGameFrame.this, e);
				}

			}
		});
		thread.start();
		openGLFrameButton.setEnabled(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == openGLFrameButton && openGLFrameButton.isEnabled()) {
			createDisplay(field);
		}
		if (e.getSource() == saveButton) {
			new GameFacade().saveTheGame();
		}

		// if (e.getSource() == loadButton) {
		// loadGameField();
		// createDisplay(field);
		// }
	}

	// private void loadGameField() {
	// {
	// // field.setPause(true);
	// closeDisplay();
	// try {
	// // addSurroundingWalls();
	// field.setCreationMode(true);
	// } catch (DatabaseException e1) {
	// e1.printStackTrace();
	// }
	// // field.setPause(false);
	// }
	// }

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

	@Override
	public void update(Observable o, Object arg) {
		selectedSpritePanel.setSprite((SpriteDTO) (arg));
		mainPanel.revalidate();
		validate();
	}

}
