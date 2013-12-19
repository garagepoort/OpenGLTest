package GameCreator.createframes;

import be.davidcorp.applicationLayer.dto.mapper.EnemyType;
import be.davidcorp.applicationLayer.dto.mapper.ItemType;

public class FrameFacade {

	private static CreateWallDialog createWallDialog;
	private static CreateEnemyDialog createEnemyDialog;
	private static CreateLightDialog createLightDialog;
	private static CreateItemDialog createItemDialog;
	
	public static void openCreateWallDialog(){
		if(createWallDialog == null){
			createWallDialog = new CreateWallDialog();
		}
	}

	public static void openEnemyDialog(EnemyType enemyType) {
		if(createEnemyDialog == null){
			createEnemyDialog = new CreateEnemyDialog(enemyType);
		}
	}
	
	public static void openCreateLightDialog() {
		if(createLightDialog == null){
			createLightDialog = new CreateLightDialog();
		}
	}
	
	public static void openItemDialog(ItemType itemType) {
		if(createItemDialog == null){
			createItemDialog = new CreateItemDialog(itemType);
		}
	}
	
	public static void closeCreateDialog(CreateDialog createDialog) {	
		createDialog.setVisible(false);
		if(createDialog instanceof CreateWallDialog){
			createWallDialog = null;
		}
		if(createDialog instanceof CreateLightDialog){
			createWallDialog = null;
		}
		if(createDialog instanceof CreateEnemyDialog){
			createWallDialog = null;
		}
	}

}
