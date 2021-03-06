package be.davidcorp.database.filehandling;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.trigger.Trigger;
import be.davidcorp.domain.trigger.TriggerBuilder;
import be.davidcorp.domain.trigger.TriggerWhen;
import be.davidcorp.domain.trigger.triggerableEvents.LightSwitchEvent;


public class SpriteSerializerTest {
	
	private static final int ID = 1;
	private static final int HEIGHT = 13;
	private static final int WIDTH = 12;
	private static final int Y = 11;
	private static final int X = 10;
	private static final int ATTACK_DAMAGE = 100;
	private File file;

	@Before
	public void init(){
		file = new File("resources/test/testfile.ser");
	}
	
	@After
	public void breakDown(){
		file.delete();
	}
	
	@Test
	public void givenAnEnemy_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		Enemy sprite = aZombie();

		//when
		SpriteSerializer.saveSpritesToFile(newArrayList(sprite),file);
		List<Sprite> deserializeSprites = SpriteSerializer.deserializeSprites(new FileInputStream(file));
		
		//then
		assertThat(deserializeSprites).hasSize(1);
		Enemy sprite2 = (Enemy) deserializeSprites.get(0);
		
		assertEnemiesAreEqual(sprite, sprite2);
	}
	
	@Test
	public void givenAWallWithTriggerToLight_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		ConstructionSprite wall = aWall();
		Light light = aLight();
		LightSwitchEvent lightSwitchEvent = new LightSwitchEvent();
		TriggerBuilder.aTrigger()
			.withSource(wall)
			.withID(5)
			.triggeredWhen(TriggerWhen.ONUSE)
			.withAnotherTriggerable(light, lightSwitchEvent)
			.build();
		//when
		SpriteSerializer.saveSpritesToFile(newArrayList(wall), file);;
		List<Sprite> deserializeSprites = SpriteSerializer.deserializeSprites(new FileInputStream(file));
		
		//then
		assertThat(deserializeSprites).hasSize(1);
		ConstructionSprite wallResult = (ConstructionSprite) deserializeSprites.get(0);
		Trigger trigger = wallResult.getAllTriggers().get(0);
		
		
		assertThat(wallResult.getAllTriggers()).hasSize(1);
		assertThat(trigger.triggerWhen).isEqualTo(TriggerWhen.ONUSE);
		assertThat(trigger.getSource()).isEqualTo(wallResult);
	}

	private void assertEnemiesAreEqual(Enemy sprite, Enemy sprite2) {
		assertThat(sprite2.getID()).isEqualTo(sprite.getID());
		assertThat(sprite2.getSpeed()).isEqualTo(sprite.getSpeed());
		assertThat(sprite2.getX()).isEqualTo(sprite.getX());
		assertThat(sprite2.getY()).isEqualTo(sprite.getY());
		assertThat(sprite2.getWidth()).isEqualTo(sprite.getWidth());
		assertThat(sprite2.getHeight()).isEqualTo(sprite.getHeight());
		assertThat(sprite2.getAttackDamage()).isEqualTo(sprite.getAttackDamage());
	}
	
	private ConstructionSprite aWall() {
		return new ConstructionSpriteBuilder()
			.withID(1)
			.withSpriteType(SpriteType.WALL)
			.build();
	}

	private Light aLight() {
		return new LightTestBuilder()
			.withRadius(200)
			.withLightOn(false)
			.withID(1)
			.build();
	}


	private Enemy aZombie() {
		return new EnemyBuilder()
			.withAttackDamage(ATTACK_DAMAGE)
			.withX(X)
			.withY(Y)
			.withWidth(WIDTH)
			.withHeight(HEIGHT)
			.withID(ID)
			.withSpriteType(SpriteType.ZOMBIE)
			.build();
	}
}
