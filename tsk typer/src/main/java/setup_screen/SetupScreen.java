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
            presenter_.setDirectory( System.getProperty( "user.dir" ) + "/levels" );
        }
        catch ( IOException ex )
        {
            System.err.println( "directory not found: " + ex );
        }

        selectedLevelText_ = new JTextArea();
        selectedLevelText_.setLineWrap( true );
        selectedLevelText_.setWrapStyleWord( true );

        selectedLevelText_.getDocument().addDocumentListener( new DocumentListener()
        {
            @Override
            public void insertUpdate( DocumentEvent e )
            {
                saveToPresenter();
            }

            @Override
            public void removeUpdate( DocumentEvent e )
            {
                saveToPresenter();
            }

            @Override
            public void changedUpdate( DocumentEvent e )
            {
                saveToPresenter();
            }
        });

        levelSelector_ = new JComboBox<String>();

        levelSelector_.addItemListener( new ItemListener()
        {
            @Override
            public void itemStateChanged( ItemEvent e )
            {
                if ( e.getStateChange() == ItemEvent.DESELECTED )
                {
                    return;
                }
                try
                {
                    presenter_.setFileName( (String)e.getItem() );
                    selectedLevelText_.setText( presenter_.getText() );
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
        if ( null == filenames ) {
            return;
        }
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
        presenter_.setText( selectedLevelText_.getText() );
    }
}
