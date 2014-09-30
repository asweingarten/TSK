import java.io.IOException;
import java.lang.Math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class InteractionBoxDemo
{
	public static void main(String[] args)
	{
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

        //
        // SWING
        //

        JFrame jFrame = new JFrame( "Keyboard v0.0.1" );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.setSize( 800, 600 );
		// jFrame.setContentPane( keyboard );
		jFrame.setVisible( true );

	}
}