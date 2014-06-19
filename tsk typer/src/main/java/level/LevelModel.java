package level;

import java.lang.IllegalArgumentException;

public class LevelModel
{
    private String text_;                        // full text
    private CharacterMode[] characterModeList_;  // stores each character mode for each character in text_ in corresponding index

    public enum CharacterMode
    {
        UNTYPED(0),
        CORRECT(1),
        INCORRECT(2),
        BAD_FORM(3);

        private int value_;

        private CharacterMode( int value )
        {
            this.value_ = value;
        }

        public int getValue()
        {
            return value_;
        }

    }

    public LevelModel( String text )
    {
        this.text_ = text;
        this.characterModeList_ = new CharacterMode[text_.length()];
        for ( int i = 0; i < text_.length(); ++i )
            this.characterModeList_[i] = CharacterMode.UNTYPED;
    }

    public int getTextLength()
    {
        return text_.length();
    }

    public CharacterMode getCharacterMode( int index ) throws IllegalArgumentException
    {
        if ( index >= text_.length() )
        {
            throw new IllegalArgumentException();
        }
        return (CharacterMode)this.characterModeList_[index];
    }

    public void setCharacterMode( int index, CharacterMode mode )
    {
        this.characterModeList_[index] = mode;
    }

    public char getCharacter( int index ) throws IllegalArgumentException
    {
        if ( index >= text_.length() )
        {
            throw new IllegalArgumentException();
        }
        return text_.charAt( index );
    }
}
