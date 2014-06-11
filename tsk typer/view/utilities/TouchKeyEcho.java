import java.io.IOException;
import java.util.Enumeration;
import javax.comm.SerialPort;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;

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
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements())
        {
            CommPortIdentifier port = (CommPortIdentifier)portList.nextElement();
            if(port.getPortType() == CommPortIdentifier.PORT_SERIAL) 
            {
                if(port.getName().equals(targetPortName))
                {
                    try
                    {
                        serialPort = (SerialPort)port.open(this.toString(),500);
                    }
                    catch(PortInUseException e)
                    {
                        System.err.println(e);
                    }
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
            System.out.print(message.character);
        }
        
        public void keyTouchReleased(TouchKeyMessage message)
        {
            System.out.print(message.character);
        }
    }
}
