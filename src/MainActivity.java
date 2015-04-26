import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;


public class MainActivity extends JPanel implements ActionListener
{
	
	//Different alcohol types
	public enum ALCOHOL_TYPE{BEER, WINE, SHOT, COCKTAIL};
	
	//Main application for Activity Navigation
	private Application application;
	private JPanel alcoholAccessorMenu[];
	private Container container;
	
	//Image for the body
	private Image bodyFigure;
	
	//Body components - COLORS
	private final static Color bodyBaseColor = Color.BLACK;
	private final static Color bodyFillLow = Color.GREEN;
	private final static Color bodyFillMed = Color.ORANGE;

	// - RECTANGLES and sizes
	private static int bodyMaxHeight = 354;
	private static int bodyWidth = 159;
	private Rectangle bodyBase;
	private Rectangle bodyFill;
	
	//Panel for holding participant info
	private JPanel participantInfo;
	private JPanel alcoholMenu;
	private JLabel nameLabel;
	private JLabel bacLabel;
	private JLabel caloriesLabel;
	
	//JLabel for names of alcohol
	private JLabel beerLabel;
	private JLabel WineLabel;
	private JLabel shotLabel;
	private JLabel cocktailLabel;
	
	//Icons for alcohols
	private ImageIcon beerIcon;
	private ImageIcon wineIcon;
	private ImageIcon cocktailIcon;
	
	//Add Buttons
	private JButton addBeer;
	private JButton addWine;
	private JButton addShot;
	private JButton addCocktail;
	
	//Remove Buttons
	private JButton removeBeer;
	private JButton removeWine;
	private JButton removeShot;
	private JButton removeCocktail;
	
	//Constant Strings
	private final static String bacLabelString = "BAC: ";
	private final static String caloriesLabelString = "CALORIES: ";
	private final static String beerLabelString = "ADD BEER(S)";
	private final static String WineLabelString = "ADD Wine";
	private final static String shotLabelString = "ADD SHOT(S)";
	private final static String cocktailLabelString = "ADD COCKTAIL(S)";
	private final static String helloMessage = "Hello ";
	private final static String maleBodyFilepath = "/Users/danielanderson/Desktop/body_fill.png";
	private final static String femaleBodyFilepath = "/Users/danielanderson/Desktop/female-w-trans.png";
	
	//Participant
	
	private Participant participant;
	
	//Constructor
	public MainActivity(Application passedApplication) throws IOException
	{
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;
		
		//Create image of the male body
		bodyFigure = Toolkit.getDefaultToolkit().getImage(maleBodyFilepath);
		
		create();
	}
	
	//create all components of MainActivity
	private void create() throws IOException
	{

		bodyBase = new Rectangle(50, 50, bodyWidth, bodyMaxHeight);
		bodyFill = new Rectangle(50,50,bodyWidth,0);
		
		//Create JPanel to hold participant information
		participantInfo = new JPanel();
		participantInfo.setLayout(new FlowLayout());
		
		//Create the general accessor menu
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setHgap(10);
		alcoholAccessorMenu = new JPanel[4];
		
		//Give them all the flow layout
		for(int i = 0; i < 4; i++)
		{
			alcoholAccessorMenu[i] = new JPanel(buttonLayout);
		}
		
		//Alcohol panel on the right?
		alcoholMenu = new JPanel();
		alcoholMenu.setLayout(new BoxLayout(alcoholMenu, BoxLayout.Y_AXIS));
		
		//Create the labels, which later will be dynamic
		bacLabel = new JLabel(bacLabelString);
		caloriesLabel = new JLabel(caloriesLabelString);
		nameLabel = new JLabel(helloMessage);
		
		//Alcohol labels
		
		beerIcon = new ImageIcon("/Users/danielanderson/Desktop/beer_icon.png");
		beerLabel = new JLabel(beerIcon);
		alcoholAccessorMenu[0].add(beerLabel);
		
		WineLabel = new JLabel(WineLabelString);
		alcoholAccessorMenu[1].add(WineLabel);
		
		shotLabel = new JLabel(shotLabelString);
		alcoholAccessorMenu[2].add(shotLabel);
		
		cocktailIcon = new ImageIcon("/Users/danielanderson/Desktop/cocktail_icon.png");
		cocktailLabel = new JLabel(cocktailIcon);
		alcoholAccessorMenu[3].add(cocktailLabel);
		
		//Create buttons -> For INSERTS
		addBeer = new JButton("+BEER");
		addBeer.addActionListener(this);
		alcoholAccessorMenu[0].add(addBeer);
		
		addWine = new JButton("+WINE");
		addWine.addActionListener(this);
		alcoholAccessorMenu[1].add(addWine);
		
		addShot = new JButton("+SHOT");
		addShot.addActionListener(this);
		alcoholAccessorMenu[2].add(addShot);
		
		addCocktail = new JButton("+COCKTAIL");
		addCocktail.addActionListener(this);
		alcoholAccessorMenu[3].add(addCocktail);
		
		//Create buttons -> For REMOVALS
		removeBeer = new JButton("-BEER");
		removeBeer.addActionListener(this);
		alcoholAccessorMenu[0].add(removeBeer);
		
		removeWine = new JButton("-WINE");
		removeWine.addActionListener(this);
		alcoholAccessorMenu[1].add(removeWine);
		
		removeShot = new JButton("-SHOT");
		removeShot.addActionListener(this);
		alcoholAccessorMenu[2].add(removeShot);
		
		removeCocktail = new JButton("-COCKTAIL");
		removeCocktail.addActionListener(this);
		alcoholAccessorMenu[3].add(removeCocktail);
		
		
	}
	
	//Create the GUI
	private void initGUI()
	{
		//Change image accordingly -> male diagram
		if(participant.getGender() == Participant.GENDER.MALE)
		{		
			bodyFigure = Toolkit.getDefaultToolkit().getImage(maleBodyFilepath);
		}
		
		//Female diagram
		else if(participant.getGender() == Participant.GENDER.FEMALE)
		{
			bodyFigure = Toolkit.getDefaultToolkit().getImage(femaleBodyFilepath);
		}
		
		container = application.getContentPane();
		
		//Add labels for participant
		participantInfo.add(bacLabel);
		participantInfo.add(caloriesLabel);
		
		//Add all flow menus to side panel box layout
		for(int i = 0; i < alcoholAccessorMenu.length; i++)
		{
			alcoholMenu.add(alcoholAccessorMenu[i]);
		}
		
		//Background colors
		participantInfo.setBackground(Color.GRAY);
		alcoholMenu.setBackground(Color.GRAY);
		nameLabel.setBackground(Color.GRAY);
		
		//Work with container
		container.setLayout(new BorderLayout());
		container.add(nameLabel, BorderLayout.NORTH);
		container.add(participantInfo, BorderLayout.SOUTH);
		container.add(alcoholMenu, BorderLayout.EAST);
		
		application.getContentPane().add(this);
	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		
		super.paintComponent(thisGraphic);
		
		Graphics2D rectGraphic = (Graphics2D) thisGraphic;
		
		int bodyFillHeight = 0;
		
		final double maxBAC = .30;
		final double maxHeight = (double) bodyFigure.getHeight(this);
		
		//Calculate height of fill rect
		bodyFillHeight = (int)((participant.getCurrentBAC() / maxBAC ) * maxHeight) ;
		
		//Set color for the base fill and draw the rectangle behind the figure
		rectGraphic.setColor(Color.black);
		rectGraphic.fillRect(250, 50, bodyFigure.getWidth(this), bodyFigure.getHeight(this));
		
		//Draw image layer one
		thisGraphic.drawImage(bodyFigure, 250, 50, this);
		
		//Draw dynamic rect -> second fill
		rectGraphic.setColor(Color.green);
		
		//Fill dynamic rect based on current height needed
		rectGraphic.fillRect(250, (int) (50 + maxHeight - bodyFillHeight), bodyFigure.getWidth(this), bodyFillHeight);
		
		//Draw image layer two
		thisGraphic.drawImage(bodyFigure, 250, 50, this);
		
	}
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		//Re-initialize the GUI
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
	
	//Start the activity processing
	public void begin() throws InterruptedException
	{
		
		repaint();
		Thread.sleep(10);
	}
	
	//Set the participant
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
        
        //If user chooses to add or remove Beer
        if (command.equals("+BEER"))
        {
        	addAlcohol(ALCOHOL_TYPE.BEER);
        }
        if(command.equals("-BEER"))
        {
        	removeAlcohol(ALCOHOL_TYPE.BEER);
        }
        
        //If user chooses to add or remove Wine
        if (command.equals("+WINE"))
        {
        	addAlcohol(ALCOHOL_TYPE.WINE);
        }
        if(command.equals("-WINE"))
        {
        	removeAlcohol(ALCOHOL_TYPE.WINE);
        }
        
        //If user chooses to add or remove a shot
        if (command.equals("+SHOT"))
        {
        	addAlcohol(ALCOHOL_TYPE.SHOT);
        }
        if(command.equals("-SHOT"))
        {
        	removeAlcohol(ALCOHOL_TYPE.SHOT);
        }
        
        //If user chooses to add or remove a cocktail
        if (command.equals("+COCKTAIL"))
        {
        	addAlcohol(ALCOHOL_TYPE.COCKTAIL);
        }
        if(command.equals("-COCKTAIL"))
        {
        	removeAlcohol(ALCOHOL_TYPE.COCKTAIL);
        }

     } // end actionPerformed()
	
	private void addAlcohol(ALCOHOL_TYPE whichAlcohol)
	{
		
		//Decide which alcohol to alter
		switch(whichAlcohol)
		{
			case BEER:
	        	//Give participant the beers
	        	participant.setCurrentBeers(participant.getCurrentBeers() + 1);
	        	break;
	        	
			case WINE:
	        	//Give participant the beers
	        	participant.setCurrentWine(participant.getCurrentWine() + 1);
	        	break;
	        	
			case SHOT:
	        	//Give participant the beers
	        	participant.setCurrentShots(participant.getCurrentShots() + 1);
	        	break;
	        	
			case COCKTAIL:
	        	//Give participant the beers
	        	participant.setCurrentCocktails(participant.getCurrentCocktails() + 1);
	        	break;
			
		}
		
    	//Set participant calories and BAC
    	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
    	participant.setCurrentCalories(HealthCalculator.caluclateCalories(participant));
    	
    	//Set a new text
    	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
    	caloriesLabel.setText(caloriesLabelString + participant.getCurrentCalories()); 
	}
	
	private void removeAlcohol(ALCOHOL_TYPE whichAlcohol)
	{
		//Decide which alcohol to alter
		switch(whichAlcohol)
		{
			case BEER:
	        	//Give participant the beers
	        	participant.setCurrentBeers(participant.getCurrentBeers() - 1);
	        	break;
	        	
			case WINE:
	        	//Give participant the beers
	        	participant.setCurrentWine(participant.getCurrentWine() - 1);
	        	break;
	        	
			case SHOT:
	        	//Give participant the beers
	        	participant.setCurrentShots(participant.getCurrentShots() - 1);
	        	break;
	        	
			case COCKTAIL:
	        	//Give participant the beers
	        	participant.setCurrentCocktails(participant.getCurrentCocktails() - 1);
	        	break;
			
		}
		
    	//Set participant calories and BAC
    	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
    	participant.setCurrentCalories(HealthCalculator.caluclateCalories(participant));
    	
    	//Set a new text
    	bacLabel.setText(bacLabelString + participant.getCurrentBAC());
    	caloriesLabel.setText(caloriesLabelString + participant.getCurrentCalories()); 
	}
	
}
