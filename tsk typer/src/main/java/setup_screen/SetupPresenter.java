package setup_screen;

import interfaces.*;
import tsk_typer.TskTyperModel;

import java.lang.*;
import java.util.*;
import java.io.IOException;

public class SetupPresenter extends BasePresenter
{
    private boolean is_panel_expanded_;  // determines whether the directory panel is visible or not

    public SetupPresenter()
    {
        super();
        setDirectoryPanel( false );
    }

    public void setDirectoryPanel( boolean is_visible )
    {
        is_panel_expanded_ = is_visible;
    }

    public boolean isDirectoryPanelVisible()
    {
        return is_panel_expanded_;
    }

    public List<String> getFileNameList()
    {
        return tskTyperModel_.getSetupModel().getFileNames();
    }

    public void setFileName( String fileName ) throws IOException
    {
        tskTyperModel_.getSetupModel().setFileName( fileName );
    }

    public String getFileName()
    {
        return tskTyperModel_.getSetupModel().getFileName();
    }

    public String getFileContents()
    {
        return tskTyperModel_.getSetupModel().getFileContents();
    }

    public void setDirectory( String directory ) throws IOException
    {
        tskTyperModel_.getSetupModel().setDirectory( directory );
    }

    public String getDirectory()
    {
        return tskTyperModel_.getSetupModel().getDirectory();
    }
}
