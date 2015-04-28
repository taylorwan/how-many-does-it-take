import static org.junit.Assert.*;

import org.junit.Test;


public class HealthCalculatorTest {

	@Test
	public void BACTest() 
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		newPerson.setCurrentBeers(3);
		newPerson.setCurrentWine(1);
		newPerson.setCurrentShots(2);
		newPerson.setCurrentCocktails(0);
				
		assertEquals(HealthCalculator.calculateBAC(newPerson), .154, .001);
		
	}
	
	@Test
	public void ouncesTest()
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		newPerson.setCurrentBeers(3);
		newPerson.setCurrentWine(1);
		newPerson.setCurrentShots(2);
		newPerson.setCurrentCocktails(0);
				
		assertEquals(HealthCalculator.calculateLiquidOunces(newPerson), 3.6, .001);
	}
	
	@Test
	public void caloriesTest()
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		newPerson.setCurrentBeers(3);
		newPerson.setCurrentWine(1);
		newPerson.setCurrentShots(2);
		newPerson.setCurrentCocktails(0);
				
		assertEquals(HealthCalculator.caluclateCalories(newPerson), 702, .001);
	}
	
	@Test
	public void hoursTest()
	{
		Participant newPerson = new Participant("Bob", 150, Participant.GENDER.MALE, 1);
		newPerson.setCurrentBeers(3);
		newPerson.setCurrentWine(1);
		newPerson.setCurrentShots(2);
		newPerson.setCurrentCocktails(0);
				
		assertEquals(HealthCalculator.calculateHoursNeeded(newPerson, 0.0), 0.0, .001);
	}

}
