package results;

import level.LevelModel;

public class ResultsModel
{
	private String levelText_;

	private LevelModel.CharacterMode[] characterModeList_;

    public ResultsModel( String levelText, LevelModel.CharacterMode[] characterModeList )
    {
    	levelText_ = levelText;
    	characterModeList_ = characterModeList;
    }

    public void calculateResults()
    {

    }
}
