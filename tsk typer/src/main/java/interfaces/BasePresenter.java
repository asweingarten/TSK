package interfaces;

import tsk_typer;

public abstract class BasePresenter
{
	protected TskTyperModel tskTyperModel_;

	BasePresenter( TskTyperModel tskTyperModel )
	{
		tskTyperModel_ = tskTyperModel;
	}

}