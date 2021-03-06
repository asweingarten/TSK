package tsk_typer;

import interfaces.*;

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

        saveToPresenter();

        currentScreen_ = null;
        // Debug function
        //presenter_.startLevel();
        presenter_.initializeGame();

    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
    }

    public void setKeyboardFocus()
    {
        currentScreen_.requestFocusInWindow();
    }

    public void updateFromPresenter()
    {

    }

    public void saveToPresenter()
    {
        presenter_.setScreenDimensions( width_, height_ );
    }

    public void update()
    {
        JComponent newScreen = presenter_.getCurrentScreen();

        if ( currentScreen_ != null )
        {
            this.remove( currentScreen_ );
            this.revalidate();
        }

        currentScreen_ = newScreen;
        this.add( currentScreen_, BorderLayout.CENTER );
        setKeyboardFocus();
    }

}
