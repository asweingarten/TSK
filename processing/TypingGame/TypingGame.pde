// Example by Tom Igoe
import processing.serial.*;

String[] wordBank = { "hat", "potato", "tree", "goat" };
color backgroundColor = color( 235, 235, 235 );

String userInput = "";
int userInputSize  = 24;
color userInputColor  = color( 100, 100, 100 );

String targetWord = wordBank[0];
int targetWordSize = 36;
color targetWordColor = color( 125, 125, 125 );

String instructions = "Press ENTER to clear your input. Press DELETE to delete last entered character";
int instructionSize = 18;
color instructionColor = targetWordColor;


void setup()
{
  // The serial port:
  Serial myPort;

  // List all the available serial ports:
  println( Serial.list() );

  // Open the port you are using at the rate you want:
  // port is called: /dev/tty/usbmodem33181 on Ariel's MacBook
  //  myPort = new Serial(this, Serial.list()[1], 9600);

  size( 900, 600 );
}

void draw()
{
  // Init backgroun
  fill( backgroundColor );
  background( 50, 50, 50 );

  // Draw instructions
  fill( instructionColor );
  textSize( instructionSize );
  text( instructions, 15, height - instructionSize*2 );

  // Draw target word
  fill( targetWordColor );
  textSize( targetWordSize );
  text( targetWord, width/2 - 30, 2*targetWordSize );

  // Draw user's input
  fill( userInputColor );
  textSize( userInputSize );
  text( userInput, width/2, height/3 );
}

// The keyPressed() function is called once every time a key is pressed.
// The key that was pressed is stored in the key variable.
void keyPressed()
{

  if ( key == RETURN || key == ENTER )
  {
    userInput = "";
    return;
  }
  else if ( key == DELETE || key == BACKSPACE )
  {
    if ( userInput.length() > 0 )
    {
      userInput = userInput.substring( 0, userInput.length()-1 );
    }
    return;
  }

  println( key );
  userInput += key;

  if ( userInput.length() == targetWord.length() )
  {
    if ( userInput.equals( targetWord ) )
    {
      userInputColor = color( 15, 230, 15 );
    }
    else
    {
      userInputColor = color( 230, 15, 15 );
    }
  }
  else
  {

  }


}

