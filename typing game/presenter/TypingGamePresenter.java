package presenter;
import model.*;

public class TypingGamePresenter
{
    TypingGameModel model_;

    public TypingGamePresenter()
    {
        this.model_ = new TypingGameModel();
    }

    public String GetFilename()
    {
        return model_.GetFilename();
    }

    public void SetFilename(String filename)
    {
        model_.SetFilename(filename);
    }

    public String GetFileContents()
    {
        return model_.GetFileContents();
    }

    public boolean IsGameReady()
    {
        return model_.IsFileValid();
    }
}
