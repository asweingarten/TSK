import static org.junit.Assert.*;

import java.io.IOException;

import model.*;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FullFileReadTests {

	private static final String filename = "./hello.txt";
	private static final String message = "Hello World\nHow's it going?";
	
	private void set_up_test() throws IOException {
		Files.write(Paths.get(filename), StandardCharsets.UTF_8.encode(message).array(), StandardOpenOption.CREATE);
	}
	
	@Test
	public void sample_test() {
		try {
			set_up_test();
			TextFileReader reader = new TextFileReader(new FullFileReadStrategy());
			assertEquals(message, reader.ReadFile(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
