import java.awt.Container;

import javax.swing.JPanel;


public class Activity
{
	protected JPanel thisPanel;
	protected Application application;
	
	protected Container container;
	
	public Activity(Application passedApplication)
	{
		thisPanel = new JPanel();
		application = passedApplication;	
		container = passedApplication.getMainFrame().getContentPane();
	}
	
	public void create()
	{}
	
	public void initGUI()
	{}
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		//Re-initialize the GUI
		initGUI();
		
		//Add to content pane
		application.getMainFrame().getContentPane().add(thisPanel);
		container.setVisible(true);
	}
	
	//Call before changing activities
	public void deactivate()
	{
		thisPanel.removeAll();
		container.removeAll();
		application.getMainFrame().getContentPane().remove(thisPanel);
		container.setVisible(false);
	}
	
	public void begin() throws InterruptedException
	{
		thisPanel.repaint();
		update();
		Thread.sleep(10);
	}
	
	private void update()
	{
		//Do any drawing here
	}
	
}
