package be.davidcorp.domain.sprite.organic.enemy;

import java.io.IOException;

import be.davidcorp.domain.exception.SpriteException;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.texture.TextureBunch;

public class Spider extends Enemy {

	public Spider() throws SpriteException, IOException{
		super();
	};
	
	public Spider(float x, float y, int width, int height)
			throws SpriteException, IOException {
		super(x, y, width, height);
		initializeTexture();
		initializeSpider();
	}

	public Spider(float x, float y) throws SpriteException, IOException {
		super(x, y, 32, 32);
		initializeTexture();
		initializeSpider();
	}

	private void initializeTexture() throws SpriteException {
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