package model;

import java.io.IOException;

public interface IReadStrategy
{
    String ReadFile(String filename) throws IOException;
}
