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

import jssc.SerialPortException;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import jssc.SerialPort;

import tsk.*;

public class SwingKeyboardView extends JComponent implements KeyboardView
{
	private KeyboardPresenter presenter;
	private Graphics2D context = null;
	private SerialPort serialPort = null;
	
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
	
		// This assumes that only one active serial port exists
		String[] portNames = SerialPortList.getPortNames();
		for (String portName : portNames) 
		{
			System.out.println("Port Found");
			try 
			{
				this.serialPort = new SerialPort(portName);
				this.serialPort.openPort();
				this.serialPort.setParams(9600, 8, 1, 0);
				break;

			} 
			catch (SerialPortException e) 
			{

			}
		}

		try {
			serialPort.addEventListener( new TouchKeyHandler(serialPort) { 
				public void keyTouched( TouchKeyMessage msg ) 
				{
					pressed ( msg.getCharacter() );
				}

				public void keyTouchReleased( TouchKeyMessage msg ) 
				{
					released( msg.getCharacter() );
				}
					

			});

		} catch (SerialPortException e) {
			System.err.println(" Failed to add listener ");
		} 

	}
	
	/**
	 * Method to draw an individual key, passing in the key itself, and dimension/location info
	 * @param key
	 * @param keyLeft
	 * @param keyTop
	 * @param keyWidth
	 * @param keyHeight
	 */
	private void drawKey(Key key, int keyLeft, int keyTop, int keyWidth, int keyHeight) 
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
	
	private void pressed( char c ) {
		presenter.keyPressed(c);
	}
	
	private void released( char c ) {
		presenter.keyReleased(c);
	}
}
