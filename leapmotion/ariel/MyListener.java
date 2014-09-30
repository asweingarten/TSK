import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class MyListener extends Listener
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