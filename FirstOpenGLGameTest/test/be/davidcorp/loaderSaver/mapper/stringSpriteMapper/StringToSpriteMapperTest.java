package be.davidcorp.loaderSaver.mapper.stringSpriteMapper;

import static org.fest.assertions.Assertions.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;



public class StringToSpriteMapperTest {

	
	@Before
	public void initialize(){
	}
	
	@Test
	public void givenAZombieString_whenLoadAllSprites_thenReturnEnemy() throws IOException{
		String zombie = 
						"SPRITETYPE:ZOMBIE\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f";
		Enemy enemy = new StringToEnemyMapper().mapSprite(zombie);
		assertThat(enemy).isInstanceOf(Enemy.class);
		assertThat(enemy.getID()).isEqualTo(1);
		assertThat(enemy.getX()).isEqualTo(10f);
		assertThat(enemy.getY()).isEqualTo(11f);
	}
	
	@Test
	public void givenASpriteFileWithOneLight_whenLoadAllSprites_thenLightInsideLightRepository() throws IOException{
		String lightString = "SPRITETYPE:LIGHT\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "RADIUS:200\n"
						+ "RED:255\n"
						+ "GREEN:0\n"
						+ "BLUE:0";
		Light light = new StringToLightMapper().mapSprite(lightString);
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
		String construction = "SPRITETYPE:WALL\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f\n"
						+ "WIDTH:12\n"
						+ "HEIGHT:13";
		ConstructionSprite wall = new StringToConstructionSpriteMapper().mapSprite(construction);
		assertThat(wall).isInstanceOf(ConstructionSprite.class);
		assertThat(wall.getID()).isEqualTo(1);
		assertThat(wall.getX()).isEqualTo(10f);
		assertThat(wall.getY()).isEqualTo(11f);
		assertThat(wall.getWidth()).isEqualTo(12);
		assertThat(wall.getHeight()).isEqualTo(13);
	}
	
	@Test
	public void givenASpriteFileWithOneItem_whenLoadAllSprites_thenItemInsideItemRepository() throws IOException{
		String itemString = "SPRITETYPE:HEALTHPOTION\n"
						+ "ID:1\n"
						+ "X:10f\n"
						+ "Y:11f";
		Item item = new StringToItemMapper().mapSprite(itemString);
		assertThat(item).isInstanceOf(Item.class);
		assertThat(item.getID()).isEqualTo(1);
		assertThat(item.getX()).isEqualTo(10f);
		assertThat(item.getY()).isEqualTo(11f);
	}

}
