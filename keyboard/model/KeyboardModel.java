package keyboard.model;

import keyboard.util.Key;

import java.util.Array;
import java.util.ArrayList;

// Make this abstract, or turn into an interface?
public class KeyboardModel 
{

    private boolean shift;

    Array<Key> keys = new ArrayList<Key>();
    public KeyboardModel() 
    {
        //Initialize all keys, or allow these to be configured?

    }

    public boolean isShifted()
    {
        return shift;
    }

    public void setShifted( boolean shift ) 
    {
        this.shift = shift;
    }
}