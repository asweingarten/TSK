package results;

import interfaces.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class ResultsScreen extends JComponent implements IView
{
    private final int width_,
                      height_;

    private Font resultsTextFont_;

    private ResultsPresenter presenter_;

    public ResultsScreen( int width, int height )
    {
        super();
        width_  = width;
        height_ = height;
        this.setFocusable(true);
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        resultsTextFont_ = new Font( "Courier New", Font.PLAIN, 16 );

        presenter_ = new ResultsPresenter();

        updateFromPresenter();
    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setFont( resultsTextFont_ );
        g2.setPaint( Color.WHITE );
        g2.fillRect( 0, 0, width_, height_ );

        g2.setPaint( Color.BLACK );

        g2.setFont( resultsTextFont_ );
        g2.drawString( "RESULTS SCREEN", 50, 50 );

        int numChars = presenter_.getTotalChars();
        int numMistypedChars = presenter_.getNumMistypedChars();
        int numWords = presenter_.getTotalWords();
        int numMistypedWords = presenter_.getNumMistypedWords();

        g2.drawString( "Total Characters Typed: " + numChars, 50, 70 );
        g2.drawString( "Total Characters Mistyped: " + numMistypedChars, 50, 87 );
        g2.drawString( "Total Words Typed: " + numWords, 50, 105 );
        g2.drawString( "Total Words Mistyped: " + numMistypedWords, 50, 122 );
        //Draw results information
    }

    public void updateFromPresenter()
    {
        repaint();
    }

    public void saveToPresenter()
    {

    }
}
