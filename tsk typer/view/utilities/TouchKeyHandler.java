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
    
    public abstract void keyTouched(TouchKeyMessage e);
    public abstract void keyTouchReleased(TouchKeyMessage e);

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
