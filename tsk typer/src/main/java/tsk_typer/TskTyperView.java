package tsk_typer;

import interfaces.*;
import level.LevelScreen;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class TskTyperView extends JComponent implements IView, IObserver
{
    // Window information
    private final int FPS_ = 30;
    private final int width_  = 800;
    private final int height_ = 600;

    // Presenter
    private TskTyperPresenter presenter_;

    private JComponent currentScreen_;

    public TskTyperView()
    {
        super();
        this.setLayout( new BorderLayout() );
        presenter_ = new TskTyperPresenter();
        presenter_.subscribe( this );

        currentScreen_ = null;
        // Debug function
        presenter_.startLevel();
    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
    }

    public void setKeyboardFocus()
    {
        currentScreen_.requestFocusInWindow();
    }

    public synchronized void updateFromPresenter()
    {

    }

    public void saveToPresenter()
    {

    }

    public void update()
    {
        String currentScreenName = presenter_.getCurrentScreen();
        Dimension levelViewDim;

        if ( currentScreen_ != null )
        {
            this.remove( currentScreen_ );
            this.revalidate();
        }

        switch( currentScreenName )
        {
            case "results":
                currentScreen_ = new LevelScreen( width_, height_ );
                levelViewDim = new Dimension( width_/2, height_/2 );
                currentScreen_.setPreferredSize( levelViewDim );
                this.add( currentScreen_, BorderLayout.CENTER );
                break;

            case "level":
                currentScreen_ = new LevelScreen( width_, height_ );
                levelViewDim = new Dimension( width_/2, height_/2 );
                currentScreen_.setPreferredSize( levelViewDim );
                this.add( currentScreen_, BorderLayout.CENTER );
                break;
        }

        setKeyboardFocus();
    }

}
