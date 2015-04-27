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


public class QuizActivity extends JPanel implements ActionListener
{
	
	//Main application for Activity Navigation
	private Application application;
	private JPanel sidebar[];
	private Container container;
	
	//Starting positions for the body
	private final static int bodyPositionX = 175;
	private final static int bodyPositionY = 100;
	
	//Image for the body
	private Image bodyFigure;
	private Image bodyPegs;
	
	//Panel for holding participant info
	private JPanel participantInfo;
	private JPanel sidebarHolder;
	private JLabel bacLabel;
	private JLabel caloriesLabel;
	
	//JLabel for questions
	private int currentQuestion;
	private JLabel questionLabel;
	private JLabel answerA;
	private JLabel answerB;
	private JLabel answerC;
	private JLabel answerD;
	private JButton submitAnswer;
		
	//Activity Navigation
	private JPanel navigation;
	private JButton returnButton;
	private JButton healthReportButton;

	//BAC limits
	private final static double maxBAC = .30;
	private final static double drivingLimit = .08;
	private final static double limitTwo = .12;
	private final static double limitThree = .20;

	// String constants
	private final static String bacLabelString = "<html><font color = '%s'> MY BAC: ";
	private final static String caloriesLabelString = "<html><font color = '%s'> MY CALORIES: ";
	private final static String fontHtmlCloser = "</font></html>";
	
	//Body Image Files
	private final static String maleBodyFilepath = "../img/body_fill.png";
	private final static String femaleBodyFilepath = "../img/female-w-trans.png";
	private final static String bodyPegsFilepath = "../img/body_pegs.png";

	//Participant
	private Participant participant;
	
	//Quiz questions
	private Quiz quiz;
	

	//Constructor
	public QuizActivity(Application passedApplication) throws IOException
	{
		System.out.println("QuizActivity::QuizActivity()");
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;

		//counter
		currentQuestion = 0;
		
		//Create image of the male body
		bodyFigure = Toolkit.getDefaultToolkit().getImage(maleBodyFilepath);
		bodyPegs = Toolkit.getDefaultToolkit().getImage(bodyPegsFilepath);
		
		create();
	}
	
	//create all components of QuizActivity
	private void create() throws IOException
	{
		System.out.println("QuizActivity::create()");
		//Create the general accessor menu
		System.out.println("QuizActivity::create()::quizLayout");
		FlowLayout quizLayout = new FlowLayout();
		// quizLayout.setHgap(5);
		
		System.out.println("QuizActivity::create()::sidebar");
		sidebar = new JPanel[6];
		
		//Participant information with Flow Layout
		System.out.println("QuizActivity::create()::infoLayout");
		FlowLayout infoLayout = new FlowLayout();
		// infoLayout.setHgap(15);
		System.out.println("QuizActivity::create()::navigation");
		navigation = new JPanel(infoLayout);
		
		// panel on the right
		System.out.println("QuizActivity::create()::sidebarHolder, layout");
		sidebarHolder = new JPanel();
		sidebarHolder.setLayout(new BoxLayout(sidebarHolder, BoxLayout.Y_AXIS));
		
		//Create the labels, which later will be dynamic
		System.out.println("QuizActivity::create()::bacLabel, caloriesLabel");
		bacLabel = new JLabel(bacLabelString);
		caloriesLabel = new JLabel(caloriesLabelString);
		
		
		// ----- END Alcohol Labels ----- //
		
		// -- Create buttons -> For INSERTS -- //
		System.out.println("QuizActivity::create()::questionLabel");
		questionLabel = new JLabel("Question");
		// sidebar[0].add(questionLabel);

		System.out.println("QuizActivity::create()::answers A-D");
		answerA = new JLabel("Answer A");
		// sidebar[1].add(questionLabel);
		answerB = new JLabel("Answer B");
		// sidebar[2].add(questionLabel);
		answerC = new JLabel("Answer C");
		// sidebar[3].add(questionLabel);
		answerD = new JLabel("Answer D");
		// sidebar[4].add(questionLabel);

		System.out.println("QuizActivity::create()::next button");
		submitAnswer = new JButton("Next");
		submitAnswer.addActionListener(this);
		// sidebar[5].add(questionLabel);
		
		//Navigation Button
		System.out.println("QuizActivity::create()::return");
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		// navigation.add(returnButton);

		System.out.println("QuizActivity::create()::health report");
		healthReportButton = new JButton("Health Report");
		// navigation.add(healthReportButton);


	}

	public void showQuestion() {
		System.out.println("QuizActivity::showQuestion()");
		if ( currentQuestion == quiz.size() ) {
		
			System.out.println( quiz.DONE );
			questionLabel.setText( quiz.DONE );

			answerA.setText("");
			answerB.setText("");
			answerC.setText("");
			answerD.setText("");

		} else {

			Question cur = quiz.get(currentQuestion);
			int size = cur.size();

			questionLabel.setText( currentQuestion+1 + ". " + cur.getText() );

			if ( size > 1 )
				answerA.setText( cur.getChoice(0) );
			if ( size > 2 )
				answerB.setText( cur.getChoice(1) );
			if ( size > 3 )
				answerC.setText( cur.getChoice(2) );
			if ( size > 4 )
				answerD.setText( cur.getChoice(3) );

		}
		repaint();
	}
	
	//Create the GUI
	private void initGUI()
	{		
		System.out.println("QuizActivity::initGUI()");
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
		updateBACText(""+participant.getCurrentBAC());
		participantInfo.add(bacLabel);
		updateCaloriesText(participant.getCurrentCalories());
		participantInfo.add(caloriesLabel);
		participantInfo.setBackground(Color.gray);
		
		//Add buttons to navigation
		navigation.add(returnButton);
		navigation.add(healthReportButton);
		navigation.setBackground(Color.LIGHT_GRAY);
		
		//Add to the top of the east panel
		sidebarHolder.add(participantInfo);
		
		//Add all flow menus to side panel box layout
		for(int i = 0; i < sidebar.length; i++)
		{
			sidebar[0].setAlignmentY(Component.LEFT_ALIGNMENT);
			sidebarHolder.add(sidebar[i]);
		}
		
		sidebarHolder.add(navigation);
		
		//Work with container
		container.setLayout(new BorderLayout());
		container.add(sidebarHolder, BorderLayout.EAST);

		showQuestion();

	}
	
	@Override
	protected void paintComponent(Graphics thisGraphic)
	{
		// System.out.println("QuizActivity::paintComponent()");	
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
		// System.out.println("QuizActivity::getBACState()");
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
		System.out.println("QuizActivity::updateBodyFill()");
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
		System.out.println("QuizActivity::activate()");
		//Re-initialize the GUI
		initGUI();
		
		//Add to content pane
		application.getMainFrame().getContentPane().add(this);
		container.setVisible(true);
	}
	
	//Call before changing activities
	public void deactivate()
	{
		System.out.println("QuizActivity::deactivate()");
		container.removeAll();
		application.getMainFrame().getContentPane().remove(this);
		container.setVisible(false);
	}
	
	//Start the activity processing
	public void begin() throws InterruptedException
	{
		// System.out.println("QuizActivity::begin()");
		repaint();
		Thread.sleep(10);
	}
	
	//Set the participant
	public void setParticipant(Participant passedParticipant)
	{
		System.out.println("QuizActivity::setParticipant()");
		participant = passedParticipant;
		bacLabel.setText(bacLabelString + participant.getCurrentBAC());
		caloriesLabel.setText(caloriesLabelString + participant.getCurrentCalories());
	}
	
	//Implemented Action Listener Function
	public void actionPerformed(ActionEvent evt) 
	{
		System.out.println("QuizActivity::actionPerformed()");
        String command = evt.getActionCommand();
        
        //If start button is pressed we want to aquire
        //All data from the form
        if (command.equals("Next"))
        {
        	currentQuestion++;
        	showQuestion();
        }

        handleNavigation( evt, command );

     } // end actionPerformed()

	
	//Handles navigation between activities
	private void handleNavigation(ActionEvent evt, String command)
	{
		System.out.println("QuizActivity::handleNavigation()");
        //----- Navigation ----- //
        if(command.equals("Return"))	//--> Returns to MainActivity
        {
        	
        	deactivate();
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
        }
        else if(command.equals("Health Report"))	//--> Navigates to HealthReportActivity
        {
        	
        	deactivate();
        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_REPORT);
        }
	}

	private void updateBACText(String participantBAC)
	{
		// System.out.println("QuizActivity::updateBACText()");
		String result = bacLabelString + participantBAC + fontHtmlCloser;
		bacLabel.setText(String.format(result, "white"));
		
	}
	
	private void updateCaloriesText(int participantCalories)
	{
		// System.out.println("QuizActivity::updateCaloriesText()");
		String result = caloriesLabelString + participantCalories + fontHtmlCloser;
		caloriesLabel.setText(String.format(result, "white"));
		
	}
	


}
