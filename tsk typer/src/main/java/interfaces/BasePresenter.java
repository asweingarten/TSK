package interfaces;

import tsk_typer.*;

public abstract class BasePresenter
{
	protected TskTyperModel tskTyperModel_;

	public BasePresenter()
	{
		tskTyperModel_ = TskTyperPresenter.getModel();
	}

}