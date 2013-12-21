package GameCreator.createframes;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class CreateDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	
	public CreateDialog(String title, int width, int height){
		mainPanel = new JPanel();
		
		initComponents();
		
		setTitle(title);
//		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().add(mainPanel);
		// addComponents();
		setResizable(false);
		setSize(new Dimension(width, height));
		setVisible(true);
		addWindowListener(new NewWindowListener());
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	protected abstract void initComponents();
	
	private class NewWindowListener implements WindowListener {

		@Override
		public void windowClosing(WindowEvent e) {
			FrameFacade.closeCreateDialog(CreateDialog.this);
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
