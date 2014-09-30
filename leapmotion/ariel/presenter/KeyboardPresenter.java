package presenter;

import model.KeyboardModel;
import util.Key;
import util.KeyboardRow;

import java.util.Map;
import java.util.List;

public class KeyboardPresenter 
{

    private KeyboardModel model;

    public KeyboardPresenter( final KeyboardModel model ) 
    {
        this.model = model;
        // Initialize state from the model, getting the set of keys.
        // May need to initialize the positioning of each keys here.
    }

    public List<KeyboardRow> getKeyboardRows() 
    {
    	return model.getKeyboardRows();
    }
    
    public int getKeyWidth() 
    {
    	return model.getKeyWidth();
    }
    
    public int getKeyHeight() 
    {
    	return model.getKeyHeight();
    }
    
    public int getKeySpacing()
    {
    	return model.getKeySpacing();
    }
    
    public int getLeft() 
    {
    	return 30;
    }
    
    public int getTop() 
    {
    	return 30;
    }
    
    public void keyPressed( char keyValue ) {
    	if ( keyValue == 15 ) 
    	{
    		model.setShifted( true );
    	} 
    	model.setPressed( keyValue, true );
    }
    
    public void keyReleased( char keyValue ) {
    	if ( keyValue == 15 ) 
    	{
    		model.setShifted( false );
    	}
    	model.setPressed( keyValue, false );
    }

    public void keyTouched( char keyValue ) {
        model.setTouched( keyValue, true );
    }
    
    public void keyTouchReleased( char keyValue ) {
        model.setTouched( keyValue, false );
    }
}
