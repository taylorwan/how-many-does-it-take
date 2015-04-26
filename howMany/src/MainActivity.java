import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class MainActivity extends JPanel implements ActionListener
{
	//Main application for Activity Navigation
	private Application application;
	private Container container;
	
	//Panel for holding participant info
	private JPanel participantInfo;
	private JPanel alcoholMenu;
	private JLabel nameLabel;
	private JLabel bacLabel;
	private JLabel caloriesLabel;
	
	//JLabel for names of alcohol
	private JLabel beerLabel;
	private JLabel whineLabel;
	private JLabel shotLabel;
	private JLabel cocktailLabel;
	
	//Inputs for alcohols
	private JTextField beerInput;
	private JTextField whineInput;
	private JTextField shotInput;
	private JTextField cocktailInput;
	
	//Add Buttons
	private JButton addBeer;
	private JButton addWhine;
	private JButton addShot;
	private JButton addCocktail;
	
	
	//Constant Strings
	final static String bacLabelString = "BAC: ";
	final static String caloriesLabelString = "CALORIES: ";
	final static String beerLabelString = "ADD BEER(S)";
	final static String whineLabelString = "ADD WHINE";
	final static String shotLabelString = "ADD SHOT(S)";
	final static String cocktailLabelString = "ADD COCKTAIL(S)";
	final static String helloMessage = "Hello ";
	
	//Participant
	private Participant participant;
	
	public MainActivity(Application passedApplication)
	{
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;
		container = application.getContentPane();
		
		create();
	}
	
	private void create()
	{
		
		//Create JPanel to hold participant information
		participantInfo = new JPanel();
		participantInfo.setLayout(new FlowLayout());
		
		//Alcohol panel on the right?
		alcoholMenu = new JPanel();
		alcoholMenu.setLayout(new BoxLayout(alcoholMenu, BoxLayout.Y_AXIS));
		
		//Create the labels, which later will be dynamic
		bacLabel = new JLabel(bacLabelString);
		caloriesLabel = new JLabel(caloriesLabelString);
		nameLabel = new JLabel(helloMessage);
		
		//Alcohol labels
		beerLabel = new JLabel(beerLabelString);
		whineLabel = new JLabel(whineLabelString);
		shotLabel = new JLabel(shotLabelString);
		cocktailLabel = new JLabel(cocktailLabelString);
		
		//Alcohol Inputs
		beerInput = new JTextField(5);
		whineInput = new JTextField(5);
		shotInput = new JTextField(5);
		cocktailInput = new JTextField(5);
		
		//Create buttons
		addBeer = new JButton("ADD BEER");
		addBeer.addActionListener(this);
		
		addWhine = new JButton("ADD WHINE");
		addWhine.addActionListener(this);
		
		addShot = new JButton("ADD SHOT");
		addShot.addActionListener(this);
		
		addCocktail = new JButton("ADD COCKTAIL");
		addCocktail.addActionListener(this);
		
	}
	
	private void initGUI()
	{
		container.setLayout(new BorderLayout());
		
		//Add labels for participant
		participantInfo.add(bacLabel);
		participantInfo.add(caloriesLabel);
		
		//Add alcohol labels with corresponding inputs
		alcoholMenu.add(beerLabel);
		alcoholMenu.add(beerInput);
		alcoholMenu.add(addBeer);
		
		alcoholMenu.add(whineLabel);
		alcoholMenu.add(whineInput);
		alcoholMenu.add(addWhine);
		
		alcoholMenu.add(shotLabel);
		alcoholMenu.add(shotInput);
		alcoholMenu.add(addShot);
		
		alcoholMenu.add(cocktailLabel);
		alcoholMenu.add(cocktailInput);
		alcoholMenu.add(addCocktail);
		
		
		//Work with container
		container.setLayout(new BorderLayout());
		container.add(nameLabel, BorderLayout.NORTH);
		container.add(participantInfo, BorderLayout.SOUTH);
		container.add(alcoholMenu, BorderLayout.EAST);
	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		super.paintComponent(thisGraphic);
		//thisGraphic.drawImage(backgroundImage, -187, 0, this);
	}
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		//Reinitallize the GUI
		initGUI();
		
		//Set the current Activity
		application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
		
		//Make container visible
		container.setVisible(true);
	}
	
	//Call before changing activities
	public void deactivate()
	{
		container.removeAll();
		container.setVisible(true);
	}
	
	public void begin()
	{
		repaint();
	}
	
	public void setParticipant(Participant passedParticipant)
	{
		participant = passedParticipant;
		nameLabel.setText(helloMessage + participant.getName());
		bacLabel.setText(bacLabelString + participant.getCurrentBAC());
		caloriesLabel.setText(caloriesLabelString + participant.getCurrentCalories());
	}
	
	//Implemented Action Listener Function
	public void actionPerformed(ActionEvent evt) 
	{
		
        String command = evt.getActionCommand();
        
        //If user chooses to add beer
        if (command.equals("ADD BEER"))
        {
        	
        	//Get new number of beers
        	int beersToAdd = Integer.parseInt( beerInput.getText() );
        	
        	//Give participant the beers
        	participant.setCurrentBeers(participant.getCurrentBeers() + beersToAdd);
        	
        	//Set participant calories
        	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
        	
        	//Set a new text
        	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
        	
        	//Reset the input text
        	beerInput.setText("");
        	
        }
        //If user chooses to add beer
        if (command.equals("ADD WHINE"))
        {
        	
        	//Get new number of beers
        	int whinesToAdd = Integer.parseInt( whineInput.getText() );
        	
        	//Give participant the beers
        	participant.setCurrentWhine(participant.getCurrentBeers() + whinesToAdd);
        	
        	//Set participant calories
        	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
        	
        	//Set a new text
        	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
        	
        	//Reset the input text
        	whineInput.setText("");
        	
        }
        //If user chooses to add beer
        if (command.equals("ADD SHOT"))
        {
        	
        	//Get new number of beers
        	int shotsToAdd = Integer.parseInt( shotInput.getText() );
        	
        	//Give participant the beers
        	participant.setCurrentShots(participant.getCurrentBeers() + shotsToAdd);
        	
        	//Set participant calories
        	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
        	
        	//Set a new text
        	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
        	
        	//Reset the input text
        	shotInput.setText("");
        	
        }
        //If user chooses to add beer
        if (command.equals("ADD COCKTAIL"))
        {
        	
        	//Get new number of beers
        	int cocktailsToAdd = Integer.parseInt( cocktailInput.getText() );
        	
        	//Give participant the beers
        	participant.setCurrentCocktails(participant.getCurrentCocktails() + cocktailsToAdd);
        	
        	//Set participant calories
        	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
        	
        	//Set a new text
        	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
        	
        	//Reset the input text
        	cocktailInput.setText("");
        	
        }

     } // end actionPerformed()
	
}
