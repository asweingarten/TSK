package tsk;

import java.io.BufferedInputStream;
import jssc.SerialPort;
import jssc.SerialPortException;

public class TouchKeyStream
{
    private static final int MESSAGE_LENGTH = 2;
    private static final int VALID_HEADER_LOW = 0;
    private static final int VALID_HEADER_HIGH = 1;
    private static final int VALID_CHARACTER_LOW = 2;
    private static final int VALID_CHARACTER_HIGH = 127;
 
    private SerialPort serialPort;

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

    public TouchKeyStream( SerialPort serialPort ) 
    {
        this.serialPort = serialPort;
    }

    public TouchKeyMessage getNextMessage() throws SerialPortException, TouchKeyException
    {
        TouchKeyMessage message = null;
        TouchKeyMessage.Type type = null;
        char character = ' ';
        byte[] buffer = new byte[MESSAGE_LENGTH];

        //Mark pre-read buffer position incase we need to back up this read
        //Becomes invalid after we read more than one full message
        if(serialPort.getInputBufferBytesCount() >= MESSAGE_LENGTH)
        {
            buffer = serialPort.readBytes(MESSAGE_LENGTH);
        }
        else
        {
            serialPort.purgePort( SerialPort.PURGE_RXCLEAR | SerialPort.PURGE_TXCLEAR );
            throw new TouchKeyException("Less than a message is in the buffer");
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
                throw new TouchKeyException("Second byte was not a valid character as expected");
            }
        }
        message = new TouchKeyMessage(character,type);
        return message;
    } 
}
