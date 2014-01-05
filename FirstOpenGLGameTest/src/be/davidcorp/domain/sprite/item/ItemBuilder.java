package be.davidcorp.domain.sprite.item;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import be.davidcorp.domain.attribute.Attribute;
import be.davidcorp.domain.sprite.item.Item.UsableImplementation;
import be.davidcorp.domain.test.builder.SpriteBuilder;

public class ItemBuilder extends SpriteBuilder<Item>{

	private UsableImplementation usableImplementation;
	private List<Attribute> attributes = newArrayList();
	private String infoTekst;
	
	@Override
	protected Item buildBasic() {
		Item item = super.buildBasic();
		item.setUsableImplementation(usableImplementation);
		for(Attribute attribute : attributes){
			item.addAttribute(attribute);
		}
		return item;
	}
	
	public ItemBuilder withUsableImplementation(UsableImplementation usableImplementation){
		this.usableImplementation = usableImplementation;
		return this;
	}
	
	public ItemBuilder withAnotherAttribute(Attribute attribute){
		attributes.add(attribute);
		return this;
	}
	
	public ItemBuilder withInfoTekst(String info){
		this.infoTekst = info;
		return this;
	}
	
	@Override
	protected Item createInstance() {
		return new Item();
	}

}
