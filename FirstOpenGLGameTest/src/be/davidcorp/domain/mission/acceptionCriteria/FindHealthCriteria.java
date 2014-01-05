package be.davidcorp.domain.mission.acceptionCriteria;

import static be.davidcorp.domain.sprite.organic.player.PlayerManager.getCurrentPlayer;

import java.util.ArrayList;

import be.davidcorp.domain.sprite.SpriteType;
import be.davidcorp.domain.sprite.item.Item;

import com.google.common.collect.Lists;

public class FindHealthCriteria implements AcceptationCriteria{

	@Override
	public boolean isCriteriaOK() {
		ArrayList<Item[]> items = Lists.newArrayList(getCurrentPlayer().getInventory().getItems());
		int healtcounter = 0;
		for (Item[] items2 : items) {
			ArrayList<Item> itemss = Lists.newArrayList(items2);
			for (Item item : itemss) {
				if(item.getType() == SpriteType.HEALTHPOTION) healtcounter++;
			}
		}
		return healtcounter>=4;
	}

}
