import static org.junit.Assert.*;
import tsk_typer.*;
import org.junit.Test;

public class SingleLevel
{

	@Test
	public void sanity_test()
    {
		try
        {
            TskTyperPresenter app = new TskTyperPresenter();
            app.initializeGame();
            app.startLevel();
		}
        catch ( Exception e )
        {
			e.printStackTrace();
		}
	}

}
