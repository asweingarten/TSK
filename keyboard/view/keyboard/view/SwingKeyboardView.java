package keyboard.view;

import keyboard.presenter.KeyboardPresenter;
import keyboard.util.Key;
import keyboard.util.KeyboardRow;

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


		    List<KeyboardRow> keyboardRows = presenter.getKeyboardRows();
	    	int keyTop = 30;
	    	
		    for ( KeyboardRow row : keyboardRows ) 
		    {
				int keyWidth = presenter.getKeyWidth();
				int keyHeight = presenter.getKeyHeight();
		    	int keyLeft = 30;
		    	for ( Key key : row.getKeys() )
		    	{    
		    		if ( key.isTouched() ) { 
		    			context.setPaint( Color.GREEN );
		    		} else if ( key.isPressed() ) {
		    			context.setPaint( Color.RED );
		    		} else {
		    			context.setPaint ( Color.GRAY );
		    		}
		    		keyWidth = (int) Math.floor( keyWidth * key.getXScale() );
		    		keyHeight = (int) Math.floor( keyHeight * key.getYScale() );
			    	context.fillRect( keyLeft,
			    					  keyTop,
			    					  keyWidth,
			    					  keyHeight );
			    	context.setPaint( Color.BLACK );
			    	context.drawString( key.toString(), keyLeft + 5, keyTop + 15 );
			    	keyLeft += (keyWidth + 5);	    	
		    	}
		    	keyTop += (keyHeight + 5);
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
