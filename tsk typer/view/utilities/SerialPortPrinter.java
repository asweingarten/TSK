import java.io.IOException;
import java.util.Enumeration;
import jssc.SerialPortList;
public class SerialPortPrinter 
{
    public static void main(String[] args)
    {
        String[] portNames = SerialPortList.getPortNames(); 
        for(String portName : portNames)
        {
            System.out.println(portName);
        }
    }

}
