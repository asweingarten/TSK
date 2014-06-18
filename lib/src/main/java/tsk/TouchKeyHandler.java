package tsk;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener; 
import jssc.SerialPortException;

public abstract class TouchKeyHandler implements TouchKeyListener
{
    public TouchKeyStream inStream;
    public TouchKeyHandler(SerialPort port) 
    {
        inStream = new TouchKeyStream(port);
    }
    
    public abstract void keyTouched(TouchKeyMessage e);
    public abstract void keyTouchReleased(TouchKeyMessage e);

    public void serialEvent(SerialPortEvent e)
    {
        if(e.isRXCHAR())
        {
            TouchKeyMessage message = null;
            try
            {
                message = inStream.getNextMessage();
            }
            catch(SerialPortException|TouchKeyStream.TouchKeyException ex)
            {
                if(ex instanceof SerialPortException)
                {
                }
                else if(ex instanceof TouchKeyStream.TouchKeyException)
                {
                }
            }

            if(message.type == TouchKeyMessage.Type.TOUCH)
            {
                keyTouched(message);
            }
            else if(message.type == TouchKeyMessage.Type.RELEASE)
            {
                keyTouchReleased(message);
            }
        }
        else
        {
            //Handle error conditions here
        }
    }
}
