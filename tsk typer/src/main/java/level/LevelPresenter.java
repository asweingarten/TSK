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
                rightIndex_;    // index of right-most character

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
        setTextContents(tskTyperModel_.getSetupModel().getFormattedText());
    }

    public void setTextContents( String text )
    {
        int numberPaddingCharacters = Math.round( fixedWindowSize_ / 2 );
        currentIndex_ = numberPaddingCharacters;

        String padding = "";
        for ( int i = 0; i < numberPaddingCharacters; ++i )
            padding += " ";
        tskTyperModel_.setLevelModel(new LevelModel( padding + text ));

        leftIndex_ = 0;
        rightIndex_ = getWindowSize();
    }

    public int getWindowSize()
    {
        return fixedWindowSize_ > tskTyperModel_.getLevelModel().getTextLength() ? tskTyperModel_.getLevelModel().getTextLength() : fixedWindowSize_;
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
        ++currentIndex_;
        ++leftIndex_;
        rightIndex_ = Math.min( rightIndex_ + 1, tskTyperModel_.getLevelModel().getTextLength() - 1 );
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
        for ( int index = leftIndex_; index < rightIndex_; ++index )
        {
            colorList[index - leftIndex_] = getCharacterColor( index ).getColor();
        }
        return colorList;
    }

    public void handleTypedCharacter( char character )
    {
        System.out.print("Current character " + character + " Current index = " + currentIndex_);
        if ( tskTyperModel_.getLevelModel().getCharacter( currentIndex_ ) == character )
        {
            tskTyperModel_.getLevelModel().setCharacterMode( currentIndex_, LevelModel.CharacterMode.CORRECT );
            System.out.println(" CORRECT ");
        }
        else
        {
            tskTyperModel_.getLevelModel().setCharacterMode( currentIndex_, LevelModel.CharacterMode.INCORRECT );
            System.out.println(" INCORRECT ");
        }

        if ( currentIndex_ == tskTyperModel_.getLevelModel().getTextLength()-1 )
        {
            endLevel();
        }

        incrementIndices();

    }

    private void endLevel()
    {
        String levelText = tskTyperModel_.getLevelModel().getLevelText(),
               trimmedLevelText = levelText.trim();
        int offset = levelText.length() - trimmedLevelText.length();

        System.out.println( "offset: " + offset + "    levelText Length: " + levelText.length() + "    trimmed len:" + trimmedLevelText.length() );

        LevelModel.CharacterMode[] characterModeList = tskTyperModel_.getLevelModel().getCharacterModeList(),
                        trimmedCharacterModeList = new LevelModel.CharacterMode[characterModeList.length - offset];
        for ( int i = offset; i < characterModeList.length; ++i )
        {
            trimmedCharacterModeList[i - offset] = characterModeList[i-1];
        }

        tskTyperModel_.endLevel( trimmedLevelText, trimmedCharacterModeList );
        System.out.println( "LEVEL OVER" );
    }
}
