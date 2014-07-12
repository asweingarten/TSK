package results;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.util.ArrayList;
import java.lang.*;
import java.awt.Color;

public class ResultsPresenter extends BasePresenter
{

    //Call tsk typer model to get handle to results model (contained in BasePresenter)

    public ResultsPresenter()
    {
        super();
    }

    public int getTotalChars()
    {
        return 0;
    }

    public int getNumMistypedChars() 
    {
        return 0;
    }

    public int getTotalWords()
    {
        return 0;
    }

    public int getNumMistypedWords()
    {
        return 0;
    }

    public int countMistypes(char c) 
    {
        return 0;
    }

    public int getWordsPerMinute()
    {
        return 0;
    }



}
