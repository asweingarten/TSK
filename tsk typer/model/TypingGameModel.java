package model;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class TypingGameModel
{
    private TextFileReader reader_;
    private String filename_;

    public TypingGameModel()
    {
        reader_ = new TextFileReader(new FullFileReadStrategy());
    }

    public String GetFilename()
    {
        return filename_;
    }

    public void SetFilename(String filename)
    {
        filename_ = filename;
    }

    public String GetFileContents()
    {
        try
        {
            return reader_.ReadFile(filename_);
        }
        catch (IOException ex)
        {
            return "";
        }
    }

    public boolean IsFileValid()
    {
        try
        {
            return new File(filename_).isFile();
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
