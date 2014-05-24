#include "mpr121.h"
#include <Wire.h>

const int NUM_IRQ_PINS = 2,
          NUM_SENSORS_PER_BOARD = 12;
int *irq_pins = new int[NUM_IRQ_PINS];


boolean touch_states[NUM_IRQ_PINS][NUM_SENSORS_PER_BOARD];

struct Touch_Message
{
  bool is_key_touched,
       is_key_released;

  char  key_code;

  char *serialize()
  {
    char *serializedMessage = new char[2];
    char header = ( is_key_released << 6 ) & ( is_key_touched << 7 );

    serializedMessage[0] = header;
    serializedMessage[1] = key_code;
    return serializedMessage;
  }

};

void setup(){

  irq_pins[0] = 2; // Digital 2
  irq_pins[1] = 5; // Digital 5

  int irq_pin;
  for ( int i = 0; i < NUM_IRQ_PINS; i++ )
  {
    irq_pin = irq_pins[i];
    pinMode( irq_pin, INPUT );
    digitalWrite( irq_pin, HIGH );
  }

  Serial.begin(9600);  // 9600 Baud
  Wire.begin();

  mpr121_setup();
}

void loop()
{
  readTouchInputs();
}

void readTouchSensor( int index, int irq_pin )
{
  if( !checkInterrupt( irq_pin ) )
  {

    //read the touch state from the proper MPR121
//    if ( irq_pin == 2 )
//    {
//      // Pin 2 -- Breakboard A
//      Wire.requestFrom( 0x5C, 2 );
//    }
//    else if ( irq_pin == 5 )
//    {
//      // Pin 5 -- Breakout Board B
//      Wire.requestFrom( 0x5A, 2 );
//    }

    Wire.requestFrom( 0x5A, 2 );

    byte LSB = Wire.read();
    byte MSB = Wire.read();

    uint16_t touched = ((MSB << 8) | LSB); //16bits that make up the touch states


    for ( int i=0; i < 12; i++ )
    {  // Check what electrodes were pressed
      if( touched & (1<<i) )
      {

        if( touch_states[index][i] == 0 ) {
          //pin i was just touched
          struct Touch_Message msg;
          msg.is_key_released = false;
          msg.is_key_touched  = true;
          msg.key_code        = i + 'a';
          Serial.write( msg.serialize(), 2 );
          // Serial.print("pin ");
          // Serial.print(i);
          // Serial.println(" was just touched");

        }
        else if( touch_states[index][i] == 1 )
        {
          //pin i is still being touched
        }

        touch_states[index][i] = 1;
      }
      else
      {

        if( touch_states[index][i] == 1 )
        {
          // Serial.print("pin ");
          // Serial.print(i);
          // Serial.println(" is no longer being touched");
          struct Touch_Message msg;
          msg.is_key_released = true;
          msg.is_key_touched  = true;
          msg.key_code        = i + 'a';
          Serial.write( msg.serialize(), 2 );
          /*

                touch/press bit : on/off bit : key
                one bit         : one bit    : one byte
            eg

          */

          //pin i is no longer being touched
       }

        touch_states[index][i] = 0;
      }

    }

  }
}

void readTouchInputs()
{
  for ( int i = 0; i < NUM_IRQ_PINS; i++ )
  {
    readTouchSensor( i, irq_pins[i] );
  }
}


boolean checkInterrupt( int irq_pin )
{
  return digitalRead( irq_pin );
}

void mpr121_setup(void){

  // set_register(0x5C, ELE_CFG, 0x00);

  // // Section A - Controls filtering when data is > baseline.
  // set_register(0x5C, MHD_R, 0x01);
  // set_register(0x5C, NHD_R, 0x01);
  // set_register(0x5C, NCL_R, 0x00);
  // set_register(0x5C, FDL_R, 0x00);

  // // Section B - Controls filtering when data is < baseline.
  // set_register(0x5C, MHD_F, 0x01);
  // set_register(0x5C, NHD_F, 0x01);
  // set_register(0x5C, NCL_F, 0xFF);
  // set_register(0x5C, FDL_F, 0x02);

  // // Section C - Sets touch and release thresholds for each electrode
  // set_register(0x5C, ELE0_T, TOU_THRESH);
  // set_register(0x5C, ELE0_R, REL_THRESH);

  // set_register(0x5C, ELE1_T, TOU_THRESH);
  // set_register(0x5C, ELE1_R, REL_THRESH);

  // set_register(0x5C, ELE2_T, TOU_THRESH);
  // set_register(0x5C, ELE2_R, REL_THRESH);

  // set_register(0x5C, ELE3_T, TOU_THRESH);
  // set_register(0x5C, ELE3_R, REL_THRESH);

  // set_register(0x5C, ELE4_T, TOU_THRESH);
  // set_register(0x5C, ELE4_R, REL_THRESH);

  // set_register(0x5C, ELE5_T, TOU_THRESH);
  // set_register(0x5C, ELE5_R, REL_THRESH);

  // set_register(0x5C, ELE6_T, TOU_THRESH);
  // set_register(0x5C, ELE6_R, REL_THRESH);

  // set_register(0x5C, ELE7_T, TOU_THRESH);
  // set_register(0x5C, ELE7_R, REL_THRESH);

  // set_register(0x5C, ELE8_T, TOU_THRESH);
  // set_register(0x5C, ELE8_R, REL_THRESH);

  // set_register(0x5C, ELE9_T, TOU_THRESH);
  // set_register(0x5C, ELE9_R, REL_THRESH);

  // set_register(0x5C, ELE10_T, TOU_THRESH);
  // set_register(0x5C, ELE10_R, REL_THRESH);

  // set_register(0x5C, ELE11_T, TOU_THRESH);
  // set_register(0x5C, ELE11_R, REL_THRESH);

  // // Section D
  // // Set the Filter Configuration
  // // Set ESI2
  // set_register(0x5C, FIL_CFG, 0x04);

  // // Section E
  // // Electrode Configuration
  // // Set ELE_CFG to 0x00 to return to standby mode
  // set_register(0x5C, ELE_CFG, 0x0C);  // Enables all 12 Electrodes


  // // Section F
  // // Enable Auto Config and auto Reconfig
  // /*set_register(0x5C, ATO_CFG0, 0x0B);
  // set_register(0x5C, ATO_CFGU, 0xC9);  // USL = (Vdd-0.7)/vdd*256 = 0xC9 @3.3V   set_register(0x5C, ATO_CFGL, 0x82);  // LSL = 0.65*USL = 0x82 @3.3V
  // set_register(0x5C, ATO_CFGT, 0xB5);*/  // Target = 0.9*USL = 0xB5 @3.3V

  // set_register(0x5C, ELE_CFG, 0x0C);


  set_register(0x5A, ELE_CFG, 0x00);

  // Section A - Controls filtering when data is > baseline.
  set_register(0x5A, MHD_R, 0x01);
  set_register(0x5A, NHD_R, 0x01);
  set_register(0x5A, NCL_R, 0x00);
  set_register(0x5A, FDL_R, 0x00);

  // Section B - Controls filtering when data is < baseline.
  set_register(0x5A, MHD_F, 0x01);
  set_register(0x5A, NHD_F, 0x01);
  set_register(0x5A, NCL_F, 0xFF);
  set_register(0x5A, FDL_F, 0x02);

  // Section C - Sets touch and release thresholds for each electrode
  set_register(0x5A, ELE0_T, TOU_THRESH);
  set_register(0x5A, ELE0_R, REL_THRESH);

  set_register(0x5A, ELE1_T, TOU_THRESH);
  set_register(0x5A, ELE1_R, REL_THRESH);

  set_register(0x5A, ELE2_T, TOU_THRESH);
  set_register(0x5A, ELE2_R, REL_THRESH);

  set_register(0x5A, ELE3_T, TOU_THRESH);
  set_register(0x5A, ELE3_R, REL_THRESH);

  set_register(0x5A, ELE4_T, TOU_THRESH);
  set_register(0x5A, ELE4_R, REL_THRESH);

  set_register(0x5A, ELE5_T, TOU_THRESH);
  set_register(0x5A, ELE5_R, REL_THRESH);

  set_register(0x5A, ELE6_T, TOU_THRESH);
  set_register(0x5A, ELE6_R, REL_THRESH);

  set_register(0x5A, ELE7_T, TOU_THRESH);
  set_register(0x5A, ELE7_R, REL_THRESH);

  set_register(0x5A, ELE8_T, TOU_THRESH);
  set_register(0x5A, ELE8_R, REL_THRESH);

  set_register(0x5A, ELE9_T, TOU_THRESH);
  set_register(0x5A, ELE9_R, REL_THRESH);

  set_register(0x5A, ELE10_T, TOU_THRESH);
  set_register(0x5A, ELE10_R, REL_THRESH);

  set_register(0x5A, ELE11_T, TOU_THRESH);
  set_register(0x5A, ELE11_R, REL_THRESH);

  // Section D
  // Set the Filter Configuration
  // Set ESI2
  set_register(0x5A, FIL_CFG, 0x04);

  // Section E
  // Electrode Configuration
  // Set ELE_CFG to 0x00 to return to standby mode
  set_register(0x5A, ELE_CFG, 0x0C);  // Enables all 12 Electrodes


  // Section F
  // Enable Auto Config and auto Reconfig
  /*set_register(0x5A, ATO_CFG0, 0x0B);
  set_register(0x5A, ATO_CFGU, 0xC9);  // USL = (Vdd-0.7)/vdd*256 = 0xC9 @3.3V   set_register(0x5A, ATO_CFGL, 0x82);  // LSL = 0.65*USL = 0x82 @3.3V
  set_register(0x5A, ATO_CFGT, 0xB5);*/  // Target = 0.9*USL = 0xB5 @3.3V

  set_register(0x5A, ELE_CFG, 0x0C);

}

void set_register(int address, unsigned char r, unsigned char v)
{
    Wire.beginTransmission(address);
    Wire.write(r);
    Wire.write(v);
    Wire.endTransmission();
}
