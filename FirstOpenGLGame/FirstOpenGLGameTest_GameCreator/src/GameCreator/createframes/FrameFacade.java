package GameCreator.createframes;

import GameCreator.panels.SelectedSpritePanel;

public class FrameFacade {

	private static SelectedSpritePanel selectedSpritePanel;

	public static void openSelectedSpritePanel() {
		if(selectedSpritePanel == null){
			selectedSpritePanel = new SelectedSpritePanel();
		}
	}
	
	public static SelectedSpritePanel getSelectedSpritePanel() {
		return selectedSpritePanel;
	}


}
