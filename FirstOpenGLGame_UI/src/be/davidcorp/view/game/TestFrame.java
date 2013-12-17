package be.davidcorp.view.game;

import java.awt.Canvas;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.applicationLayer.facade.GameFacade;

@SuppressWarnings("serial")
public class TestFrame extends JFrame{

	public TestFrame() throws IOException, LWJGLException, MapperException{
		new GameFacade().startApplication();
		GameLoop gameLoop = new GameLoop(new PlayGamePanel(),800, 600);
		
		Canvas canvas = new Canvas();
		canvas.setFocusable(true);
		canvas.setIgnoreRepaint(true);
		
		Display.setParent(canvas);
		
		canvas.setSize(800, 600);
		setSize(new Dimension(800, 600));

		this.add(canvas);
		setVisible(true);
		
		
		gameLoop.start();
	}

	public void initializeSplitPane(Canvas canvas) {
		JSplitPane jSplitPane = new JSplitPane();
		jSplitPane.setDividerLocation(JSplitPane.HORIZONTAL_SPLIT);
		jSplitPane.setLeftComponent(canvas);
		jSplitPane.setRightComponent(new JButton("test button"));
		this.add(jSplitPane);
	}

	public static void main(String[] args) throws IOException, LWJGLException, MapperException{
		new TestFrame();
	}
}
