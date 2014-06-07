package presenter;
import model.*;

public class TskTyperPresenter
{
    TskTyperModel model_;

    public TskTyperPresenter()
    {
        this.model_ = new TskTyperModel();
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
