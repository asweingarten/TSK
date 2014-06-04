package keyboard.view;

import keyboard.presenter.KeyboardPresenter;

import some swing shit;

public abstract class KeyboarView 
{
    private KeyboardPresenter presenter; 
    public KeyboardView( final KeyboardPresenter presenter ) {
        this.presenter = presenter;
    }
}