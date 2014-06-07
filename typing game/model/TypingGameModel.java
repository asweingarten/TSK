package model;

import java.util.ArrayList;

public class TypingGameModel
{
    private TextFileReader reader_;

    public TypingGameModel()
    {
        reader_ = new TextFileReader(new FullFileReadStrategy());
    }

    public String GetFileContents(String filename)
    {
        return reader_.ReadFile(filename);
    }
}
