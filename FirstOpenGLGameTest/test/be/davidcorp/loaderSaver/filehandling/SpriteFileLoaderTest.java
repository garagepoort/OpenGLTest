package be.davidcorp.loaderSaver.filehandling;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;


public class SpriteFileLoaderTest {

	private SpriteFileLoader loader;
	private FileUtility fileUtility;
	
	@Before
	public void initialize(){
		loader = spy(new SpriteFileLoader(null));		
		fileUtility = mock(FileUtility.class);
		loader.setFileUtility(fileUtility);
	}
	@Test
	public void givenASpriteFileWithOneEnemy_whenLoadAllSprites_thenEnemyInsideEnemyRepository() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
				"ENEMY\n"
						+ "SPRITETYPE:ZOMBIE\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END");
		loader.loadAllSprites();
		Enemy enemy = new EnemyRepository().getSprite(1);
		assertThat(enemy).isInstanceOf(Zombie.class);
		assertThat(enemy.getID()).isEqualTo(1);
		assertThat(enemy.getX()).isEqualTo(10f);
		assertThat(enemy.getY()).isEqualTo(11f);
	}
	
	@Test
	public void givenASpriteFileWithOneLight_whenLoadAllSprites_thenLightInsideLightRepository() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
					"LIGHT\n" 
							+ "SPRITETYPE:LIGHT\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "RADIUS:200\n"
						+ "RED:255\n"
						+ "GREEN:0\n"
						+ "BLUE:0\n"
						+ "END");
		loader.loadAllSprites();
		assertThat(new LightRepository().getSprite(1)).isNotNull();
	}
	
	@Test
	public void givenASpriteFileWithOneWall_whenLoadAllSprites_thenWallInsideConstructionSpriteRepository() throws  IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
				"CONSTRUCTION\n"
						+ "SPRITETYPE:WALL\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "WIDTH:12\n"
						+ "HEIGHT:13\n"
						+ "END");
		loader.loadAllSprites();
		assertThat(new ConstructionSpriteRepository().getSprite(1)).isNotNull();
	}
	
	@Test
	public void givenASpriteFileWithOneItem_whenLoadAllSprites_thenItemInsideItemRepository() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
						"ITEM\n"
						+ "SPRITETYPE:HEALTHPOTION\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END");
		loader.loadAllSprites();
		assertThat(new ItemRepository().getSprite(1)).isNotNull();
	}
	
	@Test
	public void givenASpriteFileWithTwoEnemies_whenLoadAllSprites_thenEnemyRepositoryIsCalled() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
						"ENEMY\n"
						+ "SPRITETYPE:ZOMBIE\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END\n"
						
						+ "ENEMY\n"
						+ "SPRITETYPE:SPIDER\n"
						+ "ID:2\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END");

		loader.loadAllSprites();
		assertThat(new EnemyRepository().getSprite(1)).isNotNull();
		assertThat(new EnemyRepository().getSprite(2)).isNotNull();
	}
	
	
	@Test
	public void givenASpriteFileWithEnemiesAndWeapons_whenLoadAllSprites_thenWeaponsAndEnemiesLoaded() throws IOException{
		//given
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
						"ENEMY\n"
						+ "SPRITETYPE:ZOMBIE\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END\n"
						
						+ "ENEMY\n"
						+ "SPRITETYPE:SPIDER\n"
						+ "ID:2\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "END\n"
						
						+ "ITEM\n"
						+ "SPRITETYPE:PISTOL\n"
						+ "ID:3\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "AANTAL_BULLETS:30\n"
						+ "END"
				);
		
		//when
		loader.loadAllSprites();

		//then
		assertThat(new EnemyRepository().getSprite(1)).isNotNull();
		assertThat(new EnemyRepository().getSprite(2)).isNotNull();
		assertThat(new ItemRepository().getSprite(3)).isNotNull();
	}
}
