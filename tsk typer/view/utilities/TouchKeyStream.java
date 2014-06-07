import java.io.BufferedInputStream;
import java.io.IOException;
import javax.comm.SerialPort;

public class TouchKeyStream extends BufferedInputStream
{
    public enum Type
    {
        TOUCH, RELEASE
    }
    private static final int VALID_HEADER_LOW = 0;
    private static final int VALID_HEADER_HIGH = 1;
    private static final int VALID_CHARACTER_LOW = 2;
    private static final int VALID_CHARACTER_HIGH = 127;

    public class TouchKeyMessage
    {
        protected char character;
        protected Type type;

        public TouchKeyMessage( char character, Type type )
        {
            this.character = character;
            this.type = type;
        }
    }

    public TouchKeyStream( SerialPort serialPort ) throws IOException
    {
        super(serialPort.getInputStream());
        //InputStream serialInputStream = ;
        reset();
    }

    public TouchKeyMessage getNextMessage() throws IOException
    {
        //Need two bytes for a message
        int byteOne = read();
        int byteTwo = read(); 

        Type type = null;
        char message;

        //Check if first byte is a header
        if( byteOne >= VALID_HEADER_LOW && byteOne <= VALID_HEADER_HIGH )
        {
            if(byteOne == 1)
            {
                type = Type.TOUCH;
            }
            else if(byteOne == 0)
            {
                type = Type.RELEASE;
            }
        }
        else
        {
            reset();
            return null;
        }

        if( byteTwo >= VALID_CHARACTER_LOW && byteTwo <= VALID_CHARACTER_HIGH )
        {
            message = (char)byteTwo;
        }
        else
        {
            reset();
            return null;
        }

        return new TouchKeyMessage(message,type);
    } 
}
