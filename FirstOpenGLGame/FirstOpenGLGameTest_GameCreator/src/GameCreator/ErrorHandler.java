package GameCreator;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ErrorHandler {

	public static void handleError(JFrame frame, Exception exception){
		JOptionPane.showMessageDialog(frame, exception.getMessage());
	}

	public static void handleError(JDialog dialog, Exception exception) {
		JOptionPane.showMessageDialog(dialog, exception.getMessage());
	}

	public static void handleError(JPanel panel, Exception exception) {
		JOptionPane.showMessageDialog(panel, exception.getMessage());
	}
}
