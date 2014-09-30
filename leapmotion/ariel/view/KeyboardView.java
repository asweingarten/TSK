package view;

import presenter.KeyboardPresenter;
import util.Key;
import util.KeyboardRow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.List;
import java.util.Map;

public class KeyboardView extends JComponent
{
    private KeyboardPresenter presenter;
    private Graphics2D context = null;

    public KeyboardView( KeyboardPresenter presenter )
    {
        this.presenter = presenter;
        
        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e)
            {
                pressed( e.getKeyChar() );
                System.out.println("Key pressed");
            }

            public void keyReleased(KeyEvent e)
            {
                released( e.getKeyChar() );
                System.out.println("Key released");
            }

            public void keyTyped(KeyEvent e) {}
        });

    }

    /**
     * Method to draw an individual key, passing in the key itself, and dimension/location info
     * @param key
     * @param keyLeft
     * @param keyTop
     * @param keyWidth
     * @param keyHeight
     */
    private void drawKey( Key key, int keyLeft, int keyTop, int keyWidth, int keyHeight )
    {
        if ( key.isTouched() ) 
        { 
            context.setPaint( Color.GREEN );
        } 
        else if ( key.isPressed() ) 
        {
            context.setPaint( Color.RED );
        } 
        else 
        {
            context.setPaint ( Color.GRAY );
        }
        keyWidth = (int) Math.floor( keyWidth * key.getXScale() );
        keyHeight = (int) Math.floor( keyHeight * key.getYScale() );
        context.fillRect( keyLeft, keyTop, keyWidth, keyHeight );
        context.setPaint( Color.BLACK );
        context.drawString( key.toString(), keyLeft + 5, keyTop + 15 );
    }

    public void draw()
    {
        if ( context != null )
        {
            int keyboardLeft = presenter.getLeft();
            int keyboardTop = presenter.getTop();
            int keySpacing = presenter.getKeySpacing();

            List<KeyboardRow> keyboardRows = presenter.getKeyboardRows();
            int keyTop = keyboardLeft;

            for ( KeyboardRow row : keyboardRows )
            {
                int keyWidth = presenter.getKeyWidth();
                int keyHeight = presenter.getKeyHeight();
                int keyLeft = keyboardLeft + row.getOffset();
                for ( Key key : row.getKeys() )
                {
                    drawKey(key, keyLeft, keyTop, keyWidth, keyHeight);
                    keyLeft += (keyWidth + keySpacing);
                }
                keyTop += (keyHeight + keySpacing);
            }

        }
    }

    public void paintComponent( Graphics g )
    {
        this.context = ( Graphics2D ) g;
        draw();
    }

    private void pressed( char c ) 
    {
        presenter.keyPressed(c);
    }

    private void released( char c ) 
    {
        presenter.keyReleased(c);
    }

    private void touched( char c ) 
    {
        presenter.keyTouched(c);
    }
    
    private void touchReleased( char c ) 
    {
        presenter.keyTouchReleased(c);
    }
}
