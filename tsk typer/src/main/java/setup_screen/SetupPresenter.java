package setup_screen;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.lang.*;
import java.util.*;

public class SetupPresenter extends BasePresenter
{
    private boolean is_panel_expanded_;  // determines whether the directory panel is visible or not

    public SetupPresenter()
    {
        super();
        HideDirectoryPanel();
    }

    public void ShowDirectoryPanel()
    {
        is_panel_expanded_ = true;
    }

    public void HideDirectoryPanel()
    {
        is_panel_expanded_ = false;
    }

    public boolean IsDirectoryPanelVisible()
    {
        return is_panel_expanded_;
    }

    public List<String> getFileNameList()
    {
        return tskTyperModel_.getSetupModel().getFileNames();
    }
}
