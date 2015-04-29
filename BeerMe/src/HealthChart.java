import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class HealthChart extends JPanel implements Activity, ActionListener
{
		//Needs the Application
		Application application;
		
		//Main Container
		Container container;
		
		//Dynamic diagram for bodily influence
		private Chart bodyDiagram;
		
		//Label for description
		private JLabel helpMessage;
		
		//Labels to hold values
		private JLabel beerLabel;
		private JLabel wineLabel;
		private JLabel shotLabel;
		private JLabel cocktailLabel;
		private static final int labelRowOneY = 10;
		private static final int labelRowTwoY = 100;
		private static final int labelOneX = 50;
		private static final int labelTwoX = 60;
		private static final int labelThreeX = 150;
		private static final int labelFourX = 155;
		
		//Background
		private Image backgroundImage;
		private static final String backgroundFilePath = "img/Chart_Background.png";

		//Image Icons
		private DescriptiveIcon liver;
		private static final String liverFilePath = "img/liver.png";
		private static final String liverDescriptionFilePath = "img/LiverDescription.png";
		private DescriptiveIcon kidney;
		private static final String kidneyFilePath = "img/kidney.png";
		private static final String kidneyDescriptionFilePath = "img/KidneyDescription.png";
		private DescriptiveIcon heart;
		private static final String heartFilePath = "img/heart.png";
		private static final String heartDescriptionFilePath = "img/HeartDescription.png";
		private DescriptiveIcon brain;
		private static final String brainFilePath = "img/brain.png";
		private static final String brainDescriptionFilePath = "img/BrainDescription.png";
		
		//JLabel Texts
		private static final String contentDescription = "<html><font color = 'white'>"
														  + "Hover over images for additional details"
														  + "</font></html>";
		
		//Chart for Display
		private JButton returnButton;
		
		//Main participant
		private Participant participant;
		
		//Constructor for HealthChart Activity
		public HealthChart(Application passedApplication)
		{
			application = passedApplication;
			
			//Create the background image
			backgroundImage = Toolkit.getDefaultToolkit().getImage(backgroundFilePath);
			
			//Create components that are needed
			createIcons();
			create();
		}
		
		//Create all the icons
		private void createIcons()
		{
			liver = new DescriptiveIcon(liverFilePath, liverDescriptionFilePath);
			kidney = new DescriptiveIcon(kidneyFilePath, kidneyDescriptionFilePath);
			heart = new DescriptiveIcon(heartFilePath, heartDescriptionFilePath);
			brain = new DescriptiveIcon(brainFilePath, brainDescriptionFilePath);
			
		}
		
		//Create all components
		public void create()
		{
			container = application.getMainFrame().getContentPane();
	
			container.setLayout(new BorderLayout());
			
			returnButton = new JButton("Return");
			returnButton.addActionListener(this);
			
			helpMessage = new JLabel(contentDescription);
			
			if(participant == null)
			{
				add(returnButton);
				return;
			}
			
		}
		
		//Init the GUI
		public void initGUI()
		{	
			remove(returnButton);
			
			this.setLayout(null);
			
			/*
			 * We did not create constants here because the
			 * absolute positioning took a lot of guess and
			 * checking in order to find minute differences
			 * in areas, if there were more common values
			 * we would have created constants
			 */
			//Set bounds of all the descriptive icons
			liver.setBounds(Application.screenWidth/2 - 65, 0, 125, 105);
			kidney.setBounds(Application.screenWidth-155, Application.screenHeight/2-100, 125, 150);
			heart.setBounds(Application.screenWidth/2-45, Application.screenHeight - 160, 100, 150);
			brain.setBounds(0, Application.screenHeight/2-85, 150, 125);
			
			//Set bounds of corresponding descriptions
			liver.setDescriptonPosition(liver.getX() + liver.getWidth() + 3, liver.getY());
			kidney.setDescriptonPosition(kidney.getX() - kidney.getWidth() + 15, kidney.getY() + kidney.getHeight());
			heart.setDescriptonPosition(heart.getX() + heart.getWidth() + 3, heart.getY()-65);
			brain.setDescriptonPosition(brain.getX() + brain.getWidth() + 3, brain.getY()-25);
			
			//Other components
			returnButton.setBounds(0, Application.screenHeight - 50, 100, 25);
			helpMessage.setBounds(10,0, 300, 50);
			
			//Add the descriptive icons
			add(liver);
			add(kidney);
			add(heart);
			add(brain);
			add(returnButton);
			add(helpMessage);
		}
		
		
		//Makes panel visible and sets applications
		//current activity
		public void activate()
		{
			//Re-initialize the GUI
			initGUI();
			
			//Add to content pane
			application.getMainFrame().getContentPane().add(this);
			container.setVisible(true);
		}
		
		//Turns off this activity
		public void deactivate()
		{
			container.removeAll();
			application.getMainFrame().getContentPane().remove(this);
			container.setVisible(false);
		}
		
		//Sets the chart to passed chart
		public void setChart(Chart passedChart)
		{
			bodyDiagram = passedChart;
			
		}
		
		//Given labels add them to the scheme
		public void giveLabels(ArrayList<JLabel> labels)
		{
			beerLabel = new JLabel( labels.get(0).getIcon() );
			beerLabel.setText(labels.get(0).getText());
			beerLabel.setBounds(labelRowOneY,labelOneX,beerLabel.getPreferredSize().height,beerLabel.getPreferredSize().height);
			add(beerLabel);
			
			wineLabel = new JLabel( labels.get(1).getIcon() );
			wineLabel.setText(labels.get(1).getText());
			wineLabel.setBounds(labelRowTwoY,labelTwoX,wineLabel.getPreferredSize().width,wineLabel.getPreferredSize().height);
			add(wineLabel);
			
			shotLabel = new JLabel ( labels.get(2).getIcon() );
			shotLabel.setText( labels.get(2).getText() );
			shotLabel.setBounds(labelRowOneY,labelThreeX,shotLabel.getPreferredSize().width , shotLabel.getPreferredSize().height);
			add(shotLabel);
			
			cocktailLabel = new JLabel ( labels.get(3).getIcon() );
			cocktailLabel.setText( labels.get(3).getText() );
			cocktailLabel.setBounds(labelRowTwoY,labelFourX,cocktailLabel.getPreferredSize().width,cocktailLabel.getPreferredSize().height);
			add(cocktailLabel);
		}

		//Implemented Action Listener Function
		public void actionPerformed(ActionEvent evt) 
		{
	        String command = evt.getActionCommand();
	        
	        //If user chooses to add or remove Beer
	        if (command.equals("Return"))
	        {
	        	deactivate();
	        	
	        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_REPORT);
	        }
	        
		}
		
		//Begins processing activity
		public void begin() throws InterruptedException
		{
			repaint();
			Thread.sleep(10);
		}

		//Do any drawing in this method
		public void update(Graphics thisGraphic)
		{	
			//Draw the background image
			thisGraphic.drawImage(backgroundImage, 0, 0, this);
			
			//Draw the diagram
			Graphics2D painter = (Graphics2D) thisGraphic;
			bodyDiagram.paint(painter);
			
			
			// ----- Show descriptions if icon is hovered over ----- //
			if(liver.isHovered())
			{
				liver.showDescription(thisGraphic, this);
			}
			else if(heart.isHovered())
			{
				heart.showDescription(thisGraphic, this);
			}
			else if(brain.isHovered())
			{
				brain.showDescription(thisGraphic, this);
			}
			else if(kidney.isHovered())
			{
				kidney.showDescription(thisGraphic, this);
			}
		}
		
		//Calls repaint
		@Override
		protected void paintComponent(Graphics thisGraphic)
		{
			super.paintComponent(thisGraphic);
			update(thisGraphic);
		}

}
