package GameCreator;

import static GameCreator.createframes.FrameFacade.openSelectedSpritePanel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.davidcorp.applicationLayer.facade.GamecreationFacade;

@SuppressWarnings("serial")
public class GameCreatorFrame extends JFrame implements MouseListener {

	private JButton createGameField;
	private JButton loadGameField;
	private JTextField fieldNameTextField;
	private JPanel mainPanel;
	private GamecreationFacade gamecreationFacade = new GamecreationFacade();
	private JLabel saveLocationLabel = new JLabel("set save location");
	private JButton chooseSaveLocationButton;
	private File saveFileDirectory;

	public GameCreatorFrame() {
		setSize(new Dimension(800, 200));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		initComponents();
		addComponents();
		initHandlers();

		getContentPane().add(mainPanel);
		revalidate();
	}

	private void initComponents() {
		chooseSaveLocationButton = new JButton("Choose savelocation");
		createGameField = new JButton("create new field");
		loadGameField = new JButton("load gamefield");
		fieldNameTextField = new JTextField();
		fieldNameTextField.setPreferredSize(new Dimension(100, 20));
		mainPanel = new JPanel();
	}

	private void addComponents() {
		mainPanel.add(chooseSaveLocationButton);
		mainPanel.add(saveLocationLabel);
		mainPanel.add(createGameField);
		mainPanel.add(loadGameField);
		mainPanel.add(fieldNameTextField);
	}

	private void initHandlers() {
		createGameField.addMouseListener(this);
		loadGameField.addMouseListener(this);
		chooseSaveLocationButton.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == createGameField) {
			if (saveFileDirectory != null) {
				setVisible(false);
				openSelectedSpritePanel();
				ZipFile createGamefieldFile = gamecreationFacade.createAndSaveGamefieldToLocation(
						fieldNameTextField.getText(),
						saveFileDirectory.getAbsolutePath());
				gamecreationFacade.loadCreatedGamefield(createGamefieldFile);
				new EditGameFrame();
			}
		}
		if (e.getSource() == loadGameField) {
			ZipFile zipFile = openGamefieldMapFile();
			gamecreationFacade.loadCreatedGamefield(zipFile);
			setVisible(false);
			openSelectedSpritePanel();
			new EditGameFrame();
		}
		if (e.getSource() == chooseSaveLocationButton) {
			chooseSaveDirectory();
		}
	}

	private File chooseSaveDirectory() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = fileChooser.showOpenDialog(this);

		saveFileDirectory = null;
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			saveFileDirectory = fileChooser.getSelectedFile();
			saveLocationLabel.setText(saveFileDirectory.getAbsolutePath());
		}
		return saveFileDirectory;
	}

	private ZipFile openGamefieldMapFile() {
		JFileChooser loadEmp = new JFileChooser();// new dialog
		File selectedFile;// needed*

		if (loadEmp.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			selectedFile = loadEmp.getSelectedFile();
			if (selectedFile.canRead() && selectedFile.exists()) {
				try {
					return new ZipFile(selectedFile);
				} catch (IOException e) {
					ErrorHandler.handleError(this, e);
				}
			} else {
				ErrorHandler.handleError(this, new RuntimeException(
						"File cannot be opened"));
			}
		}
		ErrorHandler.handleError(this, new RuntimeException(
				"File cannot be opened"));
		return null;
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
