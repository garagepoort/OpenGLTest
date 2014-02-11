package be.davidcorp.database;

import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.davidcorp.FileUtility;
import be.davidcorp.database.GamefieldLoaderSaver;
import be.davidcorp.database.repository.GamefieldRepository;

public class GamefieldLoaderTest {

	private GamefieldLoaderSaver loader;
	private FileUtility fileUtility;
	private GamefieldRepository gamefieldRepository = new GamefieldRepository();
	
	@Before
	public void init(){
		loader = new GamefieldLoaderSaver();
		fileUtility = Mockito.mock(FileUtility.class);
		loader.setFileUtility(fileUtility);
	}
	
	@Test
	public void givenAGamefieldFileWithOneGamefield_whenFileLoaded_thenTheCorrectGamefieldIsLoadedInsideTheLoader() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
						"GAMEFIELDNAME:fieldname\n"
						+ "ID:1\n"
						+ "WIDTH:12\n"
						+ "HEIGHT:13\n"
						+ "END");
		loader.loadAllGamefieldsToRepository(Mockito.any(File.class));
		Assertions.assertThat(gamefieldRepository.getGamefield(1)).isNotNull();
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getName()).isEqualTo("fieldname");
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getWidth()).isEqualTo(12);
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getHeight()).isEqualTo(13);
	}
	
	@Test
	public void givenAGamefieldFileWithTwoGamefields_whenFileLoaded_thenTheCorrectGamefieldsAreLoadedInsideTheLoader() throws IOException{
		when(fileUtility.getFileContent(Mockito.any(File.class))).thenReturn(
				"GAMEFIELDNAME:fieldname\n"
						+ "ID:1\n"
						+ "WIDTH:12\n"
						+ "HEIGHT:13\n"
						+ "END\n"
						+"GAMEFIELDNAME:fieldname2\n"
						+ "ID:2\n"
						+ "WIDTH:12\n"
						+ "HEIGHT:13\n"
						+ "END");
		loader.loadAllGamefieldsToRepository(Mockito.any(File.class));
		Assertions.assertThat(gamefieldRepository.getGamefield(1)).isNotNull();
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getName()).isEqualTo("fieldname");
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getWidth()).isEqualTo(12);
		Assertions.assertThat(gamefieldRepository.getGamefield(1).getHeight()).isEqualTo(13);
		
		Assertions.assertThat(gamefieldRepository.getGamefield(2)).isNotNull();
		Assertions.assertThat(gamefieldRepository.getGamefield(2).getName()).isEqualTo("fieldname2");
		Assertions.assertThat(gamefieldRepository.getGamefield(2).getWidth()).isEqualTo(12);
		Assertions.assertThat(gamefieldRepository.getGamefield(2).getHeight()).isEqualTo(13);
	}
}
