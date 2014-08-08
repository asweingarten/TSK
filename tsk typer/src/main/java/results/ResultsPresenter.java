package results;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.lang.*;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ResultsPresenter extends BasePresenter
{
    ResultsModel resultsModel_;
    boolean doneViewing_;

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

    public void restartGame()
    {
        exportResults();
        tskTyperModel_.initializeGame();
    }

    private void exportResults()
    {
        JSONObject json = new JSONObject();
        json.put( "totalChars", getTotalChars() );
        json.put( "mistypedChars", getNumMistypedChars() );
        json.put( "totalWords", getTotalWords() );
        json.put( "mistypedWords", getNumMistypedWords() );
        json.put( "wordsPerMinute", new DecimalFormat("#.##").format(getWordsPerMinute()) );
     
        try {
            java.util.Date now = Calendar.getInstance().getTime();
            FileWriter file = new FileWriter( "results/" + now.getTime() + ".json" );
            file.write(json.toJSONString());
            file.flush();
            file.close();
     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
