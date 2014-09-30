import java.io.IOException;
import java.lang.Math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

import view.*;
import presenter.*;
import model.*;

class InteractionBoxDemo
{
	public static void main(String[] args)
	{

		//
        // SWING
        //
        final KeyboardModel model = new KeyboardModel();
		final KeyboardPresenter presenter = new KeyboardPresenter( model );
		final KeyboardView keyboard = new KeyboardView( presenter );
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

		//
		// LEAP MOTION
		//

		MyListener listener = new MyListener();
		Controller controller = new Controller();

		controller.addListener(listener);

		// Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove the sample listener when done
        controller.removeListener(listener);

	}
}