package results;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.util.ArrayList;
import java.lang.*;
import java.awt.Color;

public class ResultsPresenter extends BasePresenter
{
    ResultsModel resultsModel_;

    public ResultsPresenter()
    {
        super();
        resultsModel_ = tskTyperModel_.getResultsModel();
    }

    public int getTotalChars()
    {
        return resultsModel_.getTotalChars();
    }

    public int getNumMistypedChars() 
    {
        return resultsModel_.getNumMistypedChars();
    }

    public int getTotalWords()
    {
        return resultsModel_.getTotalWords();
    }

    public int getNumMistypedWords()
    {
        return resultsModel_.getNumMistypedWords();
    }

    public int countMistypes(char c) 
    {
        return resultsModel_.countMistypes(c);
    }

    public double getWordsPerMinute()
    {
        return resultsModel_.getWordsPerMinute();
    }



}
