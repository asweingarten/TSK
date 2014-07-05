package interfaces;

import java.util.ArrayList;

public abstract class BaseSubject
{
	protected ArrayList<IObserver> observerList_;

	public void subscribe( IObserver observer )
	{
		observerList_.add(observer);
	}

	public void unsubscribe( IObserver observer )
	{
		observerList_.remove(observer);
	}

	protected void publish()
	{
		for ( IObserver observer : observerList_ )
		{
			observer.update();
		}
	}
}