package setup_screen;

import java.lang.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class SetupModel
{
	private File directory_;

	public SetupModel( String directory )
	{
		setDirectory( directory );
	}

	private Bool isFileValid( File fileName )
	{
		return fileName.isFile() && fileName.toLowerCase().endsWith( ".txt" );
	}

	private Bool isDirectoryValid( String directory )
	{
		File folder = new File( directory );
		return folder.exists() && folder.isDirectory();
	}

	public String getDirectory()
	{
		if ( NULL != directory_ )
		{
			return directory_.getName();
		}
		return "";
	}

	public void setDirectory( String directory )
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
			if ( isFileValid( file ) )
			{
				textFileList.add( file.getName() );
			}
		}
		return textFileList;
	}
}