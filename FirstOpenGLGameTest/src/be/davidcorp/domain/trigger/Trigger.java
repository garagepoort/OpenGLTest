package be.davidcorp.domain.trigger;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import be.davidcorp.domain.sprite.Sprite;
import be.davidcorp.domain.utilities.sprite.SpriteCollisionChecker;

public class Trigger {

	private int ID;
	private List<Triggerable> triggerables = newArrayList();
	private int triggerRadiusWidth = 100;
	private int triggerRadiusHeight = 100;
	public TriggerWhen triggerWhen = TriggerWhen.ONUSE;
	private float x = 0;
	private float y = 0;

	public Trigger(TriggerWhen triggerWhen) {
		this.triggerWhen = triggerWhen;
	}
	public void trigger(Sprite sprite) {
		if (this.triggerWhen == TriggerWhen.ONUSE) {
			if (insideTriggerRange(sprite)) {
				handleTriggerEventFromAllTriggerables();
			}
		} else {
			handleTriggerEventFromAllTriggerables();
		}
	}

	private void handleTriggerEventFromAllTriggerables() {
		for (Triggerable triggerable : triggerables) {
			triggerable.handleTriggerEvent(this);
		}
	}

	public boolean insideTriggerRange(Sprite s) {
		float x2 = getX() - triggerRadiusWidth;
		float y2 = getY() - triggerRadiusHeight;
		Sprite collisionSprite = null;
		collisionSprite = new Sprite(x2, y2, 2 * triggerRadiusWidth, 2 * triggerRadiusHeight) {
			@Override
			public void onDeath() {
			}
		};

		return SpriteCollisionChecker.doesCollisionExist(collisionSprite, s);
	}

	public void setTriggerRadius(int width, int height) {
		this.triggerRadiusWidth = width;
		this.triggerRadiusHeight = height;
	}

	public int getTriggerRadiusWidth() {
		return triggerRadiusWidth;
	}

	public void setTriggerRadiusWidth(int triggerRadiusWidth) {
		this.triggerRadiusWidth = triggerRadiusWidth;
	}

	public int getTriggerRadiusHeight() {
		return triggerRadiusHeight;
	}

	public void setTriggerRadiusHeight(int triggerRadiusHeight) {
		this.triggerRadiusHeight = triggerRadiusHeight;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Trigger: " + triggerWhen.toString() + " triggers " + triggerables.size() + " triggerables";
	}

	public void addTriggerable(Triggerable triggerable) {
		triggerables.add(triggerable);
	}
	public List<Triggerable> getTriggerables() {
		return triggerables;
	}

}
