package tsk_typer;

import interfaces.*;

public class TskTyperPresenter extends BaseSubject implements IObserver
{
    private static TskTyperModel model_;

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

    public static TskTyperModel getModel()
    {
        return model_;
    }

    public void update()
    {

    }
}
