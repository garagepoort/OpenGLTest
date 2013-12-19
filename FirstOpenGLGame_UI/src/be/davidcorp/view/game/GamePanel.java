package be.davidcorp.view.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.inputControl.InputController;
import be.davidcorp.inputControl.MouseButton;
import be.davidcorp.view.drawer.GamePanelDrawer;
import be.davidcorp.view.ui.panel.Panel;

public class GamePanel extends Observable{

	private ArrayList<Panel> panels = new ArrayList<>();
	private ArrayList<Panel> panelsToAdd = new ArrayList<>();
	
	private GamePanelDrawer gamePanelDrawer = new GamePanelDrawer();
	private InputController inputController;
	
	public void setInputController(InputController inputController) {
		this.inputController = inputController;
	}
	
	public void render() throws IOException, MapperException {
		removeClosedPanels();
		renderPanels();
	}
	
	public void checkInput(){
		inputController.checkInput();
	}

	public void addPanel(Panel panel) {
		panelsToAdd.add(panel);
	}
	
	public void removePanel(Panel panel) {
		panelsToAdd.remove(panel);
	}

	public List<Panel> getPanels() {
		return panels;
	}
	
	private void addNewlyOpenedPanels() {
		panels.addAll(panelsToAdd);
		panelsToAdd.clear();
	}
	

	public boolean handlePanelsOnClick(float x, float y, MouseButton buttonClicked) {
		boolean inPanel = false;
		for (Panel p : panels) {
			if (p.checkMouseClick((int) x, (int) y, buttonClicked)) {
				inPanel = true;
			}
		}
		addNewlyOpenedPanels();
		return inPanel;
	}
	
	private void removeClosedPanels() {
		ArrayList<Panel> rPanels = new ArrayList<>();
		for (Panel p : panels) {
			if (p.isClosed()) {
				rPanels.add(p);
			}
		}
		for (Panel p : rPanels) {
			panels.remove(p);
		}
	}
	

	private void renderPanels() throws IOException, MapperException {
		gamePanelDrawer.drawGamePanel(this);
	}
	
	protected GamePanelDrawer getGamePanelDrawer() {
		return gamePanelDrawer;
	}
}
