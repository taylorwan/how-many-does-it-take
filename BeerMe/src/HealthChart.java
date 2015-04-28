import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
		
		
		//Chart for Display
		private JButton returnButton;
		
		//Main participant
		private Participant participant;
		
		public HealthChart(Application passedApplication)
		{
			System.out.println("HealthChart::HealthChart()");
			application = passedApplication;
			
			create();
		}
		
		
		public void create()
		{
			System.out.println("HealthChart::create()");
			container = application.getMainFrame().getContentPane();
	
			container.setLayout(new BorderLayout());
			
			returnButton = new JButton("Return");
			returnButton.addActionListener(this);
			
			if(participant == null)
			{
				add(returnButton);
				return;
			}
			
		}
		
		public void initGUI()
		{	
			System.out.println("HealthReport::initGUI()");
			remove(returnButton);
			container.add(returnButton, BorderLayout.SOUTH);
		}
		
		
		//Makes panel visible and sets applications
		//current activity
		public void activate()
		{
			System.out.println("HealthReport::activate()");
			//Re-initialize the GUI
			initGUI();
			
			//Add to content pane
			application.getMainFrame().getContentPane().add(this);
			container.setVisible(true);
		}
		
		
		public void deactivate()
		{
			System.out.println("HealthReport::deactivate()");
			container.removeAll();
			application.getMainFrame().getContentPane().remove(this);
			container.setVisible(false);
		}
		
		public void setChart(Chart passedChart)
		{
			bodyDiagram = passedChart;
			
		}

		//Implemented Action Listener Function
		public void actionPerformed(ActionEvent evt) 
		{
			System.out.println("HealthReport::actionPerformed()");
	        String command = evt.getActionCommand();
	        
	        //If user chooses to add or remove Beer
	        if (command.equals("Return"))
	        {
	        	deactivate();
	        	
	        	application.setCurrentActivity(Application.CURRENT_ACTIVITY.HEALTH_REPORT);
	        }
	        
		}
		
		public void begin() throws InterruptedException
		{
			System.out.println("Inside HealthReport::Begin()");
			repaint();
			Thread.sleep(10);
		}

		public void update(Graphics2D thisGraphic)
		{	
			bodyDiagram.paint(thisGraphic);
		}
		
		@Override
		protected void paintComponent(Graphics thisGraphic)
		{
			System.out.println("Inside HealthReport::PaintComponent()");
			super.paintComponent(thisGraphic);
			Graphics2D diagramPainter = (Graphics2D) thisGraphic;
			update(diagramPainter);
		}

}
