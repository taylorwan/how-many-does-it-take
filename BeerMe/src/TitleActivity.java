import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
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
public class TitleActivity extends JPanel implements ActionListener, Activity
{
    private Application application;
     
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
    private JLabel disclaimer;
 
     
    //Constant Characters for radio buttons
    private static final String maleString = "Male";
    private static final String femaleString = "Female";
    private static final String startString = "START";
     
    //Filepath for titleActvity
	final String titleActivityFilePath = "img/intro.jpg";
     
    private Container container;
     
    public TitleActivity(Application passedApplication) throws IOException
    {
        //Set the application and other important components
        application = passedApplication;
         
        //Create background
        backgroundImage = Toolkit.getDefaultToolkit().getImage(titleActivityFilePath);
         
        //Create the GUI
        create();
        initGUI();
    }
     
    public void create() {
 
        //Create input text fields
        weightInputBox = new JTextField(15);
        nameInputBox = new JTextField(15);
         
         
        //Buttons and button group
        startButton = new JButton(startString);
        startButton.addActionListener(this);
        ComponentStyler.styleButton( startButton );
 
        // gender buttons
        genderButtons = new ButtonGroup();
        maleButton = new JRadioButton(maleString);
        ComponentStyler.styleLabel( maleButton );
        maleButton.setSelected(true);
        femaleButton = new JRadioButton(femaleString);
        ComponentStyler.styleLabel( femaleButton );
         
        //Create all labels
        nameLabel = new JLabel("NAME");
        ComponentStyler.styleLabel( nameLabel );
        weightLabel = new JLabel("WEIGHT");
        ComponentStyler.styleLabel( weightLabel );
        genderLabel = new JLabel("GENDER");
        ComponentStyler.styleLabel( genderLabel );
        hourLabel = new JLabel("HOURS *");
        ComponentStyler.styleLabel( hourLabel );
        disclaimer = new JLabel("* Hours you plan to drink");
        disclaimer.setForeground(Color.WHITE);
         
        //This container will hold the title layout
        container = application.getMainFrame().getContentPane();
        container.setLayout(null);
         
        //Drop down menu
        String hourOptions[] = {"1","2","3","4","5","6","7","8","9"};
        hoursMenu = new JComboBox<String>(hourOptions);
    }
 
 
    public void initGUI()
    {
        application.getMainFrame().getContentPane().add(this);
        container = application.getMainFrame().getContentPane();
        setLayout(null);
         
        genderButtons.add(maleButton);
        genderButtons.add(femaleButton);
         
        // positions
        int offsetTop = 230;
        int leftMargin = 230;
        int inputLeft = 20;
        int topMargin = 20;
        int labelWidth = 100;
        int inputWidth = 200;
        int lineHeight = 40;
 
        // name
        nameLabel.setBounds( leftMargin, 1*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
        nameInputBox.setBounds( leftMargin + labelWidth + inputLeft, 1*lineHeight + topMargin + offsetTop, inputWidth, lineHeight);
 
        // weight
        weightLabel.setBounds( leftMargin, 2*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
        weightInputBox.setBounds( leftMargin + labelWidth + inputLeft, 2*lineHeight + topMargin + offsetTop, inputWidth, lineHeight);
         
        // gender
        genderLabel.setBounds( leftMargin, 3*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
        maleButton.setBounds( leftMargin + labelWidth + inputLeft, 3*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
        femaleButton.setBounds( leftMargin + labelWidth + inputLeft + labelWidth, 3*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
 
        // hours
        hourLabel.setBounds( leftMargin, 4*lineHeight + topMargin + offsetTop, labelWidth, lineHeight);
        hoursMenu.setBounds( leftMargin + labelWidth + inputLeft, 4*lineHeight + topMargin + offsetTop, inputWidth, lineHeight);
 
        // start button
        startButton.setBounds( leftMargin, 5*lineHeight + 2*topMargin + offsetTop, inputWidth + labelWidth + inputLeft, lineHeight);
 
        // disclaimer
        disclaimer.setBounds( leftMargin + inputWidth/2, 6*lineHeight + 3*topMargin + offsetTop, inputWidth + labelWidth + inputLeft, lineHeight);
 
        add(nameLabel);
        add(nameInputBox);
        add(weightLabel);
        add(weightInputBox);
        add(genderLabel);
        add(maleButton);
        add(femaleButton);
        add(hourLabel);
        add(hoursMenu);
        add(startButton);
        add(disclaimer);
         
    }
     
 
 
    public void activate()
    {
        //Re-initialize the GUI
        initGUI();
         
        application.getMainFrame().getContentPane().add(this);
         
        //Make container visible
        container.setVisible(true);
    }
     
    //Call before changing activities
    public void deactivate()
    {
        container.removeAll();
        application.getMainFrame().getContentPane().remove(this);
        container.setVisible(false);
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
        if (command.equals("START"))
        {
            if( weightIsGood() && nameIsGood() )
            {
                //Create Participant variables
                String pName = nameInputBox.getText();
                int pWeight = Integer.parseInt( weightInputBox.getText() );
                double hoursDrinking = Double.parseDouble((String) hoursMenu.getSelectedItem());
 
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
                Participant participant = new Participant(pName, pWeight, pGender, hoursDrinking);
                 
                
                MainActivity mainActivity = (MainActivity) application.getActivity("mainActivity");
                
                //Give the main activity the participant
                mainActivity.setParticipant(participant);
                 
                deactivate();
                //Set new activity
                application.setCurrentActivity(Application.CURRENT_ACTIVITY.MAIN);
                 
            }
             
        }
 
     } // end actionPerformed()
}