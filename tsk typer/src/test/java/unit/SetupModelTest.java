import static org.junit.Assert.*;
import setup_screen.*;
import org.junit.Test;

public class SetupModelTest
{
    private SetupModel getSetupModel()
    {
        return new SetupModel( System.getProperty( "user.dir" ) );
    }

    @Test
    public void formattedTextTest()
    {
        String input = "  \nhello\n\tworld    !?!   \n\n !  ";
        String output = "hello world !?! !";
        SetupModel model = getSetupModel();
        model.setText( input );
        assertEquals( output, model.getFormattedText() );
    }
}
