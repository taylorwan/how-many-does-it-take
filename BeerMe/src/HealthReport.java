import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HealthReport extends Activity implements ActionListener
{
	//Main panel data
	private ArrayList<JLabel> reportList;
	
	private JPanel historyReport;
	private JPanel navigation;
	private JButton returnButton;
	
	//Constant strings
	private final static String header = "Hello %s! Below is your Health Report!";
	private final static String breaker = "-----------------------------------------";
	private final static String currentParticipantBAC = "Current BAC Level: ";
	private final static String hoursNeededTillDrivable = "Hours Till Can Drive: ";
	private final static String hoursNeededTillSober = "Hours Till Sober: ";
	
	//Main participant
	private Participant participant;
	
	public HealthReport(Application passedApplication)
	{
		super(passedApplication);
		reportList = new ArrayList<JLabel>();
		historyReport = new JPanel();
		create();
	}
	
	
	public void create()
	{
		thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.Y_AXIS));
		historyReport.setLayout(new BoxLayout(historyReport, BoxLayout.Y_AXIS));
		container.setLayout(new BorderLayout());
		
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		
		
		if(participant == null)
		{
			thisPanel.add(returnButton);
			return;
		}
		
	}
	
	public void initGUI()
	{	
		thisPanel.remove(returnButton);
		createDataFromPartcipant();
		createHistoryReport();
	}
	
	private void createDataFromPartcipant()
	{
		
		reportList.add(new JLabel(String.format(header, participant.getName())));
		reportList.add(new JLabel(breaker));
		reportList.add(new JLabel(currentParticipantBAC + participant.getCurrentBAC()));
		reportList.add(new JLabel(hoursNeededTillDrivable + Double.toString(HealthCalculator.calculateHoursNeeded(participant, .08))));
		reportList.add(new JLabel(hoursNeededTillSober + HealthCalculator.calculateHoursNeeded(participant, 0)));
		
		
		for(int i = 0; i < reportList.size(); i++)
		{
			thisPanel.add(reportList.get(i));
		}
		
		container.add(thisPanel, BorderLayout.WEST);
		container.add(returnButton, BorderLayout.SOUTH);
		
	}
	
	private void createHistoryReport()
	{
		FlowLayout simpleLayout = new FlowLayout();
		simpleLayout.setHgap(15);
		
		ArrayList<SobrietyProjectionCell> reportValues = HealthCalculator.calculateHoursWithBACs(participant, 0); 

		JPanel easyPanel = new JPanel(simpleLayout);
		easyPanel.add(new JLabel("<html>Projected <br> Hourly <br> Progression</html>"));
		easyPanel.add(new JLabel("<html>After <br> Allotted <br> Hours</html>"));
		historyReport.add(easyPanel);
		
		//Find and insert the history report
		for(int i = 0; i < reportValues.size(); i++)
		{
			easyPanel = new JPanel(simpleLayout);
			easyPanel.add(new JLabel("HOURS: " + reportValues.get(i).getNumberOfHours()));
			easyPanel.add(new JLabel("BAC: " + reportValues.get(i).getBAC()));
			
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
        	clearLists();
        	historyReport.removeAll();
        	deactivate();
        	
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
        }
        
	}
	
	private void clearLists()
	{
		reportList.clear();
	}
	
	
	
	
}
