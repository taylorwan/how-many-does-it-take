import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HealthReport extends JPanel implements ActionListener
{
	//Main panel data
	private ArrayList<JLabel> reportList;
	
	//Needs the Application
	Application application;
	
	//Main Container
	Container container;
	
	
	//Chart for Display
	private JPanel historyReport;
	private JPanel postReport;
	private JPanel navigation;
	private JButton returnButton;
	private JButton chartButton;
	
	//Constant strings
	private final static String header = "Hello %s! Below is your Health Report!";
	private final static String breaker = "-----------------------------------------";
	private final static String currentParticipantBAC = "Current BAC Level: ";
	private final static String hoursNeededTillDrivable = "Hours Till Can Drive: ";
	private final static String hoursNeededTillSober = "Hours Till Sober: ";
	private final static String headerOne = "Hours After";
	private final static String headerTwo = "Your BAC";
	
	//Main participant
	private Participant participant;
	
	public HealthReport(Application passedApplication)
	{
		application = passedApplication;
		reportList = new ArrayList<JLabel>();
		
		create();
	}
	
	
	public void create()
	{
		
		container = application.getMainFrame().getContentPane();
		postReport = new JPanel();
		historyReport = new JPanel();
		
		//Create flow for navigation
		FlowLayout flow = new FlowLayout();
		flow.setHgap(15);
		
		postReport.setLayout(new BoxLayout(postReport, BoxLayout.Y_AXIS));
		historyReport.setLayout(new BoxLayout(historyReport, BoxLayout.Y_AXIS));
		navigation = new JPanel(flow);
		container.setLayout(new BorderLayout());
		
		//Create the buttons
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		chartButton = new JButton("Health Chart");
		chartButton.addActionListener(this);
		
		if(participant == null)
		{
			navigation.add(returnButton);
			navigation.add(chartButton);
			return;
		}
		
	}
	
	public void initGUI()
	{	
		remove(returnButton);
		
		//Add buttons to the navigation
		navigation.add(returnButton);
		navigation.add(chartButton);
		
		//Create data and reports
		createDataFromPartcipant();
		createHistoryReport();
	}
	
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		//Re-initialize the GUI
		initGUI();
		
		//Add to content pane
		application.getMainFrame().getContentPane().add(this);
		container.setVisible(true);
	}
	
	
	public void deactivate()
	{
    	clearLists();
    	historyReport.removeAll();
		container.removeAll();
		postReport.removeAll();
		application.getMainFrame().getContentPane().remove(this);
		container.setVisible(false);
	}
	
	private void createDataFromPartcipant()
	{
		
    	DecimalFormat tempBAC = new DecimalFormat("#0.0000");
    	String newBAC = tempBAC.format(participant.getCurrentBAC());
    	
		reportList.add(new JLabel(String.format(header, participant.getName())));
		reportList.add(new JLabel(breaker));
		reportList.add(new JLabel(currentParticipantBAC + newBAC));
		reportList.add(new JLabel(hoursNeededTillDrivable + Double.toString(HealthCalculator.calculateHoursNeeded(participant, .08))));
		reportList.add(new JLabel(hoursNeededTillSober + HealthCalculator.calculateHoursNeeded(participant, 0)));
		
		
		for(int i = 0; i < reportList.size(); i++)
		{
			postReport.add(reportList.get(i));
		}
		
		//Add the navigation to the post report
		postReport.add(navigation);
		postReport.setPreferredSize(new Dimension(400, 100));
		
		
		container.add(postReport, BorderLayout.WEST);
		container.add(navigation, BorderLayout.SOUTH);
		
	}
	
	private void createHistoryReport()
	{
		FlowLayout simpleLayout = new FlowLayout();
		simpleLayout.setHgap(15);
		
		ArrayList<SobrietyProjectionCell> reportValues = HealthCalculator.calculateHoursWithBACs(participant, 0); 

		JPanel easyPanel = new JPanel(simpleLayout);
		easyPanel.add(new JLabel(headerOne));
		easyPanel.add(new JLabel(headerTwo));
		historyReport.add(easyPanel);
		
		//Find and insert the history report
		for(int i = 0; i < reportValues.size(); i++)
		{
	    	DecimalFormat tempBAC = new DecimalFormat("#0.0000");
	    	String newBAC = tempBAC.format(reportValues.get(i).getBAC());
			easyPanel = new JPanel(simpleLayout);
			easyPanel.add(new JLabel("HOURS: " + reportValues.get(i).getNumberOfHours()));
			easyPanel.add(new JLabel("BAC: " + newBAC));
			historyReport.add(easyPanel);
		}
		
		container.add(historyReport, BorderLayout.EAST);
	}
	
	public void setParticipant(Participant passedParticipant)
	{
		participant = passedParticipant;
		
	}

	//Implemented Action Listener Function
	public void actionPerformed(ActionEvent evt) 
	{
        String command = evt.getActionCommand();
        
        //If user chooses to add or remove Beer
        if (command.equals("Return"))
        {
        	deactivate();
        	
        	//Set the current activity to the main activity
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
        }
        if(command.equals("Health Chart"))
        {
        	deactivate();
        	
        	//Get the chart activity and create a chart for it
        	HealthChart healthChart = (HealthChart) application.getActivity("healthChart");
        	healthChart.setChart(new Chart(participant));
        	
        	//Set the current activity to the health chart
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_CHART);
        }
        
	}
	
	private void clearLists()
	{
		reportList.clear();
	}
	
	public void begin() throws InterruptedException
	{
		repaint();
		Thread.sleep(10);
	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		super.paintComponent(thisGraphic);
	}

}
