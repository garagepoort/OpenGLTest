package be.davidcorp.database;

import java.io.File;

import org.junit.After;
import org.junit.Before;


public class GamefieldSpriteLinkSaverTest {

	private File file;
	
	@Before
	public void before(){
		file = new File("resources/test/testfile.txt");
	}
	
	@After
	public void breakDown(){
		file.delete();
	}
	
	
}
