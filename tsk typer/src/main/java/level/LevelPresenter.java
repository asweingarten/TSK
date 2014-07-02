package level;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.util.ArrayList;
import java.lang.*;
import java.awt.Color;

public class LevelPresenter extends BasePresenter
{
    private int currentIndex_,  // index of current character
                leftIndex_,     // index of left-most character
                rightIndex_,    // index of right-most character
                numberPaddingCharacters_;

    private int fixedWindowSize_;

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

    public LevelPresenter( int charactersOnScreen )
    {
        super();
        fixedWindowSize_ = charactersOnScreen;
    }

    public void setTextContents( String text )
    {
        leftIndex_ = 0;
        rightIndex_ = fixedWindowSize_;
        numberPaddingCharacters_ = Math.round( rightIndex_ / 2 );
        currentIndex_ = numberPaddingCharacters_;

        String padding = "";
        for ( int i = 0; i < numberPaddingCharacters_; ++i )
            padding += " ";
        tskTyperModel_.setLevelModel(new LevelModel( padding + text ));
    }

    public int getWindowSize()
    {
        // return Math.max( fixedWindowSize_, rightIndex_ - leftIndex_ );
        return fixedWindowSize_;
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
            textBuilder.append( tskTyperModel_.getLevelModel().getCharacter( index ) );
        }
        return textBuilder.toString();
    }

    private void incrementIndices()
    {
        // System.out.println( "BEFORE Left index: " + leftIndex_ );
        // System.out.println( "BEFORE Current index: " + currentIndex_ );
        // System.out.println( "BEFORE Right index: " + rightIndex_ );
        ++currentIndex_;
        ++leftIndex_;
        rightIndex_ = Math.min( rightIndex_ + 1, tskTyperModel_.getLevelModel().getTextLength() - 1 );

        if ( numberPaddingCharacters_ > 0 )
            --numberPaddingCharacters_;
        // System.out.println( "AFTER Left index: " + leftIndex_ );
        // System.out.println( "AFTER Current index: " + currentIndex_ );
        // System.out.println( "AFTER Right index: " + rightIndex_ );

    }

    private CharacterColor getCharacterColor( int index )
    {
        try
        {
            LevelModel.CharacterMode mode = tskTyperModel_.getLevelModel().getCharacterMode( index );
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
        Color[] colorList = new Color[getWindowSize()];
        for ( int index = leftIndex_; index < leftIndex_+getWindowSize(); ++index )
        {
            colorList[index - leftIndex_] = getCharacterColor( index ).getColor();
        }
        return colorList;
    }

    public void handleTypedCharacter( char character )
    {
        if ( tskTyperModel_.getLevelModel().getCharacter( currentIndex_ ) == character )
        {
            tskTyperModel_.getLevelModel().setCharacterMode( currentIndex_, LevelModel.CharacterMode.CORRECT );
        }
        else
        {
            tskTyperModel_.getLevelModel().setCharacterMode( currentIndex_, LevelModel.CharacterMode.INCORRECT );
        }
        incrementIndices();
    }
}
