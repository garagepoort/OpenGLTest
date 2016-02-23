package be.davidcorp.view.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;

import be.davidcorp.applicationLayer.facade.GameStarterFacade;

public class GameStarter {

	public static void main(String[] args) throws LWJGLException, IOException {
		String[] natives = new String[] {
				"glfw.dll",
				"glfw32.dll",
				"jemalloc.dll",
				"jemalloc32.dll",
				"libglfw.dylib",
				"libglfw.so",
				"libglfw32.so",
				"libjemalloc.dylib",
				"libjemalloc.so",
				"libjemalloc32.so",
				"liblwjgl.dylib",
				"liblwjgl.so",
				"liblwjgl32.so",
				"lwjgl.dll",
				"lwjgl32.dll"
		};
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));
		byte[] buff = new byte[2048];
		for(String file: natives) {
			InputStream is = GameStarter.class.getResourceAsStream(file);
			File temp = new File(tmpDir, file);
			try(FileOutputStream os = new FileOutputStream(temp)) {
				int read;
				while((read = is.read(buff)) != -1) {
					os.write(buff, 0, read);
				}
			}
		}
		System.setProperty("org.lwjgl.librarypath", tmpDir.getAbsolutePath());
		try {
			new GameStarterFacade().startApplication();
			new GameLoop(new PlayGamePanel(), 800, 600).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}