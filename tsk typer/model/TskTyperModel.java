package model;

import model.utilities.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class TskTyperModel
{
    private TextFileReader reader_;
    private String filename_;

    public TskTyperModel()
    {
        reader_ = new TextFileReader(new FullFileReadStrategy());
    }

    public String getFilename()
    {
        return filename_;
    }

    public void setFilename(String filename)
    {
        filename_ = filename;
    }

    public String getFileContents()
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

    public boolean isFileValid()
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
