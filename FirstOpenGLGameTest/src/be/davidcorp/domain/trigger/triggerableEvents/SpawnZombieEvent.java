package be.davidcorp.domain.trigger.triggerableEvents;

import static be.davidcorp.domain.trigger.triggerableEvents.TriggerableEventType.SPAWN_ZOMBIE;
import be.davidcorp.domain.sprite.SpriteSpawner;
import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.trigger.Trigger;

public class SpawnZombieEvent implements TriggerableEvent<SpriteSpawner>{

	public TriggerableEventType getType() {
		return SPAWN_ZOMBIE;
	}

	@Override
	public void doTriggerEvent(Trigger trigger, SpriteSpawner sprite) {
		sprite.spawnSprite(SpriteType.ZOMBIE);
	}
}
