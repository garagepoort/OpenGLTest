package be.davidcorp.loaderSaver.mapper.spriteToString;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.domain.test.builder.LightTestBuilder;

public class SpriteToStringMapperTest {

	@Test
	public void givenAnEnemy_whenMapSprite_correctStringIsReturned() {
		// given
		Enemy spider = aSpider();
		
		String result = new EnemyToStringMapper().buildStringFromSprite(spider);
		
		assertThat(result).contains("ENEMY");
		assertThat(result).contains("SPRITETYPE:SPIDER");
		assertThat(result).contains("ID:1");
		assertThat(result).contains("X:10");
		assertThat(result).contains("Y:12");
		assertThat(result).contains("WIDTH:30");
		assertThat(result).contains("HEIGHT:20");
	}
	
	@Test
	public void givenALight_whenMapSprite_correctStringIsReturned() {
		// given
		Light light = aLight();
		
		String result = new LightToStringMapper().buildStringFromSprite(light);
		
		assertThat(result).contains("LIGHT");
		assertThat(result).contains("SPRITETYPE:LIGHT");
		assertThat(result).contains("ID:1");
		assertThat(result).contains("X:10");
		assertThat(result).contains("Y:12");
		assertThat(result).contains("WIDTH:30");
		assertThat(result).contains("HEIGHT:20");
		assertThat(result).contains("RADIUS:55");
		assertThat(result).contains("LIGHTON:true");
	}

	private Enemy aSpider() {
		return new EnemyBuilder()
			.withID(1)
			.withX(10)
			.withY(12)
			.withWidth(30)
			.withHeight(20)
			.withSpriteType(SpriteType.SPIDER)
			.build();
	}
	
	private Light aLight() {
		return new LightTestBuilder()
		.withRadius(55)
		.withLightOn(true)
		.withX(10)
		.withY(12)
		.withWidth(30)
		.withHeight(20)
		.withID(1)
		.build();
	}

}
