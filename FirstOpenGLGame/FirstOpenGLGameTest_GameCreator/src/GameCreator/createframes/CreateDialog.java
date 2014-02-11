package GameCreator.createframes;

import java.awt.Dimension;

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
//		addWindowListener(new CreateDialogWindowListener(this));
	}
	
	public JPanel getMainPanel() {
		return mainPanel;
	}

	protected abstract void initComponents();
	
}
