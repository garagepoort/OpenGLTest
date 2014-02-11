package be.davidcorp.applicationLayer.dto.mapper.spriteToDTO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import be.davidcorp.applicationLayer.dto.light.LightDTO;
import be.davidcorp.domain.sprite.HitBox;
import be.davidcorp.domain.sprite.light.Light;
import be.davidcorp.metric.Point;

public class LightToLightDTOMapperTest {

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
	
	private static final int RADIUS = 15;
	private static final boolean LIGHTON = true;
	

	@Test
	public void givenALight_whenMapToLightDTO_thenMappedLightDTO() {
		Light light = mockLight();
		new LightToLightDTOMapper();
		LightDTO dto = LightToLightDTOMapper.mapLightToDTO(light);

		assertEquals(ID, dto.getId());

		assertEquals(X, dto.getX(), 0);
		assertEquals(Y, dto.getY(), 0);
		assertEquals(WIDTH, dto.getWidth());
		assertEquals(HEIGHT, dto.getHeight());

		assertEquals(DOWNLEFTPOINT, dto.getDownLeftPoint());
		assertEquals(DOWNRIGHTPOINT, dto.getDownRightPoint());
		assertEquals(UPPERLEFTPOINT, dto.getUpperLeftPoint());
		assertEquals(UPPERRIGHTPOINT, dto.getUpperRightPoint());
		
		assertEquals(RADIUS, dto.getRadius());
		assertEquals(LIGHTON, dto.isLightOn());
	}

	private Light mockLight() {
		HitBox hitBox = mock(HitBox.class);
		when(hitBox.getDownLeftPoint()).thenReturn(DOWNLEFTPOINT);
		when(hitBox.getUpperLeftPoint()).thenReturn(UPPERLEFTPOINT);
		when(hitBox.getDownRightPoint()).thenReturn(DOWNRIGHTPOINT);
		when(hitBox.getUpperRightPoint()).thenReturn(UPPERRIGHTPOINT);

		Light light = mock(Light.class);
		when(light.getID()).thenReturn(ID);
		when(light.isAlive()).thenReturn(ALIVE);

		when(light.getX()).thenReturn(X);
		when(light.getY()).thenReturn(Y);
		when(light.getWidth()).thenReturn(WIDTH);
		when(light.getHeight()).thenReturn(HEIGHT);

		when(light.getRadius()).thenReturn(RADIUS);
		when(light.isLightOn()).thenReturn(LIGHTON);

		when(light.getHitBox()).thenReturn(hitBox);
		return light;

	}
}
