package be.davidcorp.database.filehandling;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import be.davidcorp.database.GamefieldProperty;


public class SpriteFileLoaderUtilitiesTest {

	private File file;
	
	@Before
	public void before() throws FileNotFoundException{
		file = new File("test.txt");
		PrintWriter writer = new PrintWriter(file);
		writer.write("GAMEFIELDNAME:name\n"
				+ "ID:1\n"
				+ "WIDTH:800\n"
				+ "HEIGHT:1600");
		writer.close();
	}
	
	@Test
	public void givenAGamefieldString_whenGetGamefieldProperties_thenPropertiesMappedInMapFromString() throws Exception{
		Map<GamefieldProperty, String> gamefieldProperties = SpriteFileLoaderUtilities.getGamefieldProperties(new FileInputStream(file));
		
		assertThat(gamefieldProperties.get(GamefieldProperty.GAMEFIELDNAME)).isEqualTo("name");
		assertThat(gamefieldProperties.get(GamefieldProperty.ID)).isEqualTo("1");
		assertThat(gamefieldProperties.get(GamefieldProperty.WIDTH)).isEqualTo("800");
		assertThat(gamefieldProperties.get(GamefieldProperty.HEIGHT)).isEqualTo("1600");
	}
}
