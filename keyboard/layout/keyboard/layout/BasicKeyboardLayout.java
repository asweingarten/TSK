package keyboard.layout;

import keyboard.util.KeyLocation;
import keyboard.util.Key;

import java.util.HashMap;

public class BasicKeyboardLayout extends KeyboardLayout 
{

    public BasicKeyboardLayout() 
    {
    	keyMapping = new HashMap<Key, KeyLocation>();
        keyMapping.put( new Key('q','Q'), new KeyLocation( 0,0 ) );
        keyMapping.put( new Key('w','W'), new KeyLocation( 0,1 ) );
        keyMapping.put( new Key('e','E'), new KeyLocation( 0,2 ) );
        keyMapping.put( new Key('r','R'), new KeyLocation( 0,3 ) );
        keyMapping.put( new Key('t','T'), new KeyLocation( 0,4 ) );
        keyMapping.put( new Key('y','Y'), new KeyLocation( 0,5 ) );
    }
}