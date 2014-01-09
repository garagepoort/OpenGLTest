package GameCreator.createframes;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CreateDialogWindowListener implements WindowListener{

	private CreateDialog createDialog;

	public CreateDialogWindowListener(CreateDialog createDialog){
		this.createDialog = createDialog;
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		FrameFacade.closeCreateDialog(createDialog);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		FrameFacade.closeCreateDialog(createDialog);
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
