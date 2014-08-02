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

    private Font resultsTitleFont_;
    private Font resultsTextFont_;
    private Font resultsValueFont_;
    private ResultsPresenter presenter_;
    private List<ResultsComponent> resultsComponents;

    public ResultsScreen( int width, int height )
    {
        super();
        width_  = width;
        height_ = height;
        this.setFocusable(true);
        this.setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        resultsTitleFont_ = new Font( "Courier New", Font.PLAIN, 24 );
        resultsTextFont_ = new Font( "Courier New", Font.PLAIN, 16 );
        resultsValueFont_ = new Font( "Courier New", Font.PLAIN, 16 );

        presenter_ = new ResultsPresenter();

        ResultsComponent titleComponent = new ResultsComponent( presenter_ ) {
            {
                JLabel title = new JLabel("Results:");
                title.setFont( resultsTitleFont_ );
                this.add( title );
            }
        };

        ResultsComponent characterComponent = new ResultsComponent( presenter_ ) {
            {
                this.setLayout( new GridLayout(2, 2) );
                JLabel totalChars = new JLabel("Total Characters Typed: " + presenter_.getTotalChars());
                JLabel mistypedChars = new JLabel("Total Characters Mistyped: " + presenter_.getNumMistypedChars());
                totalChars.setFont( resultsTextFont_ );
                mistypedChars.setFont( resultsTextFont_ );
                this.add( totalChars );
                this.add( mistypedChars );
            }
        };

        ResultsComponent wordComponent = new ResultsComponent( presenter_ ) {
            {
                this.setLayout( new GridLayout(3, 1) );
                JLabel totalWords = new JLabel("Total Words Typed: " + presenter_.getTotalWords());
                JLabel mistypedWords = new JLabel("Total Words Mistyped: " + presenter_.getNumMistypedWords()) ;
                JLabel wordsPerMinute = new JLabel("Average Words Per Minute: " + presenter_.getWordsPerMinute()) ;
                totalWords.setFont( resultsTextFont_ );
                mistypedWords.setFont( resultsTextFont_ );
                wordsPerMinute.setFont( resultsTextFont_ );
                this.add( totalWords );
                this.add( mistypedWords );
                this.add( wordsPerMinute );
            }
        };

        resultsComponents = new ArrayList<ResultsComponent>();
        resultsComponents.add( titleComponent );
        resultsComponents.add( characterComponent );
        resultsComponents.add( wordComponent );

        int maxX = 0;
        for (ResultsComponent component : resultsComponents) {
            Dimension d = component.getPreferredSize();
            if ((int) d.getWidth() > maxX) { maxX = (int) d.getWidth() + 10; }
        }

        for (ResultsComponent component : resultsComponents) {
            component.setMaximumSize( new Dimension (maxX, (int) component.getPreferredSize().getHeight()) );
            component.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
            component.revalidate();
            this.add(component);
        }

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
