package be.davidcorp.database;

import org.junit.Before;
import org.junit.Test;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.construction.ConstructionSprite;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.domain.sprite.item.ItemFactory;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.ConstructionSpriteBuilder;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.domain.test.builder.GamefieldTestBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;


public class GamefieldLoaderSaverTest {

	private GamefieldLoaderSaver gamefieldLoaderSaver;
	
	@Before
	public void init(){
		gamefieldLoaderSaver = new GamefieldLoaderSaver();
	}
	
	@Test
	public void givenGamefieldWithAllKindOfSprites_whenSaveGamefield_thenGamefieldIsCorrectlySaved(){
		//given
		Gamefield gamefield = aGamefieldFullOfSprites();
	
//		gamefieldLoaderSaver.saveEntireField(gamefield);
	}

	private Gamefield aGamefieldFullOfSprites() {
		return new GamefieldTestBuilder()
			.withEnemy(aZombieWithID(1))
			.withConstructionSprite(aWallWithID(2))
			.withLight(aLightWithID(3))
			.withGroundItem(aHealthPotionWithID(4))
			.build();
	}

	private Light aLightWithID(int id) {
		return new LightTestBuilder()
			.withID(id)
			.build();
	}
	
	private Item aHealthPotionWithID(int id) {
		Item createHealthPotion = ItemFactory.createHealthPotion(10, 10, 100);
		createHealthPotion.setID(id);
		return createHealthPotion;
	}

	private Enemy aZombieWithID(int id) {
		return new EnemyBuilder()
				.withID(id)
				.build();
	}
	
	private ConstructionSprite aWallWithID(int id) {
		return new ConstructionSpriteBuilder()
		.withID(id)
		.build();
	}
}
