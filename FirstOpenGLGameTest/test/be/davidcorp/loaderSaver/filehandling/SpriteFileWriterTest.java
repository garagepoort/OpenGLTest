package be.davidcorp.loaderSaver.filehandling;

import static be.davidcorp.domain.sprite.SpriteType.CONSTRUCTION;
import static be.davidcorp.domain.sprite.SpriteType.ITEM;
import static be.davidcorp.domain.sprite.SpriteType.LIGHT;
import static be.davidcorp.domain.sprite.SpriteType.WALL;
import static be.davidcorp.loaderSaver.SpriteProperty.AANTAL_BULLETS;
import static be.davidcorp.loaderSaver.SpriteProperty.LIGHTON;
import static be.davidcorp.loaderSaver.SpriteProperty.RADIUS;
import static be.davidcorp.loaderSaver.SpriteProperty.SPRITETYPE;
import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.test.builder.PistolTestBuilder;
import be.davidcorp.loaderSaver.SpriteProperty;


public class SpriteFileWriterTest {
	
	private SpriteFileWriter spriteFileWriter;
	private File file;
	private FileUtility fileUtility;
	@Before
	public void init(){
		file = new File("resources/test/testfile.txt");
		spriteFileWriter = new SpriteFileWriter(file);
		fileUtility = new FileUtility();
	}
	
	@After
	public void breakDown(){
		file.delete();
	}
	
	@Test
	public void givenAnEnemy_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		Sprite sprite = aZombie();

		//when
		spriteFileWriter.saveSprites(newArrayList(sprite));
		
		//then
		String result = fileUtility.getFileContent(file);
		assertThat(result).contains("ENEMY");
		assertThat(result).contains("SPRITETYPE:ZOMBIE");
		assertThat(result).contains(SpriteProperty.ID +":"+ 1);
		assertThat(result).contains("X:10");
		assertThat(result).contains("Y:11");
		assertThat(result).contains("WIDTH:12");
		assertThat(result).contains("HEIGHT:13");
		assertThat(result).contains("ATTACKDAMAGE:100");
	}
	
	@Test
	public void givenALight_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		Sprite sprite = aLight();
		
		//when
		spriteFileWriter.saveSprites(newArrayList(sprite));
		
		//then
		String result = fileUtility.getFileContent(file);
		assertThat(result).contains(LIGHT.toString());
		assertThat(result).contains(SPRITETYPE + ":" + "LIGHT");
		assertThat(result).contains(LIGHTON + ":" + "false");
		assertThat(result).contains(RADIUS +  ":" + 200);
	}
	
	@Test
	public void givenAPistol_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		Sprite sprite = aPistol();
		
		//when
		spriteFileWriter.saveSprites(newArrayList(sprite));
		
		//then
		String result = fileUtility.getFileContent(file);
		assertThat(result).contains(ITEM.toString());
		assertThat(result).contains(SPRITETYPE + ":" + SpriteType.PISTOL);
		assertThat(result).contains(AANTAL_BULLETS + ":" + 500);
	}
	
	@Test
	public void givenAWall_whenSave_thenIsSavedCorrectly() throws IOException{
		//given
		Sprite sprite = aWall();
		
		//when
		spriteFileWriter.saveSprites(newArrayList(sprite));
		
		//then
		String result = fileUtility.getFileContent(file);
		assertThat(result).contains(CONSTRUCTION.toString());
		assertThat(result).contains(SPRITETYPE + ":" + WALL);
		assertThat(result).contains(SpriteProperty.USABLERANGE + ":" + 212);
	}

	private Sprite aWall() {
		return new ConstructionSpriteBuilder()
			.withUsableRange(212)
			.withID(1)
			.build();
	}


	private Sprite aPistol() {
		return new PistolTestBuilder()
			.withMaximumAmmo(500)
			.withID(1)
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
			.withAttackDamage(100)
			.withX(10)
			.withY(11)
			.withWidth(12)
			.withHeight(13)
			.withID(1)
			.withSpriteType(SpriteType.ZOMBIE)
			.build();
	}
}
