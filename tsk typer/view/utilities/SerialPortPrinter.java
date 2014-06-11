import java.io.IOException;
import java.util.Enumeration;
import javax.comm.SerialPort;
import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
public class SerialPortPrinter 
{
    public static void main(String[] args)
    {
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements())
        {
            CommPortIdentifier port = (CommPortIdentifier)portList.nextElement();
            if(port.getPortType() == CommPortIdentifier.PORT_SERIAL) 
            {
                System.out.println(port.getName());
            }
        }
    }

}
