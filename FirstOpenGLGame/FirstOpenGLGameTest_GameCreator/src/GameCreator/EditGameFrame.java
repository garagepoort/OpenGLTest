package GameCreator;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.lwjgl.opengl.Display;

import GameCreator.panels.SpriteTreePanel;
import be.davidcorp.applicationLayer.facade.GameStarterFacade;
import main.java.be.davidcorp.view.game.GameLoop;

@SuppressWarnings("serial")
public class EditGameFrame extends JFrame implements MouseListener{

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private static final String EXTENSION = ".zip";
	private JPanel mainPanel;
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton loadButton;
	private JCheckBox shadowsCheckBox;
//	private SelectedSpritePanel selectedSpritePanel;
	private JPanel openGLPanel;
	
	private JTextField testTextField = new JTextField("test");

	private SpriteTreePanel spriteTreePanel;

	public EditGameFrame() {
		setLocationRelativeTo(null);
		setSize(new Dimension(1000, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		initComponents();
		addComponents();
		initHandlers();

		createDisplay();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(mainPanel);
		splitPane.setRightComponent(spriteTreePanel);
		getContentPane().add(splitPane);
		revalidate();
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
//		shadowsCheckBox.addChangeListener(new ChangeListener() {
//
//			@Override
//			public void stateChanged(ChangeEvent arg0) {
//				CurrentGameFieldManager.getCurrentGameField().setShadowsOn(shadowsCheckBox.isSelected());
//			}
//		});
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == saveButton) {
			saveFile();
		}

		// if (e.getSource() == loadButton) {
		// loadGameField();
		// createDisplay(field);
		// }
	}
	
	private int saveFile() {
	    JFileChooser fileChooser = new JFileChooser();
	    int status = fileChooser.showSaveDialog(this);

	    if (status == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();
	        try {
	            String fileName = selectedFile.getCanonicalPath();
	            if (!fileName.endsWith(EXTENSION)) {
	            	new GameStarterFacade().saveCustomCreatedGamefield(fileName);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return status;
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

	private void createDisplay() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
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
