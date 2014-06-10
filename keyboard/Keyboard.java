import keyboard.model.KeyboardModel;
import keyboard.view.SwingKeyboardView;
import keyboard.presenter.KeyboardPresenter;
import keyboard.util.Key;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class Keyboard {
	public static void main( String[] args )
	{
		final KeyboardModel model = new KeyboardModel();
		final KeyboardPresenter presenter = new KeyboardPresenter( model );
		final SwingKeyboardView keyboard = new SwingKeyboardView( presenter );
		JFrame jFrame = new JFrame( "Keyboard v0.0.1" );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.setSize( 800, 600 );
		jFrame.setContentPane( keyboard );
		jFrame.setVisible( true );
		keyboard.setFocusable(true);
		
		//This adds a listener to a timer, which will repaint the scene a number of times per second, based on the FPS
		ActionListener repainter = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				keyboard.repaint();
			}
		};
				
		Timer t = new Timer(1000/30, repainter);
		t.start();
	}
}
