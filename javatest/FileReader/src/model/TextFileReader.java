package model;

import java.io.IOException;

public class TextFileReader {	
	private IReadStrategy strategy_;

	public TextFileReader(IReadStrategy strategy) {
		this.strategy_ = strategy;
	}
	
	public String ReadFile(String filename) throws IOException {
		return this.strategy_.ReadFile(filename);
	}
}
