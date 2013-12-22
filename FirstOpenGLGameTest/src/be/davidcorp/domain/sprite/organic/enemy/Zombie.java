package be.davidcorp.domain.sprite.organic.enemy;

import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.texture.TextureBunch;

public class Zombie extends Enemy {

	public Zombie(float x, float y, int width, int height){
		super(x, y, width, height);
		initializeTextureBunch();
		initializeVariables();
	}
	
	public Zombie(){
		this(0, 0, 32, 32);
	}

	public Zombie(float x, float y) {
		this(x, y, 32, 32);
	}

	private void initializeVariables() {
		setSpeed(0.05f);
		setMaxHealthPoints(30000);
		setViewRange(50);
		setDefaultViewRange(50);
		setViewRangeOffset(150);
		setAttackRange(30);
		setAttackDamage(500);
	}

	private void initializeTextureBunch() {
		TextureBunch bunch = new TextureBunch()
			.withDefaultTexture("resources/images/enemies/zombie/zombieStanding.png")
			.withTextureAtSecond("resources/images/enemies/zombie/zombieLeft.png", 1)
			.withTextureAtSecond("resources/images/enemies/zombie/zombieRight.png", 20)
			.withLastTextureTime(40);
		setTextureBunch(bunch);
	}

	public void updateTexture() {
		if (getTextureBunch() != null) {
			if (isMoving()) {
				getTextureBunch().startLoop();
			} else {
				getTextureBunch().stopLoop();
			}
			getTextureBunch().updateTexture();
		}
	}

	public SpriteType getType() {
		return SpriteType.ZOMBIE;
	}

	@Override
	public void onDeath() {
		super.onDeath();
		GameFieldManager.getCurrentGameField().addEnemyToWorld(new Spider(getCenter().x - getWidth() / 2, getCenter().y - getHeight() / 2));
	}
}
