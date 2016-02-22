package be.davidcorp.domain.test.builder;

import be.davidcorp.domain.sprite.item.Item;

public abstract class ItemTestBuilder<ITEM extends Item> extends SpriteBuilder<ITEM>{
	
	private int weight;
	private String infoText;

	@Override
	protected ITEM buildBasic() {
		ITEM item = super.buildBasic();
		item.setWeight(weight);
		item.setInfoText(infoText);
		return item;
	}
	
	public ItemTestBuilder<ITEM> withWeight(int weight){
		this.weight = weight;
		return this;
	}
	
	public ItemTestBuilder<ITEM> withInfoText(String infoText){
		this.infoText = infoText;
		return this;
	}
}
