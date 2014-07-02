package interfaces;

import java.util.ArrayList;

public abstract class BaseSubject
{
	private ArrayList<IObserver> observer_list_;

	public void subscribe( IObserver observer )
	{
		observer_list_.add(observer);
	}

	public void unsubscribe( IObserver observer )
	{
		observer_list_.remove(observer);
	}

	protected void publish()
	{
		for ( IObserver observer : observer_list_ )
		{
			observer.update();
		}
	}
}