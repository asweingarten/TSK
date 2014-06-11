import java.io.BufferedInputStream;
import java.io.IOException;
import javax.comm.SerialPort;

public class TouchKeyStream extends BufferedInputStream
{
    private static final int MESSAGE_LENGTH = 2;
    private static final int VALID_HEADER_LOW = 0;
    private static final int VALID_HEADER_HIGH = 1;
    private static final int VALID_CHARACTER_LOW = 2;
    private static final int VALID_CHARACTER_HIGH = 127;
    public class TouchKeyException extends Exception
    {
        public TouchKeyException()
        {
            super();
        }

        public TouchKeyException(String message)
        {
            super(message);
        }
    }

    public TouchKeyStream( SerialPort serialPort ) throws IOException
    {
        super(serialPort.getInputStream());
    }

    public TouchKeyMessage getNextMessage() throws IOException, TouchKeyException
    {
        TouchKeyMessage message = null;
        TouchKeyMessage.Type type = null;
        char character = ' ';
        byte[] buffer = new byte[MESSAGE_LENGTH];

        //Mark pre-read buffer position incase we need to back up this read
        //Becomes invalid after we read more than one full message
        mark(MESSAGE_LENGTH);
        int bytesRead = read(buffer, 0, MESSAGE_LENGTH);
        if(bytesRead != MESSAGE_LENGTH)
        {
            if(bytesRead == -1)
            {
                throw new TouchKeyException("Input stream reached EOF");
                //Have reached EOF
                //We should throw an error here or return a special message type
                
            }
            if(bytesRead % 2 == 1)
            {
                throw new TouchKeyException("Odd number of bytes read");
                //If we read an odd number of bytes we are probably offset in the buffer
            }
        }

        int byteOne = (int)buffer[0];
        int byteTwo = (int)buffer[1];

        //Check if first byte is a header
        if( byteOne >= VALID_HEADER_LOW && byteOne <= VALID_HEADER_HIGH )
        {
            if(byteOne == 1)
            {
                type = TouchKeyMessage.Type.TOUCH;
            }
            else if(byteOne == 0)
            {
                type = TouchKeyMessage.Type.RELEASE;
            }
        }
        else
        {
            reset();
            read();
            throw new TouchKeyException("First byte was not a vaild header as expected");
        }
        
        if( byteTwo >= VALID_CHARACTER_LOW && byteTwo <= VALID_CHARACTER_HIGH )
        {
            character = (char)buffer[1];
        }
        else {
            //If Byte two is not a valid character but is a vaild header then perhaps we are just offset
            if(type == null && byteTwo >= VALID_HEADER_LOW && byteTwo <= VALID_HEADER_HIGH)    
            {
                reset();
                read();
                throw new TouchKeyException("Second byte was not a valid character as expected");
            }
        }
        message = new TouchKeyMessage(character,type);
        return message;
    } 
}
