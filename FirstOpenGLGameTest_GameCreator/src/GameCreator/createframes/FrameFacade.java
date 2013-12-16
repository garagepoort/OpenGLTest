package GameCreator.createframes;

import be.davidcorp.applicationLayer.dto.mapper.EnemyType;

public class FrameFacade {

	private static CreateWallFrame createWallFrame;
	private static CreateEnemyFrame createEnemyFrame;
	
	public static void openCreateWallFrame(){
		if(createWallFrame == null){
			createWallFrame = new CreateWallFrame();
		}
	}

	public static void closeCreateWallFrame() {
		createWallFrame = null;
	}

	public static void openEnemyFrame(EnemyType enemyType) {
		if(createEnemyFrame == null){
			createEnemyFrame = new CreateEnemyFrame(enemyType);
		}
	}
	
	public static void closeCreateEnemyFrame() {
		createEnemyFrame = null;
	}
}
