import java.awt.Color;
import java.awt.Graphics2D;


public class chart 
{
	//Rate constants per drink	
	private final double BRAIN_BEER = 6.0, BRAIN_WINE = 3.0, BRAIN_SHOT = 7.6, BRAIN_COCKTAIL = 6.8;
	private final double HEART_BEER = 7.0, HEART_WINE = 6.0, HEART_SHOT = 8.0, HEART_COCKTAIL = 7.0;
	private final double LIVER_BEER = 5.0, LIVER_WINE = 5.4, LIVER_SHOT = 9.0, LIVER_COCKTAIL = 6.4;
	private final double KIDNEY_BEER = 4.0, KIDNEY_WINE = 1.0, KIDNEY_SHOT = 6.0, KIDNEY_COCKTAIL = 5.0;
	
	//Middle of chart coordinates
	private final double xMiddle = 200.0, yMiddle = 200.0;
	
	//Line slopes for each organ
	private final double[] xConstants = {0, 1, 0, -1};
	private final double[] yConstants = {-1, 0, 1, 0};
	
	//Array of points for the polygon
	//0 for brain, 1 for heart, 2 for liver, 3 for kidney
	int[] xPoints;
	int[] yPoints;
	double[] lineLengths;
	
	Participant participant;
	
	public chart(Participant participant)
	{
		this.participant = particpant;
		
		calculateLineLengths();
		calculateCoordinates();
	}
	
	public void calculateCoordinates()
	{	
		
		for (int i = 0; i < 4; i++)
		{
			xPoints[i] = (int) (xMiddle + lineLengths[i] * xConstants[i]);
			yPoints[i] = (int) (yMiddle + lineLengths[i] * yConstants[i]);
		}
	}
	
	public void calculateLineLengths()
	{
		lineLengths[0] = participant.getBeerCount() * BRAIN_BEER + participant.getWineCount() * BRAIN_WINE+
						+participant.getShotCount() * BRAIN_SHOT + participant.getCocktailCount() * BRAIN_COCKTAIL;
		
		lineLengths[1] = participant.getBeerCount() * HEART_BEER + participant.getWineCount() * HEART_WINE+
						+participant.getShotCount() * HEART_SHOT + participant.getCocktailCount() * HEART_COCKTAIL;
		
		lineLengths[2] = participant.getBeerCount() * LIVER_BEER + participant.getWineCount() * LIVER_WINE+
						+participant.getShotCount() * LIVER_SHOT + participant.getCocktailCount() * LIVER_COCKTAIL;
		
		lineLengths[3] = participant.getBeerCount() * KIDNEY_BEER + participant.getWineCount() * KIDNEY_WINE+
						+participant.getShotCount() * KIDNEY_SHOT + participant.getCocktailCount() * KIDNEY_COCKTAIL;
	}
	
	public void paint(Graphics2D myGraphic) 
	{
		myGraphic.setColor(Color.CYAN);
		myGraphic.drawPolygon(xPoints, yPoints, 4);
	}
}
