package main;

import java.io.IOException;
import model.*;

public class Main {

	public static void main(String[] args) {
		TextFileReader reader = new TextFileReader(new FullFileReadStrategy());
		try {
			System.out.print(reader.ReadFile("/home/saba/hello.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
