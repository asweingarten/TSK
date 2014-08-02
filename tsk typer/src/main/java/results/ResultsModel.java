package results;

import level.LevelModel;

public class ResultsModel
{
    private final String WHITESPACE = " ";

	private String levelText_;
	private LevelModel.CharacterMode[] characterModeList_;
    private int totalChars_ = 0;
    private int totalWords_ = 0;
    private int mistypedChars_ = 0;
    private int mistypedWords_ = 0;
    private double wordsPerMinute_ = 0.0;
    private double elapsedTime_ = 0.0;

    public ResultsModel( String levelText, LevelModel.CharacterMode[] characterModeList, double elapsedTime )
    {
    	levelText_ = levelText;
    	characterModeList_ = characterModeList;
        elapsedTime_ = elapsedTime;
        System.out.println("Elapsed time = " + elapsedTime_);
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

    public double getWordsPerMinute()
    {
        return wordsPerMinute_;
    }

    //Go through the string of characters, and count the number of times a particular character is mistyped
    //Alternatively, keep a mapping of characters mistyped to the number of times (hashmap) when computing results
    //Return the value in the map for a char, or 0 if it isn't found
    public int countMistypes(char c) 
    {
        int mistypes = 0;

        for (int i = 0; i < levelText_.length(); i++) 
        {
            try 
            {
                if (levelText_.charAt(i) == c && characterModeList_[i] == LevelModel.CharacterMode.INCORRECT)
                {
                    mistypes++;
                }
            } 
            catch (Exception e) 
            {
                System.out.println("ERROR ACCESSING CHARACTER MODE LIST");
            }
        }

        return mistypes;
    }


    private void computeResults()
    {
        computeCharacterResults();
        computeWordResults();
    }

    private void computeCharacterResults()
    {
        totalChars_ = levelText_.length();
        for ( int i = 0; i < totalChars_; i++ )
        {
            if ( LevelModel.CharacterMode.INCORRECT == characterModeList_[i] ) 
            {
                mistypedChars_++;
            }
        }
    }

    private void computeWordResults()
    {
        boolean isWord = false;
        boolean isWordCorrect = true;

        for ( int i = 0; i < levelText_.length(); i++ ) 
        {
            try 
            {
                char currentCharacter = levelText_.charAt(i);
                if ( WHITESPACE.indexOf(currentCharacter) >= 0 ) 
                {
                    if ( true == isWord ) 
                    {
                        isWord = false;
                        totalWords_++;
                        if ( false == isWordCorrect ) 
                        {
                            mistypedWords_++;
                        }
                        isWordCorrect = true;
                    } 
                } 
                else
                {
                    isWord = true;
                    if ( LevelModel.CharacterMode.INCORRECT == characterModeList_[i] ) 
                    {
                        isWordCorrect = false;
                    }
                    if ( levelText_.length() - 1 == i )
                    {
                        totalWords_++;
                        if ( false == isWordCorrect ) 
                        {
                            mistypedWords_++;
                        }
                    }
                }
            } 
            catch ( Exception e ) 
            {
                System.out.println("ERROR ACCESSING CHARACTER MODE LIST");
            }
        }

        if ( 0 != elapsedTime_ )
        {
            wordsPerMinute_ = ( totalWords_ / elapsedTime_ ) * 60;
        }
    }

}
