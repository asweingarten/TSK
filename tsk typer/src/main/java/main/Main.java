package main;

import tsk_typer.*;

import javax.swing.*;
import java.awt.*;

public class Main
{
	public static void main( String[] args )
	{
		TskTyperView gameView = new TskTyperView();
		JFrame jFrame = new JFrame( "Typing Game v0.0.2" );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.setSize( 800, 600 );
		gameView.setKeyboardFocus();
		jFrame.setContentPane( gameView );
		jFrame.setVisible( true );
	}
}