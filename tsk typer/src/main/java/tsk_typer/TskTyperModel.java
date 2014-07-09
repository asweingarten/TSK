package tsk_typer;

import interfaces.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import level.LevelModel;

public class TskTyperModel extends BaseSubject
{
    LevelModel levelModel_;
    private Screen currentScreen_;

    public enum Screen
    {
        SETUP(0),
        LEVEL(1),
        RESULTS(2);

        private int value_;

        private Screen( int value )
        {
            this.value_ = value;
        }

        public int getValue()
        {
            return value_;
        }
    }

    public TskTyperModel()
    {
        this.observerList_ = new ArrayList<IObserver>();
    }

    public LevelModel getLevelModel()
    {
        return levelModel_;
    }

    public void setLevelModel(LevelModel model)
    {
        levelModel_ = model;
    }

    public void startLevel()
    {
        currentScreen_ = Screen.LEVEL;
        publish();
    }

    public void endLevel()
    {
        currentScreen_ = Screen.RESULTS;
        publish();
    }

    public Screen getCurrentScreen()
    {
        return currentScreen_;
    }
}
