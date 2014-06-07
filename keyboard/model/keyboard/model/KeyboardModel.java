package keyboard.model;


import keyboard.util.Key;

import java.util.List;
import java.util.ArrayList;

// Make this abstract, or turn into an interface?
public class KeyboardModel 
{
	private List<Key> keys;
    private boolean shift;
	
	public KeyboardModel() 
	{
	    keys = new ArrayList<Key>();
	    keys.add( new Key('q','Q') );
	    keys.add( new Key('w','W') );
	    keys.add( new Key('e','E') );
	    keys.add( new Key('r','R') );
	    keys.add( new Key('t','T') );
	    keys.add( new Key('y','Y') );
	}

    public KeyboardModel( List<Key> keys ) 
    {
        this.keys = keys;
    }
    
    public List<Key> getKeys() 
    {
    	return keys;
    }

    public boolean isShifted()
    {
        return shift;
    }

    public void setShifted( boolean shift ) 
    {
        this.shift = shift;
    }
    
    public void setPressed( char keyValue, boolean pressed ) 
    {
    	for ( Key key : keys ) 
    	{
    		if ( key.getValue() == keyValue || key.getShiftedValue() == keyValue )
    		{
    			key.setPressed( pressed );
    			break;
    		}
    	}
    }
    
    public void setTouched( char keyValue, boolean touched ) 
    {
    	for ( Key key : keys ) 
    	{
    		if (key.getValue() == keyValue || key.getShiftedValue() == keyValue)
    		{
    			key.setTouched(true);
    			break;
    		}
    	}
    }
    
    public void keyPressed( Key key )
    {
    	
    }
}