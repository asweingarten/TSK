package keyboard.view;

import keyboard.presenter.KeyboardPresenter;
import keyboard.util.Key;
import keyboard.util.KeyLocation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import java.util.List;
import java.util.Map;

public class SwingKeyboardView extends JComponent implements KeyboardView
{
	private KeyboardPresenter presenter;
	private Graphics2D context = null;
	
	public SwingKeyboardView( KeyboardPresenter presenter ) 
	{
		this.presenter = presenter;
		
		addKeyListener(new KeyListener() {
		    public void keyPressed(KeyEvent e) 
		    {
		    	pressed( e.getKeyChar() );
		    }

		    public void keyReleased(KeyEvent e) 
		    {
		    	released( e.getKeyChar() );
		    }

		    public void keyTyped(KeyEvent e) {}
		});
		
	}

	public void draw() 
	{
		if ( context != null ) 
		{
			int keyboardLeft = presenter.getLeft();
			int keyboardTop = presenter.getTop(); 
			int keyWidth = presenter.getKeyWidth();
			int keyHeight = presenter.getKeyHeight();
				    
		    List<Key> keys = presenter.getKeys();
		    Map<Key, KeyLocation> keyMapping = presenter.getKeyMapping();
		    for ( Key key : keys ) 
		    {
		    	KeyLocation location = keyMapping.get(key);
		    	if (location != null) 
		    	{
		    		int keyLeft = keyboardLeft + location.getColumn()*(keyWidth + 5);
		    		int keyTop = keyboardTop + location.getRow()*(keyHeight + 5);
			    
		    		if ( key.isTouched() ) { 
		    			context.setPaint( Color.GREEN );
		    		} else if ( key.isPressed() ) {
		    			context.setPaint( Color.RED );
		    		} else {
		    			context.setPaint ( Color.GRAY );
		    		}
			    	context.fillRect( keyLeft,
			    					  keyTop,
			    					  keyWidth,
			    					  keyHeight );
			    	context.setPaint( Color.BLACK );
			    	context.drawString( key.toString(), keyLeft + 5, keyTop + 15 );
		    	}
		    }
		    
		}
	}
	
	public void paintComponent( Graphics g )
	{
	    this.context = ( Graphics2D ) g;
	    draw();
	}
	
	private void pressed( char c ) {
		presenter.keyPressed(c);
	}
	
	private void released( char c ) {
		presenter.keyReleased(c);
	}
}
