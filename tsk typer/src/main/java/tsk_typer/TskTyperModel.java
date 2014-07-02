package tsk_typer;

import interfaces.BaseSubject;
import utilities.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import level.LevelModel;

public class TskTyperModel extends BaseSubject
{
    LevelModel levelModel_;
    private TextFileReader reader_;
    private String filename_;

    public TskTyperModel()
    {
        reader_ = new TextFileReader(new FullFileReadStrategy());
    }

    public LevelModel getLevelModel()
    {
        return levelModel_;
    }

    public void setLevelModel(LevelModel model)
    {
        levelModel_ = model;
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
