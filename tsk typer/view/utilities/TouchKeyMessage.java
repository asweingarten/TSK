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
}
