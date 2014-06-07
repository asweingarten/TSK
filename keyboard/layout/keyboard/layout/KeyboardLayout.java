package keyboard.layout;

import keyboard.util.KeyLocation;
import keyboard.util.Key;

import java.util.Map;

public abstract class KeyboardLayout 
{
    protected Map<Key, KeyLocation> keyMapping;

    public Map<Key, KeyLocation> getKeyMapping() {
        return keyMapping;
    }
}