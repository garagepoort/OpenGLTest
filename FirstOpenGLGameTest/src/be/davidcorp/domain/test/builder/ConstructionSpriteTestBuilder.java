package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.construction.ConstructionSprite;

public abstract class ConstructionSpriteTestBuilder<CONSTRUCTIONSPRITE extends ConstructionSprite> extends SpriteTestBuilder<CONSTRUCTIONSPRITE>{
	private int usableRange;

	@Override
	protected CONSTRUCTIONSPRITE buildBasic() {
		CONSTRUCTIONSPRITE sprite = super.buildBasic();
		sprite.setUsableRange(usableRange);
		return sprite;
	}

	public ConstructionSpriteTestBuilder<CONSTRUCTIONSPRITE> withUsableRange(int usableRange){
		this.usableRange = usableRange;
		return this;
	}
	
}
