package be.davidcorp.domain.sprite.item;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import be.davidcorp.domain.attribute.Attribute;
import be.davidcorp.domain.test.builder.SpriteBuilder;

public abstract class AbstractItemBuilder<ITEM extends Item> extends SpriteBuilder<ITEM>{


		private UsableImplementation usableImplementation;
		private List<Attribute> attributes = newArrayList();
		private String infoTekst;
		
		@Override
		protected ITEM buildBasic() {
			ITEM item = super.buildBasic();
			item.setUsableImplementation(usableImplementation);
			item.setInfoText(infoTekst);
			for(Attribute attribute : attributes){
				item.addAttribute(attribute);
			}
			return item;
		}
		
		public AbstractItemBuilder<ITEM> withUsableImplementation(UsableImplementation usableImplementation){
			this.usableImplementation = usableImplementation;
			return this;
		}
		
		public AbstractItemBuilder<ITEM> withAnotherAttribute(Attribute attribute){
			attributes.add(attribute);
			return this;
		}
		
		public AbstractItemBuilder<ITEM> withInfoTekst(String info){
			this.infoTekst = info;
			return this;
		}
}
