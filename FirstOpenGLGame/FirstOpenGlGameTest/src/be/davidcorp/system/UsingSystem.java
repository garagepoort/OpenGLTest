package be.davidcorp.system;

import java.util.List;

import be.davidcorp.component.ComponentType;
import be.davidcorp.component.UsableComponent;
import be.davidcorp.component.UsingComponent;
import be.davidcorp.domain.sprite.Sprite;

import com.google.common.collect.Lists;

public class UsingSystem implements System {

	private List<Sprite> usableSprites = Lists.newArrayList();

	private static UsingSystem instance = new UsingSystem();

	private UsingSystem() {
	}

	public static UsingSystem getInstance() {
		return instance;
	}

	@Override
	public void executeSystem(Sprite usingSprite, float secondsMovedInGame) {
		UsingComponent usingComponent = usingSprite.getComponent(ComponentType.USING_COMPONENT);
		if (usingComponent != null && usingComponent.isUsing()) {
			for (Sprite usableSprite : usableSprites) {
				UsableComponent usableComponent = usableSprite.getComponent(ComponentType.USABLE_COMPONENT);
				if(isInUsableRange(usingSprite, usableSprite, usableComponent.getUsableRange())){
					usableComponent.getImplementation().onUse(usableSprite, usingSprite);
				}
			}
		}
	}

	private boolean isInUsableRange(Sprite sprite, Sprite sprite2, int usableRange) {
		float x = sprite.getCenter().x;
		float y = sprite.getCenter().y;
		float x2 = sprite2.getCenter().x;
		float y2 = sprite2.getCenter().y;
		if (x < x2 + usableRange && x > x2 - usableRange && y < y2 + usableRange && y > y2 - usableRange) {
			return true;
		}
		return false;
	}

	public void addUsableSprite(Sprite sprite){
		if(sprite.getComponent(ComponentType.USABLE_COMPONENT) == null){
			throw new RuntimeException("Sprite has to have a UsableComponent");
		}
		usableSprites.add(sprite);
	}
	
	public void removeUsableSprite(Sprite sprite){
		usableSprites.remove(sprite);
	}
	
	public void clearUsableSprites(){
		usableSprites.clear();
	}
}
