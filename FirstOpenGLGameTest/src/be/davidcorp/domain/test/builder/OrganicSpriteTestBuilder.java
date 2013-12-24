package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.OrganicSprite;

public abstract class OrganicSpriteTestBuilder<ORGANICSPRITE extends OrganicSprite> extends SpriteTestBuilder<ORGANICSPRITE>{

	private int stamina= 200;

	@Override
	protected ORGANICSPRITE buildBasic() {
		ORGANICSPRITE organicsprite = super.buildBasic();
		organicsprite.setMaxStamina(stamina);
		return organicsprite;
	}
	
	public OrganicSpriteTestBuilder<ORGANICSPRITE> withMaxStamina(int stamina){
		this.stamina = stamina;
		return this;
	}
}
