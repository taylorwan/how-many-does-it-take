import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;


public class Chart 
{
	//Sizing scalar
	private final static double sizingScalar = 2;
	
	//Rate constants per drink	
	private final static double BRAIN_BEER = 6.0, BRAIN_WINE = 3.0, BRAIN_SHOT = 7.6, BRAIN_COCKTAIL = 6.8;
	private final static double HEART_BEER = 7.0, HEART_WINE = 2.0, HEART_SHOT = 8.0, HEART_COCKTAIL = 7.0;
	private final static double LIVER_BEER = 5.0, LIVER_WINE = 5.4, LIVER_SHOT = 9.0, LIVER_COCKTAIL = 6.4;
	private final static double KIDNEY_BEER = 4.0, KIDNEY_WINE = 1.0, KIDNEY_SHOT = 6.0, KIDNEY_COCKTAIL = 5.0;
	
	//Color of the chart
	private final static Color chartColor = Color.red;
	
	//Middle of chart coordinates in the middle of the frame
	private final double xMiddle = Application.screenWidth/2, yMiddle = Application.screenHeight/2-10;
	
	//Line slopes for each organ
	private final double[] xConstants = {0, 1, 0, -1};
	private final double[] yConstants = {-1, 0, 1, 0};
	
	//Constants for max lengths
	
	private final double maxUnitLength = 175;
	
	//Array of points for the polygon
	//0 for brain, 1 for heart, 2 for liver, 3 for kidney
	int[] xPoints = new int[4];
	int[] yPoints = new int [4];
	double[] lineLengths = new double[4];
	
	//The participant needed to create the chart
	Participant participant;
	
	public Chart(Participant person)
	{
		participant = person;
		
		//Calculate values for drawing the chart
		calculateLineLengths();
		calculateCoordinates();
	}
	
	//Calculates the coordinates for particular x and y values
	public void calculateCoordinates()
	{	
		for (int i = 0; i < 4; i++)
		{
			xPoints[i] = (int) (xMiddle + lineLengths[i] * xConstants[i]);
			yPoints[i] = (int) (yMiddle + lineLengths[i] * yConstants[i]);
		}
	}
	
	//Calculate the line lengths for each given combo
	public void calculateLineLengths()
	{
		lineLengths[0] = (participant.getCurrentBeers() * BRAIN_BEER + participant.getCurrentWine() * BRAIN_WINE +
						 participant.getCurrentShots() * BRAIN_SHOT + participant.getCurrentCocktails() * BRAIN_COCKTAIL) * sizingScalar;
		
		//Check for lengths that go out of bounds
		//we need to do this for display and for each
		//line length
		if(lineLengths[0] > maxUnitLength)
		{
			lineLengths[0] = maxUnitLength;
		}
		
		lineLengths[1] = (participant.getCurrentBeers() * HEART_BEER + participant.getCurrentWine() * HEART_WINE+
						 participant.getCurrentShots() * HEART_SHOT + participant.getCurrentCocktails() * HEART_COCKTAIL) * sizingScalar;
		
		if(lineLengths[1] > maxUnitLength)
		{
			lineLengths[1] = maxUnitLength;
		}
		
		lineLengths[2] = (participant.getCurrentBeers() * LIVER_BEER + participant.getCurrentWine() * LIVER_WINE+
						 participant.getCurrentShots() * LIVER_SHOT + participant.getCurrentCocktails() * LIVER_COCKTAIL) * sizingScalar;
		
		if(lineLengths[2] >= maxUnitLength)
		{
			lineLengths[2] = maxUnitLength;
		}
		
		lineLengths[3] = (participant.getCurrentBeers() * KIDNEY_BEER + participant.getCurrentWine() * KIDNEY_WINE+
						 participant.getCurrentShots() * KIDNEY_SHOT + participant.getCurrentCocktails() * KIDNEY_COCKTAIL) * sizingScalar;
		
		if(lineLengths[3] >= maxUnitLength)
		{
			lineLengths[3] = maxUnitLength;
		}
	}
	
	public void paint(Graphics2D g) 
	{
		g.setColor(chartColor);
		Polygon poly = new Polygon(xPoints, yPoints, 4);
		g.fillPolygon(poly);
		g.drawPolygon(poly);
	}
}
