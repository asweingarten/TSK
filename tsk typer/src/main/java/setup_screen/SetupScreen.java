package setup_screen;

import interfaces.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.*;
import java.io.File;
import java.io.IOException;

public class SetupScreen extends JComponent implements IView
{
    private int width_,
                height_;

    private SetupPresenter presenter_;

    private JComboBox<String> levelSelector_;
    private JTextArea selectedLevelText_;
    
    private JPanel directoryPanel_;
    private JLabel directoryLabel_;
    private JTextField directoryTextBox_;
    private JButton browseButton_;

    private JButton beginButton_;

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
            presenter_.setDirectory( new File( "./levels" ).getCanonicalPath() );
        }
        catch ( IOException ex )
        {
            System.err.println( "directory not found: " + ex );
        }
        initializeControls();
        updateFromPresenter();
    }

    private void initializeLevelControls()
    {
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

        this.setLayout( new BorderLayout() );
        this.add( levelSelector_, BorderLayout.PAGE_START );
        this.add( new JScrollPane( selectedLevelText_ ), BorderLayout.CENTER );
    }

    private void initializeDirectoryControls()
    {
        directoryPanel_ = new JPanel();

        directoryLabel_ = new JLabel("Folder: ");

        directoryTextBox_ = new JTextField();

        browseButton_ = new JButton("Browse");

        directoryPanel_.add(directoryLabel_);
        directoryPanel_.add(directoryTextBox_);
        directoryPanel_.add(browseButton_);

        this.add(directoryPanel_, BorderLayout.PAGE_END );
    }

    private void initializeControls()
    {
        initializeLevelControls();
        //initializeDirectoryControls();

        beginButton_ = new JButton("Begin");

        beginButton_.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                presenter_.startLevel();
            }
        });

        this.add( beginButton_, BorderLayout.PAGE_END );
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
        beginButton_.setEnabled( null != presenter_.getText() && 0 < presenter_.getText().trim().length() );
    }
}
