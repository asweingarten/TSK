// Example by Tom Igoe
import processing.serial.*;

// The serial port:
Serial myPort;

// List all the available serial ports:
println( Serial.list(); );

// Open the port you are using at the rate you want:
// port is called: /dev/tty/usbmodem33181 on Ariel's MacBook
myPort = new Serial(this, Serial.list()[1], 9600);
  
// Send a capital A out the serial port:
//myPort.write(65);
