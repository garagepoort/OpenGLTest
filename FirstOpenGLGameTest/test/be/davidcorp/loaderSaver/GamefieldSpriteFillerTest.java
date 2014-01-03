package be.davidcorp.loaderSaver;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import be.davidcorp.FileUtility;
import be.davidcorp.domain.game.Gamefield;
import be.davidcorp.domain.sprite.item.weapon.Pistol;
import be.davidcorp.domain.sprite.organic.enemy.Enemy;
import be.davidcorp.domain.test.builder.EnemyBuilder;
import be.davidcorp.domain.test.builder.GamefieldTestBuilder;
import be.davidcorp.domain.test.builder.PistolTestBuilder;
import be.davidcorp.loaderSaver.repository.DefaultSpriteRepository;
import be.davidcorp.loaderSaver.repository.GamefieldRepository;

public class GamefieldSpriteFillerTest {
	
	private GamefieldSpriteFiller gamefieldSpriteFiller;
	
	@Mock private DefaultSpriteRepository defaultSpriteRepository;
	@Mock private GamefieldRepository gamefieldRepository;
	@Mock private FileUtility fileUtility;
	@Mock private File file;
	
	@Before
	public void initialize(){
		MockitoAnnotations.initMocks(this);
		gamefieldSpriteFiller = new GamefieldSpriteFiller(file);
		gamefieldSpriteFiller.setDefaultSpriteRepository(defaultSpriteRepository);
		gamefieldSpriteFiller.setGamefieldRepository(gamefieldRepository);
		gamefieldSpriteFiller.setFileUtility(fileUtility);
	}
	
	@Test
	public void givenAGamefieldLink_whenGamefieldFilledIn_thenGamefieldIsFilledInCorrectly() throws IOException {
		//given
		Enemy zombie= new EnemyBuilder().withID(1).build();
		Enemy spider= new EnemyBuilder().withID(2).build();
		Pistol pistol = new PistolTestBuilder().withID(3).build();
		
		
		Gamefield gamefield = new GamefieldTestBuilder().withID(1).build();

		when(defaultSpriteRepository.getSprite(1)).thenReturn(zombie);
		when(defaultSpriteRepository.getSprite(2)).thenReturn(spider);
		when(defaultSpriteRepository.getSprite(3)).thenReturn(pistol);
		when(gamefieldRepository.getGamefield(1)).thenReturn(gamefield);

		String links =
				"GAMEFIELD:1\n"
				+ "SPRITE:1\n"
				+ "SPRITE:2\n"
				+ "SPRITE:3";
		
		when(fileUtility.getFileContent(file)).thenReturn(links);
		//when
		gamefieldSpriteFiller.fillGamefields();

		//then
		assertThat(gamefield.getEnemiesInWorld()).containsOnly(zombie, spider);
		assertThat(gamefield.getGroundItems()).containsOnly(pistol);
	}

}