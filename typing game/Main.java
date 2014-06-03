import view.*;

import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main( String[] args )
	{
		TypingGameView gameView = new TypingGameView();
		JFrame jFrame = new JFrame( "Typing Game v0.0.1" );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.setSize( 800, 600 );
		jFrame.setContentPane( gameView );
		jFrame.setVisible( true );

	}
}