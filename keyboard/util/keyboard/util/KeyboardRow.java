package keyboard.util;

import keyboard.util.Key;

import java.util.List;
import java.util.ArrayList;

public class KeyboardRow 
{
	private List<Key> keys;
	
	public KeyboardRow() {
		this.keys = new ArrayList<Key>();
	}
	
	public KeyboardRow( List<Key> keys ) {
		this.keys = keys;
	}
	
	public List<Key> getKeys() 
	{
		return keys;
	}
	
	public void addKey(Key k) 
	{
		keys.add(k);
	}
}
