package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.davidcorp.applicationLayer.dto.ItemDTO;
import be.davidcorp.applicationLayer.exception.MapperException;
import be.davidcorp.domain.sprite.HitBox;
import be.davidcorp.domain.sprite.item.Item;
import be.davidcorp.metric.Point;

public class ItemToItemDTOMapperTest {

	
	private static final int ID = 1;
	private static final boolean ALIVE = true;

	private static final float X = 10f;
	private static final float Y = 11f;
	private static final int WIDTH = 12;
	private static final int HEIGHT = 13;
	
	private static final Point DOWNLEFTPOINT = new Point(1, 2, 3);
	private static final Point UPPERLEFTPOINT = new Point(1, 2, 3);
	private static final Point DOWNRIGHTPOINT = new Point(1, 2, 3);
	private static final Point UPPERRIGHTPOINT = new Point(1, 2, 3);

	private static final String INFOTEKST = "test";

	@Test
	public void givenASprite_whenMapToSpriteDTO_thenMappedSpriteDTO() throws MapperException {
		Item item = mockItem();
		ItemDTO dto = ItemDTOMapper.doAutoMappingForItem(item);
		
		assertEquals(ID, dto.getId());
		
		assertEquals(X, dto.getX(), 0);
		assertEquals(Y, dto.getY(), 0);
		assertEquals(WIDTH, dto.getWidth());
		assertEquals(HEIGHT, dto.getHeight());
		
		assertEquals(DOWNLEFTPOINT, dto.getDownLeftPoint());
		assertEquals(DOWNRIGHTPOINT, dto.getDownRightPoint());
		assertEquals(UPPERLEFTPOINT, dto.getUpperLeftPoint());
		assertEquals(UPPERRIGHTPOINT, dto.getUpperRightPoint());
		
		assertEquals(INFOTEKST, dto.getInfoTekst());
	}

	private Item mockItem() {
		HitBox hitBox = mock(HitBox.class);
		when(hitBox.getDownLeftPoint()).thenReturn(DOWNLEFTPOINT);
		when(hitBox.getUpperLeftPoint()).thenReturn(UPPERLEFTPOINT);
		when(hitBox.getDownRightPoint()).thenReturn(DOWNRIGHTPOINT);
		when(hitBox.getUpperRightPoint()).thenReturn(UPPERRIGHTPOINT);
		
		Item item = mock(Item.class);
		when(item.getHitBox()).thenReturn(hitBox);
		
		when(item.getID()).thenReturn(ID);
		when(item.isAlive()).thenReturn(ALIVE);

		when(item.getX()).thenReturn(X);
		when(item.getY()).thenReturn(Y);
		when(item.getWidth()).thenReturn(WIDTH);
		when(item.getHeight()).thenReturn(HEIGHT);


		when(item.getInfoText()).thenReturn(INFOTEKST);
		return item;

	}
	
}
