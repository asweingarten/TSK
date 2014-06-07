package model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FullFileReadStrategy implements IReadStrategy
{

    @Override
    public String ReadFile(String filename) throws IOException {
        List<String> lineList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        StringBuilder builder = new StringBuilder();
        for (String line : lineList) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

}
