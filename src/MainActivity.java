import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainActivity extends JPanel
{
	//Main application for Activity Navigation
	private Application application;
	private Container container;
	
	//Panel for holding participant info
	private JPanel participantInfo;
	private JLabel bacLabel;
	private JLabel caloriesLabel;
	
	final static String bacLabelString = "BAC: ";
	final static String caloriesLabelString = "CALORIES: ";
	
	//Participant
	private Participant participant;
	
	public MainActivity(Application passedApplication)
	{
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;
		container = application.getContentPane();
		
		//Create JPanel to hold participant information
		participantInfo = new JPanel();
		participantInfo.setLayout(new FlowLayout());
		
		//Create the labels, which later will be dynamic
		bacLabel = new JLabel(bacLabelString);
		caloriesLabel = new JLabel(caloriesLabelString);
	}
	
	private void initGUI()
	{
		//Set the layout for container
		container.setLayout(new BorderLayout());
		container.add(participantInfo, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		super.paintComponent(thisGraphic);
		//thisGraphic.drawImage(backgroundImage, -187, 0, this);
	}
	
	public void begin()
	{
		repaint();
	}
	
	public void setParticipant(Participant passedParticipant)
	{
		participant = passedParticipant;
	}
	
}
