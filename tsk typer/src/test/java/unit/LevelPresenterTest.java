import static org.junit.Assert.*;
import tsk_typer.*;
import level.*;
import setup_screen.*;
import java.awt.Color;
import org.junit.Test;
import java.io.*;

public class LevelPresenterTest
{
    @Test
    public void correctTypedCharactersTest()
    {
        String input = "hello world";
        TskTyperPresenter globalPresenter = new TskTyperPresenter();
        SetupModel setup;
        try
        {
            setup = new SetupModel( new File( "." ).getCanonicalPath() );
        }
        catch ( IOException e )
        {
            setup = new SetupModel( "" );
        }
        setup.setText(input);
        globalPresenter.getModel().setSetupModel(setup);
        LevelPresenter levelPresenter = new LevelPresenter( 5 );

        for ( int i = 0; i < input.length(); ++i )
        {
            levelPresenter.handleTypedCharacter( input.charAt( i ) );

            Color[] colors = levelPresenter.getCharacterColors();
            int colorIndex = 0;
            for ( Color color : colors )
            {
                if ( true ) // check colors correctly
                {
                }
                ++colorIndex;
            }
        }
    }
}
