package results;

import level.LevelModel;

public class ResultsModel
{
    private final String PUNCTUATION = " ,.?!\":;\t\n";

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
                if (levelText_.charAt(i) == c && characterModeList_[i].getValue() != 1)
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
        boolean isWord = false;
        boolean isWordCorrect = true;

        for (int i = 0; i < levelText_.length(); i++) 
        {
            try 
            {
                char currentCharacter = levelText_.charAt(i);
                totalChars_++;

                if (PUNCTUATION.indexOf(currentCharacter) >= 0) 
                {
                    if (isWord == true) 
                    {
                        isWord = false;
                        totalWords_++;
                        if (isWordCorrect == false) {
                            mistypedWords_++;
                        }
                        isWordCorrect = true;
                    } 
                } 
                else
                {
                    isWord = true;
                }

                if (characterModeList_[i].getValue() != 1) 
                {
                    mistypedChars_++;
                    isWordCorrect = !isWord;
                }

                System.out.println("Character: " + currentCharacter + " processed. Bad Chars = " + mistypedChars_ + ", Bad Words = " + mistypedWords_);
            } 
            catch (Exception e) 
            {
                System.out.println("ERROR ACCESSING CHARACTER MODE LIST");
            }
        }
    }

}
