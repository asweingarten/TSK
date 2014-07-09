package tsk_typer;

import interfaces.*;
import javax.swing.JComponent;
import level.LevelScreen;

import java.util.ArrayList;

public class TskTyperPresenter extends BaseSubject implements IObserver
{
    private static TskTyperModel model_;
    private JComponent currentScreen_;
    int width_,
        height_;

    public TskTyperPresenter()
    {
        this.observerList_ = new ArrayList<IObserver>();

        this.model_ = new TskTyperModel();
        this.model_.subscribe( this );
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

    public JComponent getCurrentScreen()
    {
        return currentScreen_;
    }

    public void setScreenDimensions( int width, int height )
    {
        width_  = width;
        height_ = height;
    }

    public void update()
    {
        switch( model_.getCurrentScreen() )
        {
            case RESULTS:
                currentScreen_ = new LevelScreen( width_, height_ );
                break;

            case LEVEL:
                currentScreen_ = new LevelScreen( width_, height_ );
                break;
        }
        System.out.println( "width: " + width_ + " height:" + height_ );
        publish();
    }
}
