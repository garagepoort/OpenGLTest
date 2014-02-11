package GameCreator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorPickerFrame extends JFrame implements ChangeListener{

	public JColorChooser colorChooser = new JColorChooser();
	public Color currentColor;
	public ColorPickerFrame(){

		//initComponents();
		getContentPane().add(colorChooser);
		colorChooser.getSelectionModel().addChangeListener(this);
		//addComponents();

		setResizable(false);
		setSize(new Dimension(400,300));
		setVisible(true);
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		currentColor = colorChooser.getColor();
	}
	

}
