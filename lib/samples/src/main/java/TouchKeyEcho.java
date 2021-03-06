import java.io.IOException;
import java.util.Enumeration;
import jssc.SerialPortException;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;
import jssc.SerialPort;
import tsk.*;

public class TouchKeyEcho
{
    private final String targetPortName = "COM2";
    private SerialPort serialPort = null;

    public static void main(String[] args)
    {
        TouchKeyEcho touchKeyEcho = new TouchKeyEcho();
    }

    public TouchKeyEcho()
    {
        //Find serial port
        String[] portNames = SerialPortList.getPortNames();
        for(String portName : portNames)
        {
            //Don't worry about target port right now, will assume first port found is correct
            if(true/*portName.equals(targetPortName)*/)
            {
                try
                {
                    serialPort = new SerialPort(portName);
                    serialPort.openPort();
                    serialPort.setParams(9600, 8, 1, 0);
                }
                catch(SerialPortException e)
                {
                    System.err.println(e);
                }
            }
        }
        try
        {
            //Create TouchKeyEchoHandler
            TouchKeyEchoHandler touchKeyEchoHandler = new TouchKeyEchoHandler(serialPort);
            //Add TouchKeyEchoHandler as event lister to serial port
            serialPort.addEventListener(touchKeyEchoHandler);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
    }

    public class TouchKeyEchoHandler extends TouchKeyHandler
    {
        public TouchKeyEchoHandler(SerialPort port) throws IOException
        {
            super(port);
        }

        public void keyTouched(TouchKeyMessage message)
        {
            System.out.print(message.getCharacter());
        }
        
        public void keyTouchReleased(TouchKeyMessage message)
        {
            System.out.print(message.getCharacter());
        }
    }
}
