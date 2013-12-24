package be.davidcorp.loaderSaver;

import org.junit.Before;
import org.junit.Test;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.construction.Wall;
import be.davidcorp.domain.sprite.item.potion.HealthPotion;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.GamefieldTestBuilder;
import be.davidcorp.domain.test.builder.HealthPotionTestBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.test.builder.WallTestBuilder;
import be.davidcorp.domain.test.builder.ZombieTestBuilder;


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
	
		gamefieldLoaderSaver.saveEntireField(gamefield);
		
		
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
	
	private HealthPotion aHealthPotionWithID(int id) {
		return new HealthPotionTestBuilder()
		.withID(id)
		.build();
	}

	private Enemy aZombieWithID(int id) {
		return new ZombieTestBuilder()
				.withID(id)
				.build();
	}
	
	private Wall aWallWithID(int id) {
		return new WallTestBuilder()
		.withID(id)
		.build();
	}
}
