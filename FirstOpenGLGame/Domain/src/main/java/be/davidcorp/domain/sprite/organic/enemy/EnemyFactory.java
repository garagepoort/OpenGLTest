package be.davidcorp.domain.sprite.organic.enemy;

import static be.davidcorp.domain.sprite.SpriteType.SPIDER;
import static be.davidcorp.domain.sprite.SpriteType.ZOMBIE;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.test.builder.EnemyBuilder;

public class EnemyFactory {

	public static Enemy createZombie(float x, float y){
		Enemy enemy = new EnemyBuilder()
			.withAttackDamage(5000)
			.withAttackRange(30)
			.withViewRange(50)
			.withViewRangeOffset(150)
			.withX(x)
			.withY(y)
			.withSpeed(0.05f)
			.withMaxHealth(30000)
			.withWidth(32)
			.withHeight(32)
			.withSpriteType(ZOMBIE)
			.withInWorld(true)
			.build();
//		enemy.setTextureBunch(new TextureBunch().withDefaultTexture(getImageLocation(ZOMBIE)));
		return enemy;
	}
	
	public static Enemy createSpider(float x, float y){
		Enemy enemy = new EnemyBuilder()
		.withAttackDamage(500)
		.withAttackRange(75)
		.withViewRange(500)
		.withViewRangeOffset(150)
		.withX(x)
		.withY(y)
		.withSpeed(0.30f)
		.withMaxHealth(5000)
		.withWidth(32)
		.withHeight(32)
		.withInWorld(true)
		.withSpriteType(SPIDER)
		.build();
//		enemy.setTextureBunch(new TextureBunch().withDefaultTexture(ImageLocationManager.getImageLocation(SPIDER)));
		return enemy;
	}
	
	public static Enemy createFromType(float x, float y, SpriteType spriteType){
		if(spriteType == SPIDER) return createSpider(x, y);
		if(spriteType == ZOMBIE) return createZombie(x, y);
		throw new RuntimeException("No enemy for type: " + spriteType);
	}
}
