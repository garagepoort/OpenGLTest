package be.davidcorp.loaderSaver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.item.weapon.Weapon;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.repository.ConstructionSpriteRepository;
import be.davidcorp.loaderSaver.repository.EnemyRepository;
import be.davidcorp.loaderSaver.repository.ItemRepository;
import be.davidcorp.loaderSaver.repository.LightRepository;
import be.davidcorp.loaderSaver.repository.WeaponRepository;


public class SpriteRepositoryLoaderTest {

	private SpriteRepositoryLoader loader;
	private FileUtility fileUtility;
	
	@Before
	public void initialize(){
		loader = spy(new SpriteRepositoryLoader(null));		
		fileUtility = Mockito.mock(FileUtility.class);
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
		loader.loadAllSprites("gamefield");
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
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "RADIUS:200\n"
						+ "RED:255\n"
						+ "GREEN:0\n"
						+ "BLUE:0\n"
						+ "END");
		loader.loadAllSprites("gamefield");
		Light light = new LightRepository().getSprite(1);
		assertThat(light).isInstanceOf(Light.class);
		assertThat(light.getID()).isEqualTo(1);
		assertThat(light.getX()).isEqualTo(10f);
		assertThat(light.getY()).isEqualTo(11f);
		assertThat(light.getRadius()).isEqualTo(200);
		assertThat(light.getColor().getRed()).isEqualTo(255);
		assertThat(light.getColor().getGreen()).isEqualTo(0);
		assertThat(light.getColor().getBlue()).isEqualTo(0);
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
		loader.loadAllSprites("gamefield");
		ConstructionSprite wall = new ConstructionSpriteRepository().getSprite(1);
		assertThat(wall).isInstanceOf(Wall.class);
		assertThat(wall.getID()).isEqualTo(1);
		assertThat(wall.getX()).isEqualTo(10f);
		assertThat(wall.getY()).isEqualTo(11f);
		assertThat(wall.getWidth()).isEqualTo(12);
		assertThat(wall.getHeight()).isEqualTo(13);
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
		loader.loadAllSprites("gamefield");
		Item item = new ItemRepository().getSprite(1);
		assertThat(item).isInstanceOf(HealthPotion.class);
		assertThat(item.getID()).isEqualTo(1);
		assertThat(item.getX()).isEqualTo(10f);
		assertThat(item.getY()).isEqualTo(11f);
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

		loader.loadAllSprites("gamefield");
		Enemy enemy = new EnemyRepository().getSprite(1);
		assertThat(enemy).isInstanceOf(Zombie.class);
		assertThat(enemy.getID()).isEqualTo(1);
		assertThat(enemy.getX()).isEqualTo(10f);
		assertThat(enemy.getY()).isEqualTo(11f);
		
		Enemy enemy2 = new EnemyRepository().getSprite(2);
		assertThat(enemy2).isInstanceOf(Spider.class);
		assertThat(enemy2.getID()).isEqualTo(2);
		assertThat(enemy2.getX()).isEqualTo(10f);
		assertThat(enemy2.getY()).isEqualTo(11f);
	}
	
	
	@Test
	public void givenASpriteFileWithEnemiesAndWeapons_whenLoadAllSprites_thenWeaponsAndEnemiesLoaded() throws IOException{
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
						
						+ "WEAPON\n"
						+ "SPRITETYPE:PISTOL\n"
						+ "ID:3\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "AANTAL_BULLETS:30\n"
						+ "END"
				);
		loader.loadAllSprites("gamefield");
		Enemy enemy = new EnemyRepository().getSprite(1);
		assertThat(enemy).isInstanceOf(Zombie.class);
		assertThat(enemy.getID()).isEqualTo(1);
		assertThat(enemy.getX()).isEqualTo(10f);
		assertThat(enemy.getY()).isEqualTo(11f);
		
		Enemy enemy2 = new EnemyRepository().getSprite(2);
		assertThat(enemy2).isInstanceOf(Spider.class);
		assertThat(enemy2.getID()).isEqualTo(2);
		assertThat(enemy2.getX()).isEqualTo(10f);
		assertThat(enemy2.getY()).isEqualTo(11f);
		
		Weapon weapon = new WeaponRepository().getSprite(3);
		assertThat(weapon).isInstanceOf(Pistol.class);
		Pistol pistol = (Pistol) weapon;
		assertThat(pistol.getID()).isEqualTo(3);
		assertThat(pistol.getX()).isEqualTo(10f);
		assertThat(pistol.getY()).isEqualTo(11f); 
		assertThat(pistol.getAmmoLeft()).isEqualTo(30);
	}
}
