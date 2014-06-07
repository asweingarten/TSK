package keyboard.presenter;

import keyboard.model.KeyboardModel;

import keyboard.view.KeyboardView;

import keyboard.util.Key;
import keyboard.util.KeyLocation;

public class KeyboardPresenter 
{

    private KeyboardModel model;

    public KeyboardPresenter( final KeyboardModel model ) 
    {
        this.model = model;
        // Initialize state from the model, getting the set of keys.
        // May need to initialize the positioning of each keys here.
    }

    public Map<Key, KeyLocation> getKeyMappings() 
    {

    }
}