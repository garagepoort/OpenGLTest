package GameCreator.createframes;

import GameCreator.panels.SelectedSpritePanel;

public class FrameFacade {

//	private static CreateWallDialog createWallDialog;
//	private static CreateEnemyDialog createEnemyDialog;
//	private static CreateLightDialog createLightDialog;
//	private static CreateItemDialog createItemDialog;
	private static SelectedSpritePanel selectedSpritePanel;
//	
//	public static void openCreateWallDialog(){
//		if(createWallDialog == null){
//			createWallDialog = new CreateWallDialog();
//		}
//	}
//
//	public static void openEnemyDialog(EnemyType enemyType) {
//		if(createEnemyDialog == null){
//			createEnemyDialog = new CreateEnemyDialog(enemyType);
//		}
//	}
//	
//	public static void openCreateLightDialog() {
//		if(createLightDialog == null){
//			createLightDialog = new CreateLightDialog();
//		}
//	}
//	
//	public static void openItemDialog(ItemType itemType) {
//		if(createItemDialog == null){
//			createItemDialog = new CreateItemDialog(itemType);
//		}
//	}
//	
	public static void openSelectedSpritePanel() {
		if(selectedSpritePanel == null){
			selectedSpritePanel = new SelectedSpritePanel();
		}
	}
	
	public static SelectedSpritePanel getSelectedSpritePanel() {
		return selectedSpritePanel;
	}
//	
//	public static void closeCreateDialog(CreateDialog createDialog) {	
//		createDialog.setVisible(false);
//		createDialog.dispose();
//		if(createDialog instanceof CreateWallDialog){
//			createWallDialog = null;
//		}
//		if(createDialog instanceof CreateLightDialog){
//			createLightDialog = null;
//		}
//		if(createDialog instanceof CreateEnemyDialog){
//			createEnemyDialog = null;
//		}
//		if(createDialog instanceof CreateItemDialog){
//			createItemDialog = null;
//		}
//	}
//
//	public static boolean isCreateWallDialogOpen(){
//		return createWallDialog != null;
//	}
//
//	public static boolean isCreateEnemyDialogOpen(){
//		return createEnemyDialog != null;
//	}
//	public static boolean isCreateItemDialogOpen(){
//		return createItemDialog != null;
//	}
//	public static boolean isCreateLightDialogOpen(){
//		return createLightDialog != null;
//	}
//	
//	public static boolean isCreateWeaponDialogOpen() {
//		return false;
//	}

}
