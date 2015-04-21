import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainActivity extends JPanel
{
	//Main application for Activity Navigation
	private Application application;
	
	//Participant
	private Participant participant;
	
	public MainActivity(Application passedApplication)
	{
		application = passedApplication;
	}
	
	public void begin()
	{
		//Begin processing
	}
	
	public void setParticipant(Participant passedParticipant)
	{
		participant = passedParticipant;
	}
	
}
