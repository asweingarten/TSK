package presenter;

import model.*;
import java.lang.*;

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
        YELLOW(3);

        private int value_;

        private CharacterColor(int value)
        {
            this.value_ = value;
        }

        public int getValue()
        {
            return value_;
        }
    }

    public LevelPresenter()
    {
    }

    public void setTextContents(String text)
    {
        model_ = new LevelModel(text);
        currentIndex_ = 0;
        leftIndex_ = 0;
        rightIndex_ = Math.min(model_.getTextLength(), leftIndex_ + windowSize_);
    }

    public int getLeftIndex()
    {
        return leftIndex_;
    }

    public int getRightIndex()
    {
        return rightIndex_;
    }

    public CharacterColor getCharacterColor(int index) throws IllegalArgumentException
    {
        try
        {
            LevelModel.CharacterMode mode = model_.getCharacterMode(index);
            return CharacterColor.values()[ mode.getValue() ];
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException();
        }
    }

    public void handleTypedCharacter(char character)
    {
        if (model_.getCharacter(currentIndex_) == character)
        {
            model_.setCharacterMode(currentIndex_, LevelModel.CharacterMode.CORRECT);
        }
        else
        {
            model_.setCharacterMode(currentIndex_, LevelModel.CharacterMode.INCORRECT);
        }
        ++currentIndex_;
    }
}
