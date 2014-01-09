package GameCreator;

import static GameCreator.createframes.FrameFacade.openSelectedSpritePanel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import GameCreator.createframes.EditGameFrame;
import be.davidcorp.applicationLayer.dto.GamefieldDTO;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

@SuppressWarnings("serial")
public class GameCreatorFrame extends JFrame implements MouseListener {

	private JButton createGameField;
	private JButton loadGameField;
	private JTextField fieldNameTextField;
	private JPanel mainPanel;
	private JList<GamefieldDTO> list;
	private JScrollPane listScroller;
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();

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

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void createList() {
		list = new JList(gameFieldFacade.getAllGamefields().toArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
	}

	private void initComponents() {
		createList();
		createGameField = new JButton("create new field");
		loadGameField = new JButton("load gamefield");
		fieldNameTextField = new JTextField();
		fieldNameTextField.setPreferredSize(new Dimension(100, 20));

		mainPanel = new JPanel();
	}

	private void addComponents() {
		mainPanel.add(createGameField);
		mainPanel.add(loadGameField);
		mainPanel.add(fieldNameTextField);
		mainPanel.add(listScroller);
	}

	private void initHandlers() {
		createGameField.addMouseListener(this);
		loadGameField.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == createGameField) {
			setVisible(false);
			openSelectedSpritePanel();
			new EditGameFrame(fieldNameTextField.getText(), false);
		}
		if (e.getSource() == loadGameField) {
			setVisible(false);
			openSelectedSpritePanel();
			new EditGameFrame(list.getSelectedValue().getName(), true);
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
