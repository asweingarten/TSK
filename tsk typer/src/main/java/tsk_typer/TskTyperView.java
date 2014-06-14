package tsk_typer;

import interfaces.*;
import level.LevelComponent;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class TskTyperView extends JComponent implements IView
{
    // Window information
    private final int FPS_ = 30;
    private final int width_  = 800;
    private final int height_ = 600;

    // Presenter
    private TskTyperPresenter presenter_;

    private LevelComponent levelView_;

    public TskTyperView()
    {
        super();
        this.setLayout( new BorderLayout() );

        presenter_ = new TskTyperPresenter();

      // Typing Level Init
      levelView_ = new LevelComponent( width_, height_ );
      Dimension levelViewDim = new Dimension( width_/2, height_/2 );
      levelView_.setPreferredSize( levelViewDim );
      this.add( levelView_, BorderLayout.CENTER );


      addKeyListener( new KeyListener()
      {
        public void keyTyped( KeyEvent e )
        {
          System.out.println( "A key was typed: " + e.getKeyChar() );
        }

        public void keyPressed( KeyEvent e )
        {
          System.out.println( "A key was pressed: " + e.getKeyChar() );
        }

        public void keyReleased( KeyEvent e )
        {
          System.out.println( "A key was released: " + e.getKeyChar() );
        }

      });
    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
    }

    public void setKeyboardFocus()
    {
      levelView_.requestFocusInWindow();
    }

    public synchronized void updateFromPresenter()
    {

    }

    public void saveToPresenter()
    {

    }

}