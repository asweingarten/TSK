package view;
import presenter.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

// @TODO: Get word_ drawing to the screen

public class CurrentTextComponent extends JComponent implements IView
{
  private String word_;
  private int    currentCharIndex;

  private Font font_;

  public CurrentTextComponent()
  {
    this.setLayout( new BorderLayout() );
  }

  public void setNewWord( String newWord )
  {
    word_ = newWord;
    currentCharIndex = 0;
  }

  public void keyTyped( KeyEvent e )
  {

  }

  public void paintComponent( Graphics g )
  {
    Graphics2D g2 = ( Graphics2D ) g;
    g2.setPaint( Color.BLACK );
    g2.drawString( word_, 50, 50 );
  }

  public void updateFromPresenter()
  {

  }

  public void saveToPresenter()
  {
    // presenter_.setLastEnteredKey();
  }


}