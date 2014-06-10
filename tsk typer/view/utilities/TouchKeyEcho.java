import java.io.IOException;
import javax.comm.SerialPort;

public class TouchKeyEcho
{

    public TouchKeyEcho()
    {
        //Find serial port
        //Create TouchKeyEchoHandler
        //Add TouchKeyEchoHandler as event lister to serial port
    }

    public class TouchKeyEchoHandler extends TouchKeyHandler
    {
        public TouchKeyEchoHandler(SerialPort port) throws IOException
        {
            super(port);
        }

        public void keyTouched(TouchKeyMessage message)
        {
            System.out.print(message.character);
        }
        
        public void keyTouchReleased(TouchKeyMessage message)
        {
            System.out.print(message.character);
        }
    }
}
