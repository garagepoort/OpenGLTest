package be.davidcorp.domain.mission.acceptionCriteria;


public class FindHealthCriteria implements AcceptationCriteria{

	@Override
	public boolean isCriteriaOK() {
//		ArrayList<Item[]> items = Lists.newArrayList(getCurrentPlayer().getInventory());
//		int healtcounter = 0;
//		for (Item[] items2 : items) {
//			ArrayList<Item> itemss = Lists.newArrayList(items2);
//			for (Item item : itemss) {
//				if(item.getType() == SpriteType.HEALTHPOTION) healtcounter++;
//			}
//		}
		return false;
//		return healtcounter>=4;
	}

}
