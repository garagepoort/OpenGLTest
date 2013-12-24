package be.davidcorp.domain.sprite.organic.enemy;

import be.davidcorp.domain.game.GameFieldManager;
import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.organic.OrganicSprite;
import be.davidcorp.domain.sprite.organic.player.PlayerManager;
import be.davidcorp.domain.utilities.Cooldowner;
import be.davidcorp.domain.utilities.sprite.SpriteMovingUtility;
import be.davidcorp.texture.TextureBunch;

public class Enemy extends OrganicSprite {
	private int attackDamage = 10;
	private int range = 100;
	private double defaultViewRange = 300;
	private double viewRange = 300;
	private double viewRangeOffset = 0;
	private Cooldowner cooldowner = new Cooldowner(200);

	public Enemy() {
		super();
	}

	public Enemy(float x, float y, int width, int height) {
		super(x, y, width, height);
		initialize();

	}

	private void initialize() {
		setTextureBunch(new TextureBunch().withDefaultTexture("resources/images/droid.png"));
		setSpeed(0.10f);
	}

	@Override
	public SpriteType getType() {
		return SpriteType.ENEMY;
	}

	@Override
	public void updateSprite(int secondsMovedInGame) {
		alterViewRange(PlayerManager.getCurrentPlayer().flashLight.isLightOn());

		float prevx = getX();
		float prevy = getY();
		if (spriteInViewRange(PlayerManager.getCurrentPlayer())) {
			SpriteMovingUtility.turnSpriteTowardsPoint(this, PlayerManager.getCurrentPlayer().getCenter());
			if (inAttackRange(PlayerManager.getCurrentPlayer())) {
				attack(PlayerManager.getCurrentPlayer());
			} else {
				SpriteMovingUtility.moveSpriteOnHisDirectionVector(this, getSpeed() * secondsMovedInGame);
				if (GameFieldManager.getCurrentGameField().doesEnemyCollideWithAnyConstructionItem(this)) {
					setX(prevx);
					setY(prevy);
				}
			}
		}

		super.updateSprite(secondsMovedInGame);
	}

	public int getAttackDamage() {
		return attackDamage;
	}
	
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	protected void setAttackRange(int range) {
		this.range = range;
	}

	protected void setViewRange(double viewRange) {
		this.viewRange = viewRange;
	}

	protected void setDefaultViewRange(double defaultViewRange) {
		this.defaultViewRange = defaultViewRange;
	}

	protected void setViewRangeOffset(double viewRangeOffset) {
		this.viewRangeOffset = viewRangeOffset;
	}

	private void alterViewRange(boolean lightOn) {
		if (lightOn) {
			setViewRange(defaultViewRange + viewRangeOffset);
		} else {
			setViewRange(defaultViewRange);
		}
	}

	private boolean inAttackRange(Sprite s) {
		float x = s.getCenter().x;
		float y = s.getCenter().y;
		float x2 = getCenter().x;
		float y2 = getCenter().y;
		if (x < x2 + range && x > x2 - range && y < y2 + range && y > y2 - range) {
			return true;
		}
		return false;
	}

	private boolean spriteInViewRange(Sprite s) {
		if (viewRange == -1)
			return true;
		float x = s.getCenter().x;
		float y = s.getCenter().y;
		float x2 = getCenter().x;
		float y2 = getCenter().y;
		if (x < x2 + viewRange && x > x2 - viewRange && y < y2 + viewRange && y > y2 - viewRange) {
			return true;
		}
		return false;
	}

	private void attack(OrganicSprite organicSprite) {
		if (cooldowner.isCoolDowned()) {
			organicSprite.removeHealth(attackDamage);
			cooldowner.resetCooldownProgress();
		} else {
			cooldowner.progressCooldown();
		}
	}
}
