import static org.junit.Assert.*;
import org.junit.Test;


public class ChartTest {

	
	@Test
	public void chartConstructor() 
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		
		chart newChart = new chart(newPerson);
		assertEquals(newChart.participant.getName(), "Bob");
	}
	
	
	@Test
	public void lineLengthTest() 
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		chart newChart = new chart(newPerson);
		
		newChart.participant.setCurrentBeers(5);
		newChart.participant.setCurrentShots(1);
		newChart.participant.setCurrentWine(2);
		newChart.participant.setCurrentCocktails(1);

		newChart.calculateLineLengths();
		
		assertEquals(newChart.lineLengths[0], 50.4, 0.01);
		assertEquals(newChart.lineLengths[1], 54.0, 0.01);
		assertEquals(newChart.lineLengths[2], 51.2, 0.01);
		assertEquals(newChart.lineLengths[3], 33.0, 0.01);
	}
	
	@Test
	public void coordinateTest()
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		chart newChart = new chart(newPerson);
		
		newChart.lineLengths[0] = 10.0;
		newChart.lineLengths[1] = 12.5;
		newChart.lineLengths[2] = 14.6;
		newChart.lineLengths[3] = 5.0;
		
		newChart.calculateCoordinates();
		
		assertEquals(newChart.xPoints[0], 200);
		assertEquals(newChart.xPoints[1], 212);
		assertEquals(newChart.xPoints[2], 200);
		assertEquals(newChart.xPoints[3], 195);
		assertEquals(newChart.yPoints[0], 190);
		assertEquals(newChart.yPoints[1], 200);
		assertEquals(newChart.yPoints[2], 214);
		assertEquals(newChart.yPoints[3], 200);
	}

}
