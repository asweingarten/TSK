import java.io.IOException;
import java.util.Enumeration;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import jssc.SerialNativeInterface;

public class SerialPortPrinter 
{
    public static void main(String[] args)
    {
        //System.out.println(SerialNativeInterface.getOSType());
        String[] portNames = SerialPortList.getPortNames(); 
        System.out.println("Num name = " + portNames.length);
        for(String portName : portNames)
        {
            System.out.println(portName);
            SerialPort port = new SerialPort(portName);
            try
            {
                port.openPort();
            }
            catch(SerialPortException e)
            {
                System.err.println("Could not open port");
            }
        }
        System.out.println("Done");
    }

}
