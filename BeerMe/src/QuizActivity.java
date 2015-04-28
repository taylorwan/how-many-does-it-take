import java.awt.*;
import javax.swing.*;
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
	
	//JLabel for questions
	private JPanel sidebarHolder;
	private JLabel questionLabel;
	private JLabel rationaleLabel;
	private JLabel rightWrongLabel;
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
	
	//Quiz
	private Quiz quiz;
	private int currentQuestion;
	private boolean isRationale;


	//Constructor
	public QuizActivity(Application passedApplication) throws IOException
	{
		System.out.println("QuizActivity::QuizActivity()");
		//Set the application and use it to create 
		//a custom container exclusive to MainActivity
		application = passedApplication;

		//quiz & counter
		quiz = new Quiz();
		currentQuestion = 0;
		isRationale = false;
		
		create();
	}
	
	//create all components of QuizActivity
	private void create() throws IOException
	{
		System.out.println("QuizActivity::create()");
		container = application.getMainFrame().getContentPane();
		navigation = new JPanel();
		
		// panel on the right
		sidebarHolder = new JPanel();
		sidebarHolder.setLayout(new BoxLayout(sidebarHolder, BoxLayout.Y_AXIS));
		// sidebarHolder.setLayout( new BorderLayout() );
		// sidebarHolder.setLayout( new FlowLayout() );
		sidebarHolder.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		
		
		// ----- END Alcohol Labels ----- //
		
		// -- Create buttons -> For INSERTS -- //
		questionLabel = new JLabel("Question");
		rationaleLabel = new JLabel("");
		rightWrongLabel = new JLabel("");
		rationaleLabel.setForeground(Color.DARK_GRAY);
		rightWrongLabel.setForeground(Color.DARK_GRAY);

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
		styleButton( healthReportButton );

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
		sidebarHolder.add(rightWrongLabel);
		sidebarHolder.add(rationaleLabel);

		//Add buttons to navigation
		navigation.add(returnButton);
		navigation.add(healthReportButton);

		sidebarHolder.add(navigation);

		//Work with container
		container.setLayout(new FlowLayout());
		container.add(sidebarHolder);
		// container.add(temp, BoxLayout.Y_AXIS);

		container.setVisible(true);

		showQuestion( isRationale );
	}
	
	public void showQuestion( boolean rationale ) {
		System.out.println("QuizActivity::showQuestion()");
		
		if ( isRationale ) {
			System.out.println("QuizActivity::showQuestion()::rationale: " + quiz.get( currentQuestion ).getRationale());
			rationaleLabel.setText( "Good job!" );
			rationaleLabel.setText( quiz.get( currentQuestion ).getRationale() );
		}
		else if ( currentQuestion == quiz.size() ) 
		{
			questionLabel.setText( quiz.DONE );
			rationaleLabel.setText( "You got a " + quiz.getCorrect() + "/" + quiz.size() );

			sidebarHolder.remove(answerAButton);
			sidebarHolder.remove(answerBButton);
			sidebarHolder.remove(answerCButton);
			sidebarHolder.remove(answerDButton);
			sidebarHolder.remove(submitAnswer);

		} 
		else 
		{
			Question cur = quiz.get(currentQuestion);
			int size = cur.size();

			rationaleLabel.setText("");

			answerAButton.setSelected( false );
			answerBButton.setSelected( false );
			answerCButton.setSelected( false );
			answerDButton.setSelected( false );

			System.out.print(cur);

			questionLabel.setText( currentQuestion+1 + ". " + cur.getText() );

			if ( size > 0 )
				answerAButton.setText( cur.getChoice(0) );
			if ( size > 1 )
				answerBButton.setText( cur.getChoice(1) );
			if ( size > 2 )
				answerCButton.setText( cur.getChoice(2) );
			if ( size > 3 )
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
	}
	
	
	//Makes panel visible and sets applications
	//current activity
	public void activate()
	{
		System.out.println("QuizActivity::activate()");
		currentQuestion = 0;

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
        	if ( isRationale )
	        	currentQuestion++;
        	isRationale = !isRationale;
        	showQuestion( isRationale );
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
