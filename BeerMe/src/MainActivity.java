import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.text.DecimalFormat;


public class MainActivity extends JPanel implements ActionListener
{
	
	//Different alcohol types
	private final static AlcoholFactory alcoholFactory = new AlcoholFactory();
	
	//Main application for Activity Navigation
	private Application application;
	private QuizActivity quizActivity;
	private HealthReport healthReport;
	private JPanel alcoholAccessorMenu[];
	private Container container;
	
	//Starting positions for the body
	private final static int bodyPositionX = 175;
	private final static int bodyPositionY = 100;
	
	//Image for the body
	private Image bodyFigure;
	private Image bodyPegs;
	
	//Panel for holding participant info
	private JPanel participantInfo;
	private JPanel alcoholMenu;
	JLabel bacLabel;
	private JLabel caloriesLabel;
	
	//JLabel for icons that have hover over ability along with
	//Labels for consumptions
	private JLabel beerLabel;
	private JLabel wineLabel;
	private JLabel shotLabel;
	private JLabel cocktailLabel;
	
	//Icons for alcohols
	private ImageIcon beerIcon;
	private ImageIcon wineIcon;
	private ImageIcon cocktailIcon;
	private ImageIcon shotIcon;
	
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
	
	//BAC limits
	private final static double maxBAC = .30;
	private final static double drivingLimit = .08;
	private final static double limitTwo = .12;
	private final static double limitThree = .20;
	
	//Constant Strings
	private final static String bacLabelString = "<html><font color = '%s'> MY BAC: ";
	private final static String caloriesLabelString = "<html><font color = '%s'> MY CALORIES: ";
	private final static String fontHtmlCloser = "</font></html>";
	private final static String maleBodyFilepath = "../img/body_fill.png";
	private final static String femaleBodyFilepath = "../img/female-w-trans.png";
	private final static String bodyPegsFilepath = "../img/body_pegs.png";
	
	//Consumption Quantity Character
	private final static String consumptionQuantityChar = "x";
	
	//Informational Strings for hover overs
	private final static String beerHoverOver = "<html><font face='sansserif' color='black'>"
												+ "Beer <br>Calories: %s <br>Ounces: %s</html>";
	private final static String wineHoverOver = "<html><font face='sansserif' color='black'>"
												+ "Wine <br> Calories: %s <br> Ounces: %s</html>";;
	private final static String shotHoverOver = "<html><font face='sansserif' color='black'>"
												+ "Shot <br> Calories: %s <br> Ounces: %s</html>";;
	private final static String cocktailHoverOver = "<html><font face='sansserif' color='black'>"
												+ "Cocktail <br> Calories: %s <br> Ounces: %s</html>";;
	
	//Activity Navigation
	private JPanel navigation;
	private JButton returnButton;
	private JButton healthReportButton;
	private JButton quizButton;
	
	//Participant
	private Participant participant;
	
	//Constructor
	public MainActivity(Application passedApplication, QuizActivity passedQuizActivity, HealthReport passedHealthReport) throws IOException
	{
		System.out.println("MainActivity::MainActivity()");
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;
		quizActivity = passedQuizActivity;
		healthReport = passedHealthReport;
		
		//Create image of the male body
		bodyFigure = Toolkit.getDefaultToolkit().getImage(maleBodyFilepath);
		bodyPegs = Toolkit.getDefaultToolkit().getImage(bodyPegsFilepath);
		
		create();
	}
	
	//create all components of MainActivity
	private void create() throws IOException
	{
		System.out.println("MainActivity::create()");
		
		//Create the general accessor menu
		FlowLayout buttonLayout = new FlowLayout();
		buttonLayout.setHgap(5);
		
		alcoholAccessorMenu = new JPanel[4];
		
		//Participant information with Flow Layout
		FlowLayout infoLayout = new FlowLayout();
		infoLayout.setHgap(15);
		participantInfo = new JPanel(infoLayout);
		navigation = new JPanel(infoLayout);
		
		//Give them all the flow layout
		for(int i = 0; i < alcoholAccessorMenu.length; i++)
		{
			alcoholAccessorMenu[i] = new JPanel(buttonLayout);
			
			if(i % 2 == 0)
			{
				alcoholAccessorMenu[i].setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				alcoholAccessorMenu[i].setBackground(Color.gray);
			}
		}
		
		//Alcohol panel on the right?
		alcoholMenu = new JPanel();
		alcoholMenu.setLayout(new BoxLayout(alcoholMenu, BoxLayout.Y_AXIS));
		
		//Create the labels, which later will be dynamic
		bacLabel = new JLabel(bacLabelString);
		caloriesLabel = new JLabel(caloriesLabelString);
		
		// ----- Alcohol labels ----- //
		
		//BEER LABEL//
		beerIcon = new ImageIcon("../img/beer_icon.png");
		beerLabel = new JLabel(beerIcon);
		
		//Create hover over
		String thisHover = String.format(beerHoverOver, alcoholFactory.getCalories("Beer"),
				alcoholFactory.getOuncesPerDrink("Beer"));
		beerLabel.setToolTipText(thisHover);
		
		//Add label
		alcoholAccessorMenu[0].add(beerLabel);
		
		//WINE LABEL//
		wineIcon = new ImageIcon("../img/wine_icon.png");
		wineLabel = new JLabel(wineIcon);
		
		//Create hover over
		thisHover = String.format(wineHoverOver,alcoholFactory.getCalories("Wine"),
								 alcoholFactory.getOuncesPerDrink("Wine"));
		wineLabel.setToolTipText(thisHover);
		
		//Add label
		alcoholAccessorMenu[1].add(wineLabel);
		
		
		//SHOT LABEL//
		shotIcon = new ImageIcon("../img/shot_icon.png");
		shotLabel = new JLabel(shotIcon);
		
		//Create hover over
		thisHover = String.format(shotHoverOver,alcoholFactory.getCalories("Shot"),
				 alcoholFactory.getOuncesPerDrink("Shot"));
		shotLabel.setToolTipText(thisHover);
		
		//Add label
		alcoholAccessorMenu[2].add(shotLabel);
		
		//COCKTAIL LABEL//
		cocktailIcon = new ImageIcon("../img/cocktail_icon.png");
		cocktailLabel = new JLabel(cocktailIcon);
		
		//Create hover over
		thisHover = String.format(cocktailHoverOver,alcoholFactory.getCalories("Cocktail"),
				 alcoholFactory.getOuncesPerDrink("Cocktail"));
		cocktailLabel.setToolTipText(thisHover);
		
		//Add label
		alcoholAccessorMenu[3].add(cocktailLabel);
		
		// ----- END Alcohol Labels ----- //
		
		// -- Create buttons -> For INSERTS -- //
		addBeer = new JButton("+BEER");
		addBeer.addActionListener(this);
		styleButton( addBeer );
		alcoholAccessorMenu[0].add(addBeer);
		
		addWine = new JButton("+WINE");
		addWine.addActionListener(this);
		styleButton( addWine );
		alcoholAccessorMenu[1].add(addWine);
		
		addShot = new JButton("+SHOT");
		addShot.addActionListener(this);
		styleButton( addShot );
		alcoholAccessorMenu[2].add(addShot);
		
		addCocktail = new JButton("+COCKTAIL");
		addCocktail.addActionListener(this);
		styleButton( addCocktail );
		alcoholAccessorMenu[3].add(addCocktail);
		
		//Create buttons -> For REMOVALS
		removeBeer = new JButton("-BEER");
		removeBeer.addActionListener(this);
		styleButton( removeBeer );
		alcoholAccessorMenu[0].add(removeBeer);
		
		removeWine = new JButton("-WINE");
		removeWine.addActionListener(this);
		styleButton( removeWine );
		alcoholAccessorMenu[1].add(removeWine);
		
		removeShot = new JButton("-SHOT");
		removeShot.addActionListener(this);
		styleButton( removeShot );
		alcoholAccessorMenu[2].add(removeShot);
		
		removeCocktail = new JButton("-COCKTAIL");
		removeCocktail.addActionListener(this);
		styleButton( removeCocktail );
		alcoholAccessorMenu[3].add(removeCocktail);
		
		//Navigation Button
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		styleButton( returnButton );
		
		healthReportButton = new JButton("Health Report");
		healthReportButton.addActionListener(this);
		styleButton( healthReportButton );

		quizButton = new JButton("Quiz");
		quizButton.addActionListener(this);
		styleButton( quizButton );
		
	}
	
	//Create the GUI
	private void initGUI()
	{

		System.out.println("MainActivity::initGUI()");
		//Reset all text components
		beerLabel.setText(consumptionQuantityChar + participant.getCurrentBeers());
		wineLabel.setText(consumptionQuantityChar + participant.getCurrentWine());
		shotLabel.setText(consumptionQuantityChar + participant.getCurrentShots());
		cocktailLabel.setText(consumptionQuantityChar + participant.getCurrentCocktails());
		
		
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
		
		container = application.getMainFrame().getContentPane();
		
		//Add labels for participant
    	DecimalFormat tempBAC = new DecimalFormat("#0.0000");
    	String newBAC = tempBAC.format(participant.getCurrentBAC());
    	
		updateBACText(newBAC);
		participantInfo.add(bacLabel);
		updateCaloriesText(0);
		participantInfo.add(caloriesLabel);
		participantInfo.setBackground(Color.gray);
		
		//Add buttons to navigation
		navigation.add(returnButton);
		navigation.add(healthReportButton);
		navigation.add(quizButton);
		navigation.setBackground(Color.LIGHT_GRAY);
		
		//Add to the top of the east panel
		alcoholMenu.add(participantInfo);
		
		//Add all flow menus to side panel box layout
		for(int i = 0; i < alcoholAccessorMenu.length; i++)
		{
			alcoholAccessorMenu[0].setAlignmentY(Component.LEFT_ALIGNMENT);
			alcoholMenu.add(alcoholAccessorMenu[i]);
		}
		
		alcoholMenu.add(navigation);
		
		//Work with container
		container.setLayout(new BorderLayout());
		container.add(alcoholMenu, BorderLayout.EAST);
	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		// System.out.println("MainActivity::paintComponent()");
		
		super.paintComponent(thisGraphic);
		
		Graphics2D rectGraphic = (Graphics2D) thisGraphic;
		
		//Set color for the base fill and draw the rectangle behind the figure
		rectGraphic.setColor(Color.black);
		rectGraphic.fillRect(bodyPositionX, bodyPositionY, bodyFigure.getWidth(this), bodyFigure.getHeight(this));
		
		//Draw image layer one
		thisGraphic.drawImage(bodyFigure, bodyPositionX, bodyPositionY, this);
		
		//Updates the fill for the background color
		updateBodyFill(rectGraphic);
		
		//Draw image layer two
		thisGraphic.drawImage(bodyFigure, bodyPositionX, bodyPositionY, this);
		
		//Draw layer three -> multiple layers prevent from access fill being
		//shown on the edges
		thisGraphic.drawImage(bodyFigure, bodyPositionX, bodyPositionY, this);
		thisGraphic.drawImage(bodyPegs, bodyPositionX-150, bodyPositionY-5, this);
	}
	
	private int getBACState()
	{
		// System.out.println("MainActivity::getBACState()");
		//Decide fill color based on heights, etc
		if(participant.getCurrentBAC() < drivingLimit)
		{
			return 1;
		}
		else if(participant.getCurrentBAC() >= drivingLimit && participant.getCurrentBAC() < limitTwo)
		{
			return 2;
		}
		else if(participant.getCurrentBAC() >= limitTwo && participant.getCurrentBAC() < limitThree)
		{
			return 3;
		}
		else if(participant.getCurrentBAC() >= limitThree)
		{
			return 4;
		}
		
		return -1;
	}
	
	private void updateBodyFill(Graphics2D rectGraphic)
	{
		// System.out.println("MainActivity::updateBodyFill()");
		//Calculate the max height
		final double maxHeight = (double) bodyFigure.getHeight(this);
		
		//Calculate height of fill rect
		int bodyFillHeight = (int)((participant.getCurrentBAC() / maxBAC ) * maxHeight);
		
		//Decide fill color based on heights, etc
		if(getBACState() == 1)
		{
			rectGraphic.setColor(Color.green);
		}
		else if(getBACState() == 2)
		{
			rectGraphic.setColor(Color.yellow);
		}
		else if(getBACState() == 3)
		{
			rectGraphic.setColor(Color.orange);
		}
		else if(getBACState() == 4)
		{
			rectGraphic.setColor(Color.red);
		}
		
		//Draw rect using simple percentage-height formulation
		if(participant.getCurrentBAC() <= maxBAC)
		{
			//Fill dynamic rect based on current height needed
			rectGraphic.fillRect(bodyPositionX, (int) (bodyPositionY + maxHeight - bodyFillHeight), 
								 bodyFigure.getWidth(this), bodyFillHeight);
		}
		
		//We don't want to draw passed the bounds, this sets our threshold at the max
		else
		{
			rectGraphic.fillRect(bodyPositionX, bodyPositionY, bodyFigure.getWidth(this), bodyFigure.getHeight(this));
		}
	}
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		System.out.println("MainActivity::activate()");
		//Re-initialize the GUI
		initGUI();
		
		//Add to content pane
		application.getMainFrame().getContentPane().add(this);
		container.setVisible(true);
	}
	
	//Call before changing activities
	public void deactivate()
	{
		System.out.println("MainActivity::deactivate()");
		container.removeAll();
		application.getMainFrame().getContentPane().remove(this);
		container.setVisible(false);
	}
	
	//Start the activity processing
	public void begin() throws InterruptedException
	{
		// System.out.println("MainActivity::begin()");	
		repaint();
		Thread.sleep(10);
	}
	
	//Set the participant
	public void setParticipant(Participant passedParticipant)
	{
		System.out.println("MainActivity::setParticipant()");
		participant = passedParticipant;
		bacLabel.setText(bacLabelString + participant.getCurrentBAC());
		caloriesLabel.setText(caloriesLabelString + participant.getCurrentCalories());
	}
	
	//Implemented Action Listener Function
	public void actionPerformed(ActionEvent evt) 
	{

        String command = evt.getActionCommand();
		System.out.println( "MainActivity::actionPerformed() with command " + command );
        
        //If user chooses to add or remove Beer
        if (command.equals("+BEER"))
        {
        	addAlcohol(AlcoholFactory.ALCOHOL_TYPE.BEER);
        }
        if(command.equals("-BEER") && participant.getCurrentBeers() > 0)
        {
        	removeAlcohol(AlcoholFactory.ALCOHOL_TYPE.BEER);
        }
        
        //If user chooses to add or remove Wine
        if (command.equals("+WINE"))
        {
        	addAlcohol(AlcoholFactory.ALCOHOL_TYPE.WINE);
        }
        if(command.equals("-WINE") && participant.getCurrentWine() > 0)
        {
        	removeAlcohol(AlcoholFactory.ALCOHOL_TYPE.WINE);
        }
        
        //If user chooses to add or remove a shot
        if (command.equals("+SHOT"))
        {
        	addAlcohol(AlcoholFactory.ALCOHOL_TYPE.SHOT);
        }
        if(command.equals("-SHOT") && participant.getCurrentShots() > 0)
        {
        	removeAlcohol(AlcoholFactory.ALCOHOL_TYPE.SHOT);
        }
        //If user chooses to add or remove a shot
        if (command.equals("+COCKTAIL"))
        {
        	addAlcohol(AlcoholFactory.ALCOHOL_TYPE.COCKTAIL);
        }
        if(command.equals("-COCKTAIL") && participant.getCurrentCocktails() > 0)
        {
        	removeAlcohol(AlcoholFactory.ALCOHOL_TYPE.COCKTAIL);
        }
        
        //Handles navigation between activities if necessary
        handleNavigation(evt, command);
       

     } // end actionPerformed()
	
	//Handles navigation between activities
	private void handleNavigation(ActionEvent evt, String command)
	{
		System.out.println("MainActivity::handleNavigation()");

        //----- Navigation ----- //
        if(command.equals("Return"))	//--> Returns to MainActivity
        {	
        	deactivate();
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.TITLE);
        }
        else if(command.equals("Health Report"))	//--> Navigates to HealthReportActivity
        {
        	
        	deactivate();
        	healthReport.setParticipant(participant);
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_REPORT);
        }
        else if(command.equals("Quiz"))	//--> Navigates to QuizActivity
        {
        	// quizActivity.setParticipant(participant);
        	deactivate();
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.QUIZ);
        }
	}
	
	private void addAlcohol(AlcoholFactory.ALCOHOL_TYPE whichAlcohol)
	{
		System.out.println("MainActivity::addAlcohol()");
		//If maximum alcohol is reached
		if(participant.getCurrentBAC() >= .3 )
		{
			return;
		}
		
		//Decide which alcohol to alter, update participant values
		//and update corresponding labels
		switch(whichAlcohol)
		{
			case BEER:
	        	//Give participant the beers
	        	participant.setCurrentBeers(participant.getCurrentBeers() + 1);
	        	beerLabel.setText(consumptionQuantityChar + participant.getCurrentBeers());
	        	break;
	        	
			case WINE:
	        	//Give participant the beers
	        	participant.setCurrentWine(participant.getCurrentWine() + 1);
	        	wineLabel.setText(consumptionQuantityChar + participant.getCurrentWine());
	        	break;
	        	
			case SHOT:
	        	//Give participant the beers
	        	participant.setCurrentShots(participant.getCurrentShots() + 1);
	        	shotLabel.setText(consumptionQuantityChar + participant.getCurrentShots());
	        	break;
	        	
			case COCKTAIL:
	        	//Give participant the beers
	        	participant.setCurrentCocktails(participant.getCurrentCocktails() + 1);
	        	cocktailLabel.setText(consumptionQuantityChar + participant.getCurrentCocktails());
	        	break;
			
		}
		
    	//Set participant calories and BAC
    	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
    	participant.setCurrentCalories(HealthCalculator.caluclateCalories(participant));
    	
    	DecimalFormat tempBAC = new DecimalFormat("#0.0000");
    	String newBAC = tempBAC.format(participant.getCurrentBAC());
    	
    	//Set a new text
    	updateBACText(newBAC);
    	updateCaloriesText(participant.getCurrentCalories()); 
	}
	
	private void removeAlcohol(AlcoholFactory.ALCOHOL_TYPE whichAlcohol)
	{
		System.out.println("MainActivity::removeAlcohol()");
		//Decide which alcohol to alter
		switch(whichAlcohol)
		{
			case BEER:
	        	//Give participant the beers
	        	participant.setCurrentBeers(participant.getCurrentBeers() - 1);
	        	beerLabel.setText(consumptionQuantityChar + participant.getCurrentBeers());
	        	break;
	        	
			case WINE:
	        	//Give participant the beers
	        	participant.setCurrentWine(participant.getCurrentWine() - 1);
	        	wineLabel.setText(consumptionQuantityChar + participant.getCurrentWine());
	        	break;
	        	
			case SHOT:
	        	//Give participant the beers
	        	participant.setCurrentShots(participant.getCurrentShots() - 1);
	        	shotLabel.setText(consumptionQuantityChar + participant.getCurrentShots());
	        	break;
	        	
			case COCKTAIL:
	        	//Give participant the beers
	        	participant.setCurrentCocktails(participant.getCurrentCocktails() - 1);
	        	cocktailLabel.setText(consumptionQuantityChar + participant.getCurrentCocktails());
	        	break;
			
		}
		
    	//Set participant calories and BAC
    	participant.setCurrentBAC(HealthCalculator.calculateBAC(participant));
    	participant.setCurrentCalories(HealthCalculator.caluclateCalories(participant));
    	
    	DecimalFormat tempBAC = new DecimalFormat("#0.0000");
    	String newBAC = tempBAC.format(participant.getCurrentBAC());
    	
    	//Set a new text
    	updateBACText(newBAC);
    	updateCaloriesText(participant.getCurrentCalories()); 
	}
	
	private void updateBACText(String participantBAC)
	{
		// System.out.println("MainActivity::updateBACText()");
		String result = bacLabelString + participantBAC + fontHtmlCloser;
		bacLabel.setText(String.format(result, "white"));
		
	}
	
	private void updateCaloriesText(int participantCalories)
	{
		// System.out.println("MainActivity::updateCaloriesText()");
		String result = caloriesLabelString + participantCalories + fontHtmlCloser;
		caloriesLabel.setText(String.format(result, "white"));
		
	}

	public void styleButton( JButton button ) {
		button.setBorderPainted( false );
		button.setFocusPainted( false );
		button.setContentAreaFilled( false );
		button.setOpaque( true );
		button.setBackground( new Color( 69, 50, 9 ) );
		button.setForeground( Color.WHITE );	
	}
}