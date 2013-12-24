package be.davidcorp.loaderSaver.mapper.spriteToString;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.test.builder.LightTestBuilder;
import be.davidcorp.domain.test.builder.SpiderTestBuilder;

public class SpriteToStringMapperTest {

	@Test
	public void givenAnEnemy_whenMapSprite_correctStringIsReturned() {
		// given
		Spider spider = aSpider();
		
		String result = new EnemyToStringMapper<Spider>().buildStringFromSprite(spider);
		
		assertThat(result).contains("ENEMY");
		assertThat(result).contains("SPRITETYPE:SPIDER");
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
		assertThat(result).contains("X:10");
		assertThat(result).contains("Y:12");
		assertThat(result).contains("WIDTH:30");
		assertThat(result).contains("HEIGHT:20");
		assertThat(result).contains("RADIUS:55");
		assertThat(result).contains("LIGHTON:true");
	}

	private Spider aSpider() {
		return new SpiderTestBuilder()
			.withX(10)
			.withY(12)
			.withWidth(30)
			.withHeight(20)
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
		.build();
	}

}
