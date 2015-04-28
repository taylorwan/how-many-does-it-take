import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;


public class Application extends Applet
{
	//Keeping track of activities
	public enum CURRENT_ACTIVITY {TITLE, MAIN, HEALTH_REPORT, HEALTH_CHART, QUIZ};
	private static CURRENT_ACTIVITY currentActivity;
	
	private JFrame mainFrame;
	
	//Activities
	private TitleActivity titleActivity;
	private MainActivity mainActivity;
	private QuizActivity quizActivity;
	private HealthReport healthReport;
	private HealthChart healthChart;
	final String titleActivityFilePath = "img/intro.jpg";
	
	//Screen dimension constants
	final static int screenWidth = 800;
	final static int screenHeight = 600;
	
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
		
		//Set the main Frame for the application
		mainFrame = new JFrame();
		
		//Create all activities and current activity
		createActivities();
		
		//Set the current activity to the title screen
		setCurrentActivity(CURRENT_ACTIVITY.TITLE);
		
		//mainFrame.setContentPane(titleActivity);
	}
	
	private void createActivities() throws IOException
	{
		//Create the health report
		healthReport = new HealthReport(this);
		
		//Create the main activity
		mainActivity = new MainActivity(this, quizActivity, healthReport);
		
		//Create the title activity
		titleActivity = new TitleActivity(titleActivityFilePath, this, mainActivity);
		
		//Create the quiz activity
		quizActivity = new QuizActivity(this);
		
		//Create the health chart 
		healthChart = new HealthChart(this);
	
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
					case HEALTH_CHART:
						healthChart.begin();
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
			case HEALTH_CHART:
				healthChart.activate();
				break;
		}
	}
	
	//Returns the main frame
	public JFrame getMainFrame()
	{
		return mainFrame;
	}
	
	public <T> T getActivity(String thisActivity)
	{
	   switch (thisActivity)
	   {
	   case "mainActivity":
		   return (T) mainActivity;
	   case "titleActivity":
		   return (T) titleActivity;
	   case "healthReport":
		   return (T) healthReport;
	   case "healthChart":
		   return (T) healthChart;
	   case "quizActivity":
		   return (T) quizActivity;
		 default:
			 throw new IllegalArgumentException("ERROR: Activity not found");
	   }
	}
	
	
}
