package view;
import .*;

import javax.swing.*;

public class TypingGameView extends JComponent implements IView
{
	// Window information
	private final int FPS_ = 30;
	private final int width_  = 800;
	private final int height_ = 600;

	// Presenter
	private TypingGamePresenter presenter_;

	public TypingGameView()
	{
		super();
		this.setLayout( new BorderLayout() );

		presenter_ = new TypingGamePresenter();
	}

	public void paintComponent( Graphics g )
	{
		Graphics2D g2 = ( Graphics2D ) g;
	}

	public synchronized void updateFromPresenter()
	{

	}

	public void saveToPresenter()
	{

	}

}