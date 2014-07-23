package setup_screen;

import java.lang.*;
import java.io.*;
import java.nio.*;
import java.util.*;
import utilities.*;

public class SetupModel
{
    private File directory_;
    private File file_;
    private TextFileReader reader_;

    public SetupModel( String directory )
    {
        try
        {
            setDirectory( directory );
        }
        catch ( IOException ex )
        {
            directory_ = null;
        }
        reader_ = new TextFileReader( new FullFileReadStrategy() );
    }

    private boolean isFileValid( String fileName )
    {
        File file = new File( fileName );
        return file.getName().toLowerCase().endsWith( ".txt" );
    }

    private boolean isDirectoryValid( String directory )
    {
        File folder = new File( directory );
        return folder.exists() && folder.isDirectory();
    }

    public String getDirectory()
    {
        if ( null != directory_ )
        {
            return directory_.getName();
        }
        return "";
    }

    public void setDirectory( String directory ) throws IOException
    {
        if ( isDirectoryValid( directory ) )
        {
            directory_ = new File( directory );
        }
        else
        {
            throw new IOException();
        }
    }

    public List<String> getFileNames()
    {
        File[] fileList = directory_.listFiles();
        List<String> textFileList = new ArrayList<String>();
        for ( File file : fileList )
        {
            if ( isFileValid( file.getName() ) )
            {
                textFileList.add( file.getName() );
            }
        }
        return textFileList;
    }

    public String getFileName()
    {
        if ( null != file_ )
        {
            return file_.getName();
        }
        return "";
    }

    public void setFileName( String fileName ) throws IOException
    {
        if ( isFileValid( fileName ) )
        {
            file_ = new File( fileName );
        }
        else
        {
            throw new IOException();
        }
    }

    public String getFileContents()
    {
        try
        {
            return reader_.ReadFile( file_.getAbsolutePath() );
        }
        catch ( IOException ex )
        {
            return "";
        }
    }
}
