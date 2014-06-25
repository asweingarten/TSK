package level;

// import java.util.Array;
import java.util.ArrayList;
import java.lang.*;
import java.awt.Color;

public class LevelPresenter
{
    private LevelModel model_;
    private int currentIndex_;  // index of current character
    private int leftIndex_; // index of left-most character
    private int rightIndex_;    // index of right-most character

    private static final int windowSize_ = 50;

    public enum CharacterColor
    {
        BLACK(0),
        GREEN(1),
        RED(2),
        YELLOW(3),
        INVALID(4);

        private int value_;

        private CharacterColor( int value )
        {
            this.value_ = value;
        }

        public int getValue()
        {
            return value_;
        }

        public Color getColor()
        {
            switch ( this )
            {
                case BLACK:
                return Color.BLACK;
                case GREEN:
                return Color.GREEN;
                case RED:
                return Color.RED;
                case YELLOW:
                return Color.YELLOW;
                default:
                return Color.BLUE;
            }
        }
    }

    public LevelPresenter()
    {
    }

    public void setTextContents( String text )
    {
        model_ = new LevelModel( text );
        currentIndex_ = 0;
        leftIndex_ = 0;
        rightIndex_ = Math.min( model_.getTextLength() - 1, leftIndex_ + windowSize_ );
    }

    public int getWindowSize()
    {
        return windowSize_;
    }

    public int getLeftIndex()
    {
        return leftIndex_;
    }

    public int getRightIndex()
    {
        return rightIndex_;
    }

    public int getCurrentIndex()
    {
        return currentIndex_;
    }

    public String getTextToDraw()
    {
        StringBuilder textBuilder = new StringBuilder();
        for ( int index = leftIndex_; index < rightIndex_; ++index )
        {
            textBuilder.append( model_.getCharacter( index ) );
        }
        return textBuilder.toString();
    }

    private void incrementIndices()
    {
        ++currentIndex_;
        if ( currentIndex_ > ( windowSize_ / 2 ) )
        {
            ++leftIndex_;
        }
        rightIndex_ = Math.min( rightIndex_ + 1, model_.getTextLength() - 1 );
    }

    private CharacterColor getCharacterColor( int index )
    {
        try
        {
            LevelModel.CharacterMode mode = model_.getCharacterMode( index );
            return CharacterColor.values()[ mode.getValue() ];
        }
        catch ( IllegalArgumentException e )
        {
            System.err.println( "Error in getCharacterColor" );
        }
        return CharacterColor.INVALID;
    }

    public Color[] getCharacterColors()
    {
        Color[] colorList = new Color[windowSize_];
        for ( int index = leftIndex_; index < leftIndex_+windowSize_; ++index )
        {
            colorList[index - leftIndex_] = getCharacterColor( index ).getColor();
        }
        return colorList;
    }

    public void handleTypedCharacter( char character )
    {
        if ( model_.getCharacter( currentIndex_ ) == character )
        {
            model_.setCharacterMode( currentIndex_, LevelModel.CharacterMode.CORRECT );
        }
        else
        {
            model_.setCharacterMode( currentIndex_, LevelModel.CharacterMode.INCORRECT );
        }
        incrementIndices();
    }
}
