package tsk_typer;

import interfaces.*;
import java.util.ArrayList;

public class TskTyperPresenter extends BaseSubject implements IObserver
{
    private static TskTyperModel model_;
    private String currentScreen_;

    public TskTyperPresenter()
    {
        this.observerList_ = new ArrayList<IObserver>();

        this.model_ = new TskTyperModel();
        this.model_.subscribe( this );
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

    // Debug function
    public void startLevel()
    {
        model_.startLevel();
    }

    public String getCurrentScreen()
    {
        return currentScreen_;
    }

    public void update()
    {
        currentScreen_ = model_.getCurrentScreen();
        publish();
    }
}
