package be.davidcorp;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtility {

	public String getFileContent(File file) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
		return Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();
	}
}
