package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.organic.OrganicSprite;

public abstract class OrganicSpriteBuilder<ORGANICSPRITE extends OrganicSprite> extends SpriteBuilder<ORGANICSPRITE>{

	private int stamina= 200;

	@Override
	protected ORGANICSPRITE buildBasic() {
		ORGANICSPRITE organicsprite = super.buildBasic();
		organicsprite.setMaxStamina(stamina);
		return organicsprite;
	}
	
	public OrganicSpriteBuilder<ORGANICSPRITE> withMaxStamina(int stamina){
		this.stamina = stamina;
		return this;
	}
}
