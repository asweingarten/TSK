package view;
import presenter.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// import java.util.*;

public class TypingLevelView extends JComponent implements IView
{
  private EventListenerList listenerList_ = new EventListenerList();


  private final int width_,
                    height_;

  private TypingLevelPresenter presenter_;

  public TypingLevelView( int width, int height )
  {
    super();
    width_  = width;
    height_ = height;

    addMouseListener( new MouseAdapter()
    {
        public void mousePressed( MouseEvent e )
        {
            fireActionPerformed( new ActionEvent( this, 0, "MOUSE_PRESSED" ) );
            repaint();
            System.out.println( "Mouse Pressed" );
        }

        public void mouseReleased( MouseEvent e )
        {
            // fireActionPerformed( new ActionEvent( this, 1, "MOUSE_RELEASED" ) );
        }
    });
  }

  public void paintCompnent( Graphics g )
  {
    Graphics2D g2 = ( Graphics2D ) g;
    g2.setPaint( Color.WHITE );
    g2.fillRect( 0, 0, width_, height_ );
    g2.setPaint( Color.BLACK );
    System.out.println( "Yo, I'm painting here!" );
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
