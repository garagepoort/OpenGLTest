package GameCreator.createframes;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;

import javax.swing.JDialog;
import javax.swing.JPanel;

public abstract class CreateDialog extends Observable {

	private JDialog dialog;
	private JPanel mainPanel;
	
	public CreateDialog(String title, int width, int height){
		dialog = new JDialog();
		mainPanel = new JPanel();
		
		initComponents();
		
		dialog.setTitle(title);
		dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		dialog.getContentPane().add(mainPanel);
		// addComponents();
		dialog.setResizable(false);
		dialog.setSize(new Dimension(width, height));
		dialog.setVisible(true);
		dialog.addWindowListener(new NewWindowListener());
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setVisible(boolean visible) {
		dialog.setVisible(visible);
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
