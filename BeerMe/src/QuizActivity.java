import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
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
	
	//JLabel for questions
	private JPanel sidebarHolder;
	private int currentQuestion;
	private JLabel questionLabel;
	private JLabel answerA;
	private JLabel answerB;
	private JLabel answerC;
	private JLabel answerD;
	private ButtonGroup answers;
	private JRadioButton answerAButton;
	private JRadioButton answerBButton;
	private JRadioButton answerCButton;
	private JRadioButton answerDButton;
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

		//quiz
		quiz = new Quiz();
		
		//Create image of the male body
		bodyFigure = Toolkit.getDefaultToolkit().getImage(maleBodyFilepath);
		bodyPegs = Toolkit.getDefaultToolkit().getImage(bodyPegsFilepath);
		
		create();
	}
	
	//create all components of QuizActivity
	private void create() throws IOException
	{
		System.out.println("QuizActivity::create()");
		container = application.getMainFrame().getContentPane();

		//Create the general accessor menu
		FlowLayout quizLayout = new FlowLayout();
		// quizLayout.setHgap(5);
		
		// sidebar = new JPanel[6];
		
		//Participant information with Flow Layout
		FlowLayout infoLayout = new FlowLayout();
		// infoLayout.setHgap(15);
		navigation = new JPanel(infoLayout);
		
		// panel on the right
		sidebarHolder = new JPanel();
		sidebarHolder.setLayout(new BoxLayout(sidebarHolder, BoxLayout.Y_AXIS));
		
		
		// ----- END Alcohol Labels ----- //
		
		// -- Create buttons -> For INSERTS -- //
		questionLabel = new JLabel("Question");
		Dimension dim = new Dimension( 400, 20 );
		questionLabel.setPreferredSize( dim );
		
		answerA = new JLabel("Answer A");
		answerB = new JLabel("Answer B");
		answerC = new JLabel("Answer C");
		answerD = new JLabel("Answer D");

		answerAButton = new JRadioButton("Answer A");
		answerBButton = new JRadioButton("Answer B");
		answerCButton = new JRadioButton("Answer C");
		answerDButton = new JRadioButton("Answer D");

		answers = new ButtonGroup();

		submitAnswer = new JButton("Next");
		submitAnswer.addActionListener(this);
		styleButton( submitAnswer );
		
		//Navigation Buttons
		returnButton = new JButton("Return");
		returnButton.addActionListener(this);
		styleButton( returnButton );

		healthReportButton = new JButton("Health Report");
		returnButton.addActionListener(this);
		styleButton( returnButton );

	}

	
	//Create the GUI
	private void initGUI()
	{		
		System.out.println("QuizActivity::initGUI()");

		answers.add(answerAButton);
		answers.add(answerBButton);
		answers.add(answerCButton);
		answers.add(answerDButton);

		sidebarHolder.add(questionLabel);
		sidebarHolder.add(answerAButton);
		sidebarHolder.add(answerBButton);
		sidebarHolder.add(answerCButton);
		sidebarHolder.add(answerDButton);
		sidebarHolder.add(submitAnswer);
		sidebarHolder.setAlignmentY(Component.LEFT_ALIGNMENT);

		//Add all flow menus to side panel box layout
		// for(int i = 0; i < sidebar.length; i++)
		// {
		// 	sidebar[i].setAlignmentY(Component.LEFT_ALIGNMENT);
		// 	sidebarHolder.add(sidebar[i]);
		// }

		//Add buttons to navigation
		navigation.add(returnButton);
		navigation.add(healthReportButton);
		navigation.setBackground(Color.LIGHT_GRAY);

		sidebarHolder.add(navigation);

		//Work with container
		container.setLayout(new BorderLayout());
		container.add(sidebarHolder, BorderLayout.EAST);

		showQuestion();

	}
	
	public void showQuestion() {
		System.out.println("QuizActivity::showQuestion()");
		
		if ( currentQuestion == quiz.size() ) 
		{
		
			questionLabel.setText( quiz.DONE + quiz.getCorrect() + "/" + quiz.size() );

			answerAButton.setText("");
			answerBButton.setText("");
			answerCButton.setText("");
			answerDButton.setText("");
			sidebarHolder.remove(submitAnswer);

		} 
		else 
		{
			Question cur = quiz.get(currentQuestion);
			int size = cur.size();

			answerAButton.setSelected( false );
			answerBButton.setSelected( false );
			answerCButton.setSelected( false );
			answerDButton.setSelected( false );

			System.out.print(cur);

			questionLabel.setText( currentQuestion+1 + ". " + cur.getText() );

			//if ( size > 1 )
				answerAButton.setText( cur.getChoice(0) );
			//if ( size > 2 )
				answerBButton.setText( cur.getChoice(1) );
			//if ( size > 3 )
				answerCButton.setText( cur.getChoice(2) );
			//if ( size > 4 )
				answerDButton.setText( cur.getChoice(3) );

		}
		repaint();
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
		// updateBodyFill(rectGraphic);
		
		//Draw image layer two
		thisGraphic.drawImage(bodyFigure, bodyPositionX, bodyPositionY, this);
		
		//Draw layer three -> multiple layers prevent from access fill being
		//shown on the edges
		thisGraphic.drawImage(bodyFigure, bodyPositionX, bodyPositionY, this);
		thisGraphic.drawImage(bodyPegs, bodyPositionX-150, bodyPositionY-5, this);
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
	
	public void styleButton( JButton button ) {
		button.setBorderPainted( false );
		button.setFocusPainted( false );
		button.setContentAreaFilled( false );
		button.setOpaque( true );
		button.setBackground( new Color( 69, 50, 9 ) );
		button.setForeground( Color.WHITE );	
	}

}
