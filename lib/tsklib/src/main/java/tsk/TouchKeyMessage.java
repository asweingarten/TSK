package tsk;

public class TouchKeyMessage
{
    public enum Type
    {
        TOUCH, RELEASE
    }

    protected char character;
    protected Type type;

    public TouchKeyMessage( char character, Type type )
    {
        this.character = character;
        this.type = type; 
    }
    
    public char getCharacter()
    {
        return character;
    }

    public Type getType()
    {
        return type;
    }
}
