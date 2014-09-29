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

	public onDisconnect(Controller controller)
	{
		System.out.println("Disconnected");
	}

	public void onExit(Controller controller)
	{
		System.out.println("Exited");
	}

	public void onFrames(Controller controller)
	{
		InteractionBox iBox = frame.getInteractionBox();
		System.out.println("Center: " + iBox.center());
	}
}