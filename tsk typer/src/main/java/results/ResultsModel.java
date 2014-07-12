package results;

import level.LevelModel;

public class ResultsModel
{
	private String levelText_;
	private LevelModel.CharacterMode[] characterModeList_;
    private int totalChars_ = 0;
    private int totalWords_ = 0;
    private int mistypedChars_ = 0;
    private int mistypedWords_ = 0;
    private int wordsPerMinute_ = 0;

    public ResultsModel( String levelText, LevelModel.CharacterMode[] characterModeList )
    {
    	levelText_ = levelText;
    	characterModeList_ = characterModeList;
        computeResults();
    }

    public int getTotalChars()
    {
        return totalChars_;
    }

    public int getNumMistypedChars() 
    {
        return mistypedChars_;
    }

    public int getTotalWords()
    {
        return totalWords_;
    }

    public int getNumMistypedWords()
    {
        return mistypedWords_;
    }

    public int getWordsPerMinute()
    {
        return wordsPerMinute_;
    }

    public int countMistypes(char c) 
    {
        //Go through the string of characters, and count the number of times a particular character is mistyped
        //Alternatively, keep a mapping of characters mistyped to the number of times (hashmap) when computing results
        //Return the value in the map for a char, or 0 if it isn't found
        return 0;
    }

    private void computeResults()
    {

    }
}
