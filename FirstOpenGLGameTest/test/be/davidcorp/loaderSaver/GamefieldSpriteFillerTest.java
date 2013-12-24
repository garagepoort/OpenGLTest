package be.davidcorp.loaderSaver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.organic.enemy.Spider;
import be.davidcorp.domain.sprite.organic.enemy.Zombie;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;
import be.davidcorp.loaderSaver.repository.GamefieldRepository;

public class GamefieldSpriteFillerTest {
	
	private GamefieldSpriteFiller gamefieldSpriteFiller;
	
	@Mock private DefaultSpriteRepository defaultSpriteRepository;
	@Mock private GamefieldRepository gamefieldRepository;

	@Before
	public void initialize(){
		MockitoAnnotations.initMocks(this);
		gamefieldSpriteFiller = new GamefieldSpriteFiller();
		gamefieldSpriteFiller.setDefaultSpriteRepository(defaultSpriteRepository);
		gamefieldSpriteFiller.setGamefieldRepository(gamefieldRepository);
	}
	
	@Test
	public void givenAGamefieldLink_whenGamefieldFilledIn_thenGamefieldIsFilledInCorrectly() {
		//given
		Zombie zombie= mock(Zombie.class);
		Spider spider= mock(Spider.class);
		Pistol pistol = mock(Pistol.class);
		
		when(zombie.getID()).thenReturn(1);
		when(spider.getID()).thenReturn(2);
		when(pistol.getID()).thenReturn(3);
		
		Gamefield gamefield = new Gamefield("testfield", 100, 100);

		when(defaultSpriteRepository.getSprite(1)).thenReturn(zombie);
		when(defaultSpriteRepository.getSprite(2)).thenReturn(spider);
		when(defaultSpriteRepository.getSprite(3)).thenReturn(pistol);
		when(gamefieldRepository.getGamefield(1)).thenReturn(gamefield);

		String links =
				"GAMEFIELD:1\n"
				+ "SPRITE:1\n"
				+ "SPRITE:2\n"
				+ "SPRITE:3";
		
		//when
		gamefieldSpriteFiller.fillGamefields(links);
		gamefield.addSpritesToWorld();

		//then
		assertThat(gamefield.getEnemiesInWorld()).containsOnly(zombie, spider);
		assertThat(gamefield.getGroundItems()).containsOnly(pistol);
	}

}