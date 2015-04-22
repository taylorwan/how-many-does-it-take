import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;


public class Application extends JFrame
{
	//Keeping track of activities
	public enum CURRENT_ACTIVITY {TITLE, MAIN};
	private static CURRENT_ACTIVITY currentActivity;
	
	//Activities
	private TitleActivity titleActivity;
	private MainActivity mainActivity;
	
	final String titleActivityFilePath = "/Users/danielanderson/Desktop/start_screen2.png";
	final String mainActivityFilePath = "";
	
	//Screen dimension constants
	final static int screenWidth = 800;
	final static int screenHeight = 600;
	
	//Application constructor
	public Application() throws IOException
	{
		
		create();
		initGUI();
	}
	
	//Internal application creation function
	private void create() throws IOException
	{
		
		//Create all activities and current activity
		mainActivity = new MainActivity(this);
		titleActivity = new TitleActivity(titleActivityFilePath, this, mainActivity);
		currentActivity = CURRENT_ACTIVITY.TITLE;
	}
	
	//Creates all the GUI components
	private void initGUI()
	{
		
		//Init GUI components of the JFrame
		setLocation(100,100);
		Color color = new Color(0,0,0,0);
		setForeground(color);
		setSize(screenWidth, screenHeight);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Main processing function
	public static void main(String[] args) 
	{
		
		//Try application
		try
		{	
			//Create the application and run it
			Application application = new Application();
			application.runApp();
		}
		catch(IOException e)
		{
			System.out.println("io exception");
			System.out.println(e.getMessage());
		}
		catch(InterruptedException i)
		{
			System.out.println("interrupted exception");
			System.out.println(i.getMessage());
		}
		catch(NullPointerException n)
		{
			System.out.println("null exception");
			System.out.println(n.getMessage());
		}

	}
	
	//Runs the application based on current activity
	public void runApp() throws InterruptedException
	{
		try
		{
			//Application Loop
			while(true)
			{
				switch (currentActivity)
				{
					case TITLE:
						titleActivity.begin();
						break;
					case MAIN:
						
						mainActivity.begin();
						break;
				}
				
			}
			
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Sets the current activity for application control
	public void setCurrentActivity(CURRENT_ACTIVITY passedActivity)
	{
		currentActivity = passedActivity;
	}
	
	
}
