package GameCreator.createframes;

import static GameCreator.createframes.FrameFacade.closeCreateWallFrame;

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

import GameCreator.ErrorHandler;
import GameCreator.SpringUtilities;
import be.davidcorp.applicationLayer.dto.EnemyDTO;
import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.exception.ModelException;
import be.davidcorp.applicationLayer.facade.EnemyFacade;
import be.davidcorp.applicationLayer.facade.GameFieldFacade;

public class CreateEnemyFrame extends Observable implements MouseListener {

	private JDialog dialog = new JDialog();
	private EnemyType enemyType;

	private JPanel mainPanel;
	private JPanel labelFieldPanel;

	private JTextField fieldX;
	private JTextField fieldY;

	private JLabel xLabel;
	private JLabel yLabel;

	private JButton createButton = new JButton("create Enemy");
	private JButton colorPickerButton = new JButton("pick color");

	private EnemyFacade enemyFacade = new EnemyFacade();
	private GameFieldFacade gameFieldFacade = new GameFieldFacade();

	public CreateEnemyFrame(EnemyType enemyType) {
		this.enemyType = enemyType;
		initComponents();
		dialog.setTitle("Create new " + enemyType.toString().toLowerCase());
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.getContentPane().add(mainPanel);
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

		xLabel = new JLabel("x value: ", JLabel.TRAILING);
		yLabel = new JLabel("y value: ", JLabel.TRAILING);

		xLabel.setLabelFor(fieldX);
		yLabel.setLabelFor(fieldY);

		labelFieldPanel.add(xLabel);
		labelFieldPanel.add(fieldX);

		labelFieldPanel.add(yLabel);
		labelFieldPanel.add(fieldY);

		// mainPanel.add(createButton);

		SpringUtilities.makeCompactGrid(labelFieldPanel, 2, 2, // rows, cols
				6, 6, // initX, initY
				6, 6); // xPad, yPad

		createButton.addMouseListener(this);
		colorPickerButton.addMouseListener(this);

		mainPanel.add(labelFieldPanel);
		mainPanel.add(createButton);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (event.getSource() == createButton) {
			try {
				gameFieldFacade.addEnemyToWorld(createEnemy().getId());
				dialog.setVisible(false);
				setChanged();
				notifyObservers();
			} catch (ModelException e) {
				ErrorHandler.handleError(dialog, e);
			}
		}
	}

	public EnemyDTO createEnemy() throws ModelException {
		EnemyDTO enemyDTO = null;
		switch (enemyType) {
			case ZOMBIE :
				enemyDTO = enemyFacade.createZombie(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()));
				break;
			case SPIDER :
				enemyDTO = enemyFacade.createSpider(Float.parseFloat(fieldX.getText()), Float.parseFloat(fieldY.getText()));
				break;
			default :
				break;
		}
		return enemyDTO;
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
