package setup_screen;

import interfaces.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.*;
import java.io.IOException;

public class SetupScreen extends JComponent implements IView
{
	private int width_,
				height_;

	private SetupPresenter presenter_;

	private JComboBox<String> levelSelector_;
	private JTextArea selectedLevelText_;

	public SetupScreen( int width, int height )
	{
		super();
		width_  = width;
		height_ = height;
		this.setFocusable(true);

		this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		presenter_ = new SetupPresenter();
		try
		{
			// import java.nio.file.Path;
			// import java.nio.file.Paths;

			// System.out.println("Working Directory = " +
			//               System.getProperty("user.dir"));
			// Path currentRelativePath = Paths.get("");
			// String s = currentRelativePath.toAbsolutePath().toString();
			// System.out.println("Current relative path is: " + s);

			presenter_.setDirectory( "/Users/arielweingarten/University/4A/FYDP/TSK/tsk typer/levels" );
		}
		catch ( IOException ex )
		{
			System.err.println( "directory not found: " + ex );
		}

		selectedLevelText_ = new JTextArea();
		selectedLevelText_.setLineWrap( true );
		selectedLevelText_.setWrapStyleWord( true );

		levelSelector_ = new JComboBox<String>();

		levelSelector_.addItemListener( new ItemListener()
		{
			public void itemStateChanged( ItemEvent e )
			{
				if ( e.getStateChange() == ItemEvent.DESELECTED )
					return;

				try
				{
					presenter_.setFileName( (String)e.getItem() );
					selectedLevelText_.setText( presenter_.getFileContents() );
				}
				catch ( IOException ex )
				{

				}
			}
		});

		this.add( levelSelector_ );
		this.add( selectedLevelText_ );
		updateFromPresenter();
	}

	private void populateLevelSelector( java.util.List<String> filenames )
	{
		levelSelector_.removeAllItems();
		for ( String filename : filenames )
		{
			levelSelector_.addItem( filename );
		}
	}

	public void updateFromPresenter()
	{
		populateLevelSelector( presenter_.getFileNameList() );
	}

	public void saveToPresenter()
	{

	}
}
