package level;

// import java.util.Array;
import interfaces.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class LevelScreen extends JComponent implements IView
{
  private EventListenerList listenerList_ = new EventListenerList();


  private final int width_,
                    height_;

  private char lastTypedChar;

  private LevelPresenter presenter_;

  private Font font_;

  private String textToDraw_;
  private Color[] characterColors_;

  public LevelScreen( int width, int height )
  {
    super();
    width_  = width;
    height_ = height;
    this.setFocusable(true);
    this.setLayout( new FlowLayout( FlowLayout.LEFT, width_/5, height_/3 ) );
    font_ = new Font( "Courier New", Font.PLAIN, 16 );
    presenter_ = new LevelPresenter();
    presenter_.setTextContents( "My country, 'tis of thee," +
                                  "Sweet land of liberty," +
                                  "Of thee I sing;" +
                                  "Land where my fathers died," +
                                  "Land of the pilgrims' pride," +
                                  "From ev'ry mountainside" +
                                  "Let freedom ring!" +
                                  "2" +
                                  "My native country, thee," +
                                  "Land of the noble free," +
                                  "Thy name I love;" +
                                  "I love thy rocks and rills," +
                                  "Thy woods and templed hills;" +
                                  "My heart with rapture thrills," +
                                  "Like that above." +
                                  "3" +
                                  "Let music swell the breeze," +
                                  "And ring from all the trees" +
                                  "Sweet freedom's song;" +
                                  "Let mortal tongues awake;" +
                                  "Let all that breathe partake;" +
                                  "Let rocks their silence break," +
                                  "The sound prolong." +
                                  "4" +
                                  "Our fathers' God to Thee," +
                                  "Author of liberty," +
                                  "To Thee we sing." +
                                  "Long may our land be bright," +
                                  "With freedom's holy light," +
                                  "Protect us by Thy might," +
                                  "Great God our King." );
    initializeListeners();
    updateFromPresenter();
  }

  public void paintComponent( Graphics g )
  {
    Graphics2D g2 = ( Graphics2D ) g;
    g2.setFont( font_ );
    g2.setPaint( Color.WHITE );
    g2.fillRect( 0, 0, width_, height_ );

    for ( int i = 0; i < textToDraw_.length(); ++i )
    {
      g2.setPaint( characterColors_[i] );
      g2.drawString( textToDraw_.substring( i, i+1 ), 200 + i*9, 295 );
    }
  }

  private void initializeListeners()
  {
    addKeyListener( new KeyAdapter()
    {
      public void keyTyped( KeyEvent e )
      {
        fireActionPerformed( new ActionEvent( this, 0, "KEY_TYPED" ) );
        System.out.println( "A key was typed: " + e.getKeyChar() );
        lastTypedChar = e.getKeyChar();
        saveToPresenter();
      }

      public void keyReleased( KeyEvent e )
      {
        updateFromPresenter();
      }

    });
  }

  public void updateFromPresenter()
  {
    textToDraw_ = presenter_.getTextToDraw();
    characterColors_ = presenter_.getCharacterColors();
  }

  public void saveToPresenter()
  {
    presenter_.handleTypedCharacter( lastTypedChar );
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
