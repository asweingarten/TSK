package model;

import java.util.Map;
import java.util.HashMap;
import java.lang.IllegalArgumentException;

public class LevelModel
{
    private String text_;   // full text
    private Map characterMap_;  // maps each character in text_ to a character mode

    public enum CharacterMode {
        CORRECT,
        INCORRECT,
        BAD_FORM
    }

    public LevelModel(String text) {
        this.text_ = text;
        this.characterMap_ = new HashMap(text_.length());
    }

    public CharacterMode getCharacterMode(int index) throws IllegalArgumentException {
        if (index >= text_.length()) {
            throw new IllegalArgumentException();
        }
        return (CharacterMode)this.characterMap_.get(index);
    }

    public void setCharacterMode(int index, CharacterMode mode) {
        this.characterMap_.put(index, mode);
    }
}
