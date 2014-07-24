package results;

import interfaces.*;
import javax.swing.*;

import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.List;
import java.util.ArrayList;


public class ResultsScreen extends JComponent implements IView
{

    public class ResultsComponent extends JPanel 
    {
        ResultsPresenter presenter_;
        public ResultsComponent(ResultsPresenter presenter) { presenter_ = presenter; }
    } 

    private final int width_,
                      height_;

    private Font resultsTextFont_;
    private ResultsPresenter presenter_;
    private List<ResultsComponent> resultsComponents;

    public ResultsScreen( int width, int height )
    {
        super();
        width_  = width;
        height_ = height;
        this.setFocusable(true);
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        resultsTextFont_ = new Font( "Courier New", Font.PLAIN, 16 );

        presenter_ = new ResultsPresenter();


        ResultsComponent characterComponent = new ResultsComponent( presenter_ ) {
            {
                this.setLayout( new GridLayout(2, 1) );
                JLabel totalChars = new JLabel("Total Characters Typed: " + presenter_.getTotalChars());
                JLabel mistypedChars = new JLabel("Total Characters Mistyped: " + presenter_.getNumMistypedChars());
                totalChars.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
                mistypedChars.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
                this.add( totalChars );
                this.add( mistypedChars );
                this.revalidate();
                this.setMaximumSize( this. getPreferredSize() );
            }
        };

        ResultsComponent wordComponent = new ResultsComponent( presenter_ ) {
            {
                this.setLayout( new GridLayout(2, 1) );
                JLabel totalWords = new JLabel("Total Words Typed: " + presenter_.getTotalWords());
                JLabel mistypedWords = new JLabel("Total Words Mistyped: " + presenter_.getNumMistypedWords()) ;
                totalWords.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
                mistypedWords.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
                this.add( totalWords );
                this.add( mistypedWords );
                this.revalidate();
                this.setMaximumSize( this. getPreferredSize() );
            }
        };

        this.add( new JLabel("Results:") );
        //this.add(titleComponent);
        this.add(characterComponent);
        this.add(wordComponent);

      //  this.add(resultsPanel);
        revalidate();

        updateFromPresenter();
    }

    public void paintComponent( Graphics g )
    {
        Graphics2D g2 = ( Graphics2D ) g;
        g2.setFont( resultsTextFont_ );
        g2.setPaint( Color.WHITE );
        //g2.fillRect( 0, 0, width_, height_ );

        g2.setPaint( Color.BLACK );
        g2.setFont( resultsTextFont_ );

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
