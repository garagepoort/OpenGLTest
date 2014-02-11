package webdrivertests;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.AWTException;

import org.fest.swing.core.matcher.DialogMatcher;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.finder.WindowFinder;
import org.fest.swing.fixture.Containers;
import org.fest.swing.fixture.DialogFixture;
import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import GameCreator.createframes.FrameFacade;
import GameCreator.panels.SpriteTreePanel;

public class SpriteTreePanelWebDriverTest {

	private FrameFixture testFrame;
	private SpriteTreePanel frame;
	
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@Before
	public void setUp() {
		frame = GuiActionRunner.execute(new GuiQuery<SpriteTreePanel>() {

			@Override
			protected SpriteTreePanel executeInEDT() throws Throwable {
				return new SpriteTreePanel();
			}
		});
		testFrame = Containers.showInFrame(frame);
	}

	@After
	public void tearDown() {
		testFrame.cleanUp();
	}
	
	@Test
	public void whenDoubleClickedOnZombieInSpriteTree_thenOpenCreateEnemyDialog(){
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Enemies/ZOMBIE");
		assertCreateEnemyDialogOpen();
	}
	
	@Test
	public void whenDoubleClickedOnSpiderInSpriteTree_thenOpenCreateEnemyDialog(){
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Enemies/SPIDER");
		assertCreateEnemyDialogOpen();
	}


	@Test
	public void whenDoubleClickedOnHealthPotionInSpriteTree_thenOpenCreateItemDialog() throws AWTException{
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Items/HEALTHPOTION");
		assertCreateItemDialogOpen();
	}
	
	@Test
	public void whenDoubleClickedOnStaminaPotionInSpriteTree_thenOpenCreateItemDialog() throws AWTException{
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Items/STAMINAPOTION");
		assertCreateItemDialogOpen();
	}
	
	@Test
	public void whenDoubleClickedOnWallInSpriteTree_thenOpenCreateWallDialog(){
		testFrame.tree("spriteTree").doubleClickPath("Sprites/ConstructionSprites/WALL");
		assertCreateConstructionSpriteDialogOpen();
	}
	
	@Test
	public void whenDoubleClickedOnAxInSpriteTree_thenOpenCreateItemDialog(){
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Items/AX");
		assertCreateItemDialogOpen();
	}
	
	@Test
	public void whenDoubleClickedOnPistolInSpriteTree_thenOpenCreateItemDialog(){
		testFrame.tree("spriteTree").doubleClickPath("Sprites/Items/PISTOL");
		assertCreateItemDialogOpen();
	}

	private void assertCreateConstructionSpriteDialogOpen() {
		DialogFixture createEnemyDialogFixture = WindowFinder.findDialog(DialogMatcher.withName("CreateWallDialog")).using(testFrame.robot);
//		assertThat(FrameFacade.isCreateWallDialogOpen()).isTrue();
		createEnemyDialogFixture.cleanUp();
	}
	
	private void assertCreateItemDialogOpen() {
		DialogFixture createEnemyDialogFixture = WindowFinder.findDialog(DialogMatcher.withName("CreateItemDialog")).using(testFrame.robot);
//		assertThat(FrameFacade.isCreateItemDialogOpen()).isTrue();
		assertThat(createEnemyDialogFixture).isNotNull();
		createEnemyDialogFixture.cleanUp();
	}
	
	private void assertCreateEnemyDialogOpen() {
		DialogFixture createEnemyDialogFixture = WindowFinder.findDialog(DialogMatcher.withName("CreateEnemyDialog")).using(testFrame.robot);
//		assertThat(FrameFacade.isCreateEnemyDialogOpen()).isTrue();
		assertThat(createEnemyDialogFixture).isNotNull();
		createEnemyDialogFixture.cleanUp();
	}
	
}
