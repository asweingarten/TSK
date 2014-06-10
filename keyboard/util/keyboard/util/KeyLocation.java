package keyboard.util;

public class KeyLocation 
{
    private int row;
    private int col;

    public KeyLocation( int row, int col ) 
    {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return this.row; } 

    public int getColumn() { return this.col; }

}