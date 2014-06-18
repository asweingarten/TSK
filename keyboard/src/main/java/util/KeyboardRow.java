package util;

import util.Key;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collection;

public class KeyboardRow 
{
	private Map<Character, Key> keyMappings;
	private Map<Character, Key> shiftedKeyMappings;
	private int offset = 0;
	
	public KeyboardRow() {
		this.keyMappings = new LinkedHashMap<Character, Key>();
		this.shiftedKeyMappings = new LinkedHashMap<Character, Key>();
	}
	
	public KeyboardRow(int rowOffset) {
		this.offset = rowOffset;
		this.keyMappings = new LinkedHashMap<Character, Key>();
		this.shiftedKeyMappings = new LinkedHashMap<Character, Key>();
	}
	
	public Collection<Key> getKeys() 
	{
		return keyMappings.values();
	}
	
	public void addKey(Key k) 
	{
		keyMappings.put( k.getValue(), k );
		shiftedKeyMappings.put( k.getShiftedValue(), k );
	}
	
	public boolean containsKey( Key k ) {
		return ( keyMappings.containsKey(k.getValue()) || shiftedKeyMappings.containsKey(k.getShiftedValue()) );
	}
	
	public Key getKey( Key k )
	{
		Key foundKey = keyMappings.get( k.getValue() );
		if ( foundKey == null ) 
		{
			foundKey = shiftedKeyMappings.get( k.getShiftedValue() );
		}  
		return foundKey;
	}
	
	public int getOffset() {
		return offset;
	}
	
}
