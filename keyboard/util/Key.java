package keyboard.util;


public class Key 
{
    private char value;
    private char shiftValue;
	private boolean touched;
    private boolean pressed;


	public char getValue() 
	{
		return this.value;
	}

    public void setValue( final char value ) 
    {
        this.value = value;
    }

    public char getShiftedValue() 
    {
        return this.shiftValue;
    }

    public void setShiftedValue( final char shiftedValue ) 
    {
        this.shiftedValue = shiftedValue;
    }

    public boolean isTouched() {
        return this.touched;
    }

    public void setTouched( final boolean touched ) {
        this.touched = touched;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public void setPressed( final boolean pressed ) {
        this.pressed = pressed;
    }

    @Override
    public boolean equals( Object other ) 
    {
        if ( !(other instanceof Key) )return false;
        if ( other == this ) return true;        
        Key otherKey = (Key) other;
        return ( this.value == otherKey.getValue() && this.shiftedValue == otherKey.getShiftedValue() );
    }

    @Override
    public String toString() 
    {
        return String.valueOf( value );
    }

}