package view;
import presenter.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class TypingLevelView extends JComponent implements IView
{
  private EventListenerList listenerList_ = new EventListenerList();


  private final int width_,
                    height_;

  private TypingLevelPresenter presenter_;

  // Components
  private JLabel previouslyTyped,
                 beingTyped,
                 toBeTyped;

  public TypingLevelView( int width, int height )
  {
    super();
    width_  = width;
    height_ = height;
    this.setLayout( new FlowLayout( FlowLayout.LEFT, width_/5, height_/3 ) );

    // Text to be typed
    previouslyTyped = new JLabel( "Previously Typed", JLabel.LEFT );
    beingTyped = new JLabel( "Being Typed", JLabel.CENTER );
    toBeTyped = new JLabel( "To Be Typed", JLabel.RIGHT );

    this.add( previouslyTyped );
    this.add( beingTyped );
    this.add( toBeTyped );

    addMouseListener( new MouseAdapter()
    {
        public void mousePressed( MouseEvent e )
        {
          fireActionPerformed( new ActionEvent( this, 0, "MOUSE_PRESSED" ) );
          System.out.println( "Mouse Pressed" );
        }

        public void mouseReleased( MouseEvent e )
        {
        }
    });

    addKeyListener( new KeyAdapter()
    {
      public void keyTyped( KeyEvent e )
      {
        fireActionPerformed( new ActionEvent( this, 0, "KEY_TYPED" ) );
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
    g2.setPaint( Color.WHITE );
    g2.fillRect( 0, 0, width_, height_ );

    g2.setPaint( Color.BLACK );
    // previouslyTyped.paint( g2 );
  }

  public void updateFromPresenter()
  {

  }

  public void saveToPresenter()
  {

  }

  //***************************
  //*   Event Listener code
  //***************************
  public void addActionListener( ActionListener l )
  {
      listenerList_.add( ActionListener.class, l );
  }

  public void removeActionListener( ActionListener l )
  {
      listenerList_.remove( ActionListener.class, l );
  }

  protected void fireActionPerformed( ActionEvent e )
  {
      Object[] listeners = listenerList_.getListenerList();
      for (int i = listeners.length-2; i>=0; i-=2)
      {
          if (listeners[i]==ActionListener.class)
              ((ActionListener)listeners[i+1]).actionPerformed(e);
      }

  }
}
