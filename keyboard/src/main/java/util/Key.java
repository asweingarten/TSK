package util;


public class Key 
{
    private char value;
    private char shiftValue;
    private double xScale;
    private double yScale;
	private boolean touched;
    private boolean pressed;

    public Key( char value, char shiftValue ) 
    {
        this.value = value;
        this.shiftValue = shiftValue;
        this.xScale = 1;
        this.yScale = 1;
    }
    
    public Key( char value, char shiftValue, double xScale, double yScale ) 
    {
        this.value = value;
        this.shiftValue = shiftValue;
        this.xScale = xScale;
        this.yScale = yScale;
    }


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

    public void setShiftedValue( final char shiftValue ) 
    {
        this.shiftValue = shiftValue;
    }

    public double getXScale() {
    	return xScale;
    }
    
    public void setXScale( double xScale ) {
    	this.xScale = xScale;
    }
    
    public double getYScale() {
    	return yScale;
    }
    
    public void setYScale( double yScale ) {
    	this.yScale = yScale;
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
        return ( this.value == otherKey.getValue() && this.shiftValue == otherKey.getShiftedValue() );
    }

    @Override
    public String toString() 
    {
        return String.valueOf( shiftValue );
    }
    
    @Override
    public int hashCode() 
    {
        return 17 + 31*value + 57*shiftValue;
    }

}
