import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/*
 * 
 * Problems to look at, how to nest panels?
 * It seems we can't add a new panel to the
 * content pane w/o losing the background picture
 * we are having issues adequitly resising the 
 * components to make them work with a grid layout
 * 
 */
public class TitleActivity extends JPanel implements ActionListener
{
	private Application application;
	private MainActivity mainActivity;
	private JPanel form;
	private JPanel switchActivities;
	
	//Background
	private Image backgroundImage;
	
	//Buttons
	private JTextField weightInputBox;
	private JTextField nameInputBox;
	
	//Radio buttons
	private ButtonGroup genderButtons;
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	
	//Combo Box
	private JComboBox<String> hoursMenu;
	
	//Start Button
	private JButton startButton;
	
	//Associative texts for the form
	private JLabel nameLabel;
	private JLabel weightLabel;
	private JLabel genderLabel;
	private JLabel hourLabel;

	
	//Constant Characters for radio buttons
	private static final String maleString = "Male";
	private static final String femaleString = "Female";
	private static final String startString = "Start";
	
	
	private Container container;
	
	public TitleActivity(final String filePath, Application passedApplication, MainActivity passedMainActivity) throws IOException
	{
		//Set the application and other important components
		application = passedApplication;
		mainActivity = passedMainActivity;
		backgroundImage = Toolkit.getDefaultToolkit().getImage(filePath);
		
		//Create input text fields
		weightInputBox = new JTextField(15);
		nameInputBox = new JTextField(15);
		
		
		//Buttons and button group
		startButton = new JButton(startString);
		startButton.addActionListener(this);
		genderButtons = new ButtonGroup();
		maleButton = new JRadioButton(maleString);
		maleButton.setSelected(true);
		femaleButton = new JRadioButton(femaleString);
		
		//Create all labels
		nameLabel = new JLabel("NAME");
		weightLabel = new JLabel("WEIGHT");
		genderLabel = new JLabel("GENDER");
		hourLabel = new JLabel("HRS");
		
		//This contains form input 
		form = new JPanel(new FlowLayout());
		form.setVisible(true);
		
		switchActivities = new JPanel(new FlowLayout());
		switchActivities.setVisible(true);
		
		//This container will hold the title layout
		container = application.getContentPane();
		container.setLayout(new BorderLayout());
		application.setLayout(new BorderLayout());
		
		
		//Drop down menu
		String hourOptions[] = {"1","2","3","4","5","6","7","8","9"};
		hoursMenu = new JComboBox<String>(hourOptions);
		
		//application.getContentPane().add(this);
		
		//Create the GUI
		initGUI();
	}
	
	public void initGUI()
	{
		container.setLayout(new BorderLayout());
		application.getContentPane().add(this);
		container = application.getContentPane();
		
		genderButtons.add(maleButton);
		genderButtons.add(femaleButton);
		
		
		Dimension d = new Dimension(50,50);
		nameLabel.setSize(d);
		weightLabel.setSize(d);
		form.add(nameLabel);
		nameInputBox.setSize(d);
		weightInputBox.setSize(d);
		startButton.setSize(d);
		form.add(nameInputBox);
		form.add(weightLabel);
		form.add(weightInputBox);
		form.add(genderLabel);
		form.add(maleButton);
		form.add(femaleButton);
		form.add(hourLabel);
		form.add(hoursMenu);
		form.setBackground(Color.GRAY);
		switchActivities.add(startButton);
		
		container.add(form, BorderLayout.SOUTH);
		container.add(switchActivities, BorderLayout.NORTH);
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
		thisGraphic.drawImage(backgroundImage, 0, 0, this);
	}
	
	private boolean weightIsGood()
	{
		if(Integer.parseInt(weightInputBox.getText()) < 0)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean nameIsGood() throws IllegalArgumentException
	{
		if(nameInputBox.getText() == null)
		{
			return false;
		}
		
		return true;
	}
	
	//Implemented Action Listener Function
	public void actionPerformed(ActionEvent evt) 
	{
		
        String command = evt.getActionCommand();
        
        //If start button is pressed we want to aquire
        //All data from the form
        if (command.equals("Start"))
        {
        	
        	if( weightIsGood() && nameIsGood() )
        	{
            	//Create Participant variables
            	String pName = nameInputBox.getText();
            	int pWeight = Integer.parseInt( weightInputBox.getText() );
            	Participant.GENDER pGender = null; 
            	
            	//Check for gender
            	if(maleButton.isSelected())
            	{
            		pGender = Participant.GENDER.MALE;
            	}
            	else
            	{
            		pGender = Participant.GENDER.FEMALE;
            	}
            	
            	//Create participant
            	Participant participant = new Participant(pName, pWeight, pGender);
            	
            	//Give the main activity the participant
            	mainActivity.setParticipant(participant);
            	
            	//Deactivate current activity
        		container.setVisible(false);
        		container.removeAll();
        		application.getContentPane().removeAll();
        		
            	//Activate main activity
            	mainActivity.activate();
        	}
        	
        }

     } // end actionPerformed()

}
