package be.davidcorp.domain.sprite.organic.enemy;

import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.texture.TextureBunch;

public class Spider extends Enemy {

	public Spider() {
		this(0, 0, 32, 32);
	};
	
	public Spider(float x, float y, int width, int height){
		super(x, y, width, height);
		initializeTexture();
		initializeSpider();
	}

	public Spider(float x, float y) {
		this(x, y, 32, 32);
	}
	
	private void initializeTexture() {
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/enemies/spider.png"));;
	}

	private void initializeSpider() {
		setSpeed(0.30f);
		setMaxHealthPoints(5000);
		setViewRange(500);
		setDefaultViewRange(500);
		setAttackRange(75);
		setAttackDamage(500);
	}

	public SpriteType getType() {
		return SpriteType.SPIDER;
	}

	@Override
	public void onDeath() {
		super.onDeath();
		// try {
		// gameField.getGroundItemsToAdd().add(new
		// HealthPotion(getCenter().x-getWidth()/2,
		// getCenter().y-getHeight()/2));
		// gameField.getGroundItemsToAdd().add(new
		// Pistol(getCenter().x-getWidth()/2,
		// getCenter().y-getHeight()/2,100,null));
		// gameField.getGroundItemsToAdd().add(new
		// StaminaPotion(getCenter().x-getWidth()/2,
		// getCenter().y-getHeight()/2));
		//
		// } catch (SpriteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
