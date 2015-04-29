
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.io.IOException;
  
public class QuizActivity extends JPanel implements ActionListener, Activity
{
      
    //Main application for Activity Navigation
    private Application application;
    private Container container;
      
    //JLabel for questions
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
    private JButton returnButton;
    private JButton healthReportButton;
      
    //Quiz
    private Quiz quiz;
    private int currentQuestion;
    private boolean isRationale;
    private static final int lineBreak = 70;
 
    //constants for absolute positioning
    private static final int offsetTop = 20;
    private static final int leftMargin = 100;
    private static final int inputLeft = 20;
    private static final int topMargin = 20;
    private static final int buttonWidth = 150;
    private static final int fullWidth = 600;
    private static final int lineHeight = 35;
  
  
    //Constructor
    public QuizActivity(Application passedApplication) throws IOException
    {
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
    public void create()
    {
        container = application.getMainFrame().getContentPane();
        container.setLayout(null);
          
        // Create buttons
        questionLabel = new JLabel("Question");
        rationaleLabel = new JLabel("");
        rightWrongLabel = new JLabel("");
  
        answerAButton = new JRadioButton("Answer A");
        answerBButton = new JRadioButton("Answer B");
        answerCButton = new JRadioButton("Answer C");
        answerDButton = new JRadioButton("Answer D");
  
        answers = new ButtonGroup();
  
        submitAnswer = new JButton("Next");
        submitAnswer.addActionListener(this);
        ComponentStyler.styleButton( submitAnswer );
          
        //Navigation Buttons
        returnButton = new JButton("Return");
        returnButton.addActionListener(this);
        ComponentStyler.styleButton( returnButton );
  
        healthReportButton = new JButton("Health Report");
        returnButton.addActionListener(this);
        ComponentStyler.styleButton( healthReportButton );
  
    }
  
      
    //Create the GUI
    public void initGUI()
    {       
  
        // add answers to buttonGroup
        answers.add(answerAButton);
        answers.add(answerBButton);
        answers.add(answerCButton);
        answers.add(answerDButton);
 
 
        // SET BOUNDS
 
        // question
        questionLabel.setBounds(leftMargin, 0*lineHeight + offsetTop, fullWidth, lineHeight );
         
        // answers
        answerAButton.setBounds(leftMargin, 1*lineHeight + offsetTop, fullWidth, lineHeight );
        answerBButton.setBounds(leftMargin, 2*lineHeight + offsetTop, fullWidth, lineHeight );
        answerCButton.setBounds(leftMargin, 3*lineHeight + offsetTop, fullWidth, lineHeight );
        answerDButton.setBounds(leftMargin, 4*lineHeight + offsetTop, fullWidth, lineHeight );
 
        // next
        submitAnswer.setBounds(leftMargin, 5*lineHeight + topMargin + offsetTop, buttonWidth, lineHeight );
 
        // rationale
        rightWrongLabel.setBounds(leftMargin, 7*lineHeight + offsetTop, fullWidth, lineHeight );
        rationaleLabel.setBounds(leftMargin, 8*lineHeight + offsetTop, fullWidth, lineHeight*2 );
 
        // navigation
        returnButton.setBounds(leftMargin, 14*lineHeight + offsetTop, buttonWidth, lineHeight );
        healthReportButton.setBounds(leftMargin+buttonWidth+inputLeft, 14*lineHeight + offsetTop, buttonWidth, lineHeight );
 
 
        // ADD ALL TO VIEW
 
        container.setLayout( null );
        container.add(questionLabel);
        container.add(answerAButton);
        container.add(answerBButton);
        container.add(answerCButton);
        container.add(answerDButton);
        container.add(submitAnswer);
        container.add(rightWrongLabel);
        container.add(rationaleLabel);
        container.add(returnButton);
        container.add(healthReportButton);
  
        container.setVisible(true);
 
        // SHOW FIRST QUESTION
        showQuestion( isRationale );
    }
 
    //Clears answer rationale
    public void clearRationale() {
        rightWrongLabel.setText(" ");
        rationaleLabel.setText(" ");
    }
      
    public void showQuestion( boolean rationale ) {
          
        // answer checking
        if ( isRationale ) 
        {
            String userAnswer = "", reason = "", rightWrong="";
              
            //Gets selected radio button
            if (answerAButton.isSelected())
                userAnswer = answerAButton.getText();
            if (answerBButton.isSelected())
                userAnswer = answerBButton.getText();
            if (answerCButton.isSelected())
                userAnswer = answerCButton.getText();
            if (answerDButton.isSelected())
                userAnswer = answerDButton.getText();
              
            //Gets current Question that user is answering
            Question current = quiz.get(currentQuestion);
              
              
            //If user answer is the correct answer, increment correct
            if (current.isCorrect(userAnswer))
            {
                rightWrong = "Good Job!";
                reason = quiz.get(currentQuestion).getRationale();
                quiz.incrementCorrect();
            }
            else {
                rightWrong = "Oops!";
                reason = quiz.get(currentQuestion).getRationale();
            }
            rightWrongLabel.setText(rightWrong);
                  
            //If line length is too long, put on multiple lines
            if (reason.length() >= lineBreak)
            {
                String reasonFirstHalf = reason.substring(0, reason.length()/2);
                int index = reasonFirstHalf.lastIndexOf(' ');
                reasonFirstHalf = reason.substring(0, index);
                String reasonSecondHalf = reason.substring(index, reason.length());
                rationaleLabel.setText("<html>" + reasonFirstHalf + "<br>" + reasonSecondHalf + "</html>");
                rationaleLabel.setBounds(leftMargin, 8*lineHeight + offsetTop, rationaleLabel.getPreferredSize().width, rationaleLabel.getPreferredSize().height );
            }
            else
                rationaleLabel.setText(reason);
 
              
         }
        else if ( currentQuestion == quiz.size() ) 
        {
            rightWrongLabel.setText( Quiz.DONE + " You got a " + quiz.getCorrect() + "/" + quiz.size() );
            rationaleLabel.setText( "Click return to go back." );
        } 
        else
        {
            Question cur = quiz.get(currentQuestion);
            int size = cur.size();
  
            clearRationale();
  
            // clear choices
            answerAButton.setSelected( false );
            answerBButton.setSelected( false );
            answerCButton.setSelected( false );
            answerDButton.setSelected( false );
             
            answers.clearSelection();
              
            // question text
            questionLabel.setText( currentQuestion+1 + ". " + cur.getText() );
  
            // adding answer choices, up to 4 choices
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
  
    //Paints everything
    @Override
    protected void paintComponent(Graphics thisGraphic)
    {
        super.paintComponent(thisGraphic);
          
        Graphics2D rectGraphic = (Graphics2D) thisGraphic;
    }
      
      
    //Makes panel visible and sets applications
    //current activity
    public void activate()
    {
        currentQuestion = 0;
        quiz.setNumCorrect(0);
  
        // Re-initialize the GUI
        initGUI();
          
        // Add to content pane
        application.getMainFrame().getContentPane().add(this);
        container.setVisible(true);
    }
      
    //Call before changing activities
    public void deactivate()
    {
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
        //----- Navigation ----- //
        if( command.equals("Return") )    //--> Returns to MainActivity
        {
            deactivate();
            application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
        }
        else if( command.equals("Health Report") )    //--> Navigates to HealthReportActivity
        {
            deactivate();
            application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_REPORT);
        }
    }
      
  
}