package presenter;
import model.*;

public class TskTyperPresenter
{
    TskTyperModel model_;

    public TskTyperPresenter()
    {
        this.model_ = new TskTyperModel();
    }

    public String getFilename()
    {
        return model_.getFilename();
    }

    public void setFilename(String filename)
    {
        model_.setFilename(filename);
    }

    public String getFileContents()
    {
        return model_.getFileContents();
    }

    public boolean isGameReady()
    {
        return model_.isFileValid();
    }
}
