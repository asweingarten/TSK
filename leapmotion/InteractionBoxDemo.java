import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class InteractionBoxListener extends Listener
{
	public void onInit(Controller controller)
	{
		System.out.println("Initialized");
	}

	public void onConnect(Controller controller)
	{
		System.out.println("Connected");
	}

	public void onDisconnect(Controller controller)
	{
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller)
	{
		System.out.println("Exited");
	}

	public void onFrame(Controller controller)
	{
		Frame frame = controller.frame();
		InteractionBox iBox = frame.interactionBox();
		System.out.println("Center: " + iBox.center());
	}
}

class InteractionBoxDemo
{
	public static void main(String[] args)
	{
		InteractionBoxListener listener = new InteractionBoxListener();
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