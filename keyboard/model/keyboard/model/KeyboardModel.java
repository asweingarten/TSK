package keyboard.model;


import keyboard.util.Key;
import keyboard.util.KeyboardRow;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

// Make this abstract, or turn into an interface?
public class KeyboardModel 
{
	private List<KeyboardRow> keyboardRows;
	private LinkedHashMap<Character, Key> test;
    private boolean shift;
	
	public KeyboardModel() 
	{
    	keyboardRows = new ArrayList<KeyboardRow>();
    	
    	KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addKey( new Key('`','~') );
        keyboardRow.addKey( new Key('1','!') );
        keyboardRow.addKey( new Key('2','@') );
        keyboardRow.addKey( new Key('3','#') );
        keyboardRow.addKey( new Key('4','$') );
        keyboardRow.addKey( new Key('5','%') );
        keyboardRow.addKey( new Key('6','^') );
        keyboardRow.addKey( new Key('7','&') );
        keyboardRow.addKey( new Key('8','*') );
        keyboardRow.addKey( new Key('9','(') );
        keyboardRow.addKey( new Key('0',')') );
        keyboardRow.addKey( new Key('-','_') );
        keyboardRow.addKey( new Key('=','+') );
        keyboardRows.add( keyboardRow );
        
        keyboardRow = new KeyboardRow();
        keyboardRow.addKey( new Key('q','Q') );
        keyboardRow.addKey( new Key('w','W') );
        keyboardRow.addKey( new Key('e','E') );
        keyboardRow.addKey( new Key('r','R') );
        keyboardRow.addKey( new Key('t','T') );
        keyboardRow.addKey( new Key('y','Y') );
        keyboardRow.addKey( new Key('u','U') );
        keyboardRow.addKey( new Key('i','I') );
        keyboardRow.addKey( new Key('o','O') );
        keyboardRow.addKey( new Key('p','P') );
        keyboardRow.addKey( new Key('[','{') );
        keyboardRow.addKey( new Key(']','}') );
        keyboardRow.addKey( new Key('\\','|') );
        keyboardRows.add( keyboardRow );
        
        keyboardRow = new KeyboardRow();
        keyboardRow.addKey( new Key('a','A') );
        keyboardRow.addKey( new Key('s','S') );
        keyboardRow.addKey( new Key('d','D') );
        keyboardRow.addKey( new Key('f','F') );
        keyboardRow.addKey( new Key('g','G') );
        keyboardRow.addKey( new Key('h','H') );
        keyboardRow.addKey( new Key('j','J') );
        keyboardRow.addKey( new Key('k','K') );
        keyboardRow.addKey( new Key('l','L') );
        keyboardRow.addKey( new Key(';',':') );
        keyboardRow.addKey( new Key('\'','"') );
        keyboardRow.addKey( new Key('E','E') );
        keyboardRows.add( keyboardRow );
        
        keyboardRow = new KeyboardRow();
        keyboardRow.addKey( new Key('z','Z') );
        keyboardRow.addKey( new Key('x','X') );
        keyboardRow.addKey( new Key('c','C') );
        keyboardRow.addKey( new Key('v','V') );
        keyboardRow.addKey( new Key('b','B') );
        keyboardRow.addKey( new Key('n','N') );
        keyboardRow.addKey( new Key('m','M') );
        keyboardRow.addKey( new Key(',','<') );
        keyboardRow.addKey( new Key('.','>') );
        keyboardRow.addKey( new Key('/','?') );
        keyboardRow.addKey( new Key('S','S') );
        keyboardRows.add( keyboardRow );
        
        keyboardRow = new KeyboardRow();
        Key k =  new Key( ' ',' ', 4.0, 1.0 );
        keyboardRow.addKey(k);
        test = new LinkedHashMap<Character, Key>();
        test.put(new Character('S'), k);
        
        keyboardRows.add( keyboardRow );
        
        
        //TODO: As a backup - this model should create a default regular keyboard layout
        //		Other models will load the keyboard from a config (XML?) file, using loadKeyboardFromXML()
        // 		We also need to add each of the keys to a hashmap to make the key pressed lookup faster (O(1) vs O(n) current)
	}

    public KeyboardModel( List<KeyboardRow> rows ) 
    {
        this.keyboardRows = rows;
    }
    
    public void loadKeyboardFromXML( String filename )
    {
    	// Do necessary XML parsing to get this 
    }
    
    public List<KeyboardRow> getKeyboardRows() 
    {
    	return keyboardRows;
    }

    public boolean isShifted()
    {
        return shift;
    }

    public void setShifted( boolean shift ) 
    {
        this.shift = shift;
    }
    
	//TODO: Change this to use a hashmap lookup of keys
    public void setPressed( char keyValue, boolean pressed ) 
    {
    	for ( KeyboardRow row : keyboardRows ) 
    	{
    		for ( Key key : row.getKeys() ) 
    		{
    			if ( key.getValue() == keyValue || key.getShiftedValue() == keyValue )
        		{
        			key.setPressed( pressed );
        			break;
        		}
    		}
    	}
    	
    }
    
	//TODO: Change this to use a hashmap lookup of keys
    public void setTouched( char keyValue, boolean touched ) 
    {
    	for ( KeyboardRow row : keyboardRows ) 
    	{
    		for ( Key key : row.getKeys() ) 
    		{
    			if ( key.getValue() == keyValue || key.getShiftedValue() == keyValue )
        		{
    				key.setTouched( touched );
        			break;
        		}
    		}
    	}
    }
    
    public void keyPressed( Key key )
    {
    	
    }
}