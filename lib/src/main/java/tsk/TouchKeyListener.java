package tsk;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;

public interface TouchKeyListener extends SerialPortEventListener
{
    /*
     * These calls will come from serialEvent, once a complete key touch/release 
     * message has been read seial event will build a KeyEvent and propogate it
     */
    void keyTouched(TouchKeyMessage e);
    void keyTouchReleased(TouchKeyMessage e);

    /*
     * TouchKeyListener will be added as a event listener on the serial port so
     * that we can be informed when new data is a available
     */
    void serialEvent(SerialPortEvent e);
}
