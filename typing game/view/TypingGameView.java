package view;
import presenter.*;

import javax.swing.*;
import java.awt.*;

public class TypingGameView extends JComponent implements IView
{
    // Window information
    private final int FPS_ = 30;
    private final int width_  = 800;
    private final int height_ = 600;

    // Presenter
    private TypingGamePresenter presenter_;

    private TypingLevelView levelView_;

    public TypingGameView()
    {
        super();
        this.setLayout( new BorderLayout() );

        presenter_ = new TypingGamePresenter();

      // Typing Level Init
      levelView_ = new TypingLevelView( width_, height_ );
      Dimension levelViewDim = new Dimension( width_/2, height_/2 );
      levelView_.setPreferredSize( levelViewDim );
      this.add( levelView_, BorderLayout.CENTER );
    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
    }

    public synchronized void updateFromPresenter()
    {

    }

    public void saveToPresenter()
    {

    }

}