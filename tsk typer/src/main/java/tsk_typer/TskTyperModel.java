package tsk_typer;

import interfaces.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import level.LevelModel;
import setup_screen.SetupModel;
import results.ResultsModel;
import utilities.TextFileReader;

public class TskTyperModel extends BaseSubject
{
    LevelModel levelModel_;
    SetupModel setupModel_;
    ResultsModel resultsModel_;
    private TextFileReader reader_;
    private String filename_;
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

    public SetupModel getSetupModel()
    {
        return setupModel_;
    }

    public void setSetupModel(SetupModel model)
    {
        setupModel_ = model;
    }

    public ResultsModel getResultsModel()
    {
        return resultsModel_;
    }

    public void setResultsModel(ResultsModel model)
    {
        resultsModel_ = model;
    }

    public void startLevel()
    {
        currentScreen_ = Screen.LEVEL;
        publish();
    }

    public void initializeGame()
    {
        currentScreen_ = Screen.SETUP;
        publish();
    }

    public void endLevel( String levelText, LevelModel.CharacterMode[] charModes )
    {
        currentScreen_ = Screen.RESULTS;
        setResultsModel( new ResultsModel( levelText, charModes ) );
        publish();
    }

    public Screen getCurrentScreen()
    {
        return currentScreen_;
    }
}
