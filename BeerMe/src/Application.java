import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;


public class Application extends Applet
{
	//Keeping track of activities
	public enum CURRENT_ACTIVITY {TITLE, MAIN, HEALTH_REPORT, QUIZ};
	private static CURRENT_ACTIVITY currentActivity;
	
	private JFrame mainFrame;
	
	//Activities
	private TitleActivity titleActivity;
	private MainActivity mainActivity;
	private QuizActivity quizActivity;
	private HealthReport healthReport;
	final String titleActivityFilePath = "../img/intro.jpg";
	
	//Screen dimension constants
	private final static int screenWidth = 800;
	private final static int screenHeight = 600;
	
	//Application constructor
	public Application() throws IOException
	{
		System.out.println("Application::Application()");
		create();
		initGUI();
	}
	
	//Internal application creation function
	private void create() throws IOException
	{
		System.out.println("Application::create()");
		//Create all activities and current activity
		mainFrame = new JFrame();
		healthReport = new HealthReport(this);
		mainActivity = new MainActivity(this, quizActivity, healthReport);
		titleActivity = new TitleActivity(titleActivityFilePath, this, mainActivity);
		quizActivity = new QuizActivity(this);
		currentActivity = CURRENT_ACTIVITY.TITLE;
		//setContentPane(titleActivity);
	}
	
	//Creates all the GUI components
	private void initGUI()
	{
		System.out.println("Application::initGUI()");
		//Init GUI components of the JFrame
		mainFrame.setLocation(0,0);
		Color color = new Color(0,0,0,0);
		mainFrame.setForeground(color);
		mainFrame.setSize(screenWidth, screenHeight);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Main processing function
	public static void main(String[] args) 
	{
		System.out.println("Application::main()");
		
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
			System.out.println("Application::runApp()");
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
					case QUIZ:
						quizActivity.begin();
						break;
					case HEALTH_REPORT:
						healthReport.begin();
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
		System.out.println("Application::setCurrentActivity()");
		currentActivity = passedActivity;
		
		switch(currentActivity)
		{
			case TITLE:
				titleActivity.activate();
				break;
			case MAIN:
				mainActivity.activate();
				break;
			case QUIZ:
				quizActivity.activate();
				break;
			case HEALTH_REPORT:
				healthReport.activate();
				break;
		}
	}
	
	//Returns the main frame
	public JFrame getMainFrame()
	{
		return mainFrame;
	}
	
	
}
