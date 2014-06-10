import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

public abstract class TouchKeyHandler implements TouchKeyListener
{
    public TouchKeyStream inStream;
    public TouchKeyHandler(SerialPort port) throws IOException
    {
        inStream = new TouchKeyStream(port);
    }
    
    public abstract void keyTouched(KeyEvent e);
    public abstract void keyTouchReleased(KeyEvent e);

    public void serialEvent(SerialPortEvent e)
    {
        if(e.getEventType() == e.DATA_AVAILABLE)
        {
            TouchKeyMessage message = null;
            try
            {
                message = inStream.getNextMessage();
            }
            catch(IOException ex)
            {
            }

            if(message.type == TouchKeyMessage.Type.TOUCH)
            {
                //Create KeyEvent and call keyTouched
            }
            else if(message.type == TouchKeyMessage.Type.RELEASE)
            {
                //Create KeyEvent and call keyTouchReleased
            }
        }
        else
        {
            //Handle error conditions here
        }
    }
}
