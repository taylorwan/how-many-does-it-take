import static org.junit.Assert.*;

import org.junit.Test;


public class HealthCalculatorTest 
{

	//Test getting total BAC
	@Test
	public void testCalculateCalories() 
	{
		Participant participant = new Participant("Daniel", 155, Participant.GENDER.MALE, 1);
		participant.setCurrentBeers(2);
		participant.setCurrentWine(2);
		participant.setCurrentCocktails(2);
		participant.setCurrentShots(3);
		
		assertEquals(1228, HealthCalculator.caluclateCalories(participant));
	}
	
	//Test getting total BAC -> For a female
	@Test
	public void testCalculateBACFemale() 
	{
		Participant participant = new Participant("Taylor", 100, Participant.GENDER.FEMALE, 1);
		participant.setCurrentBeers(2);
		participant.setCurrentWine(2);
		participant.setCurrentShots(3);
		
		assertEquals(0.3120, HealthCalculator.calculateBAC(participant), .001);
	}
	
	//Test getting total BAC -> For a male
	@Test
	public void testCalculateBACMale() 
	{
		Participant participant = new Participant("Daniel", 155, Participant.GENDER.MALE, 1);
		participant.setCurrentBeers(2);
		participant.setCurrentWine(2);
		participant.setCurrentCocktails(2);
		participant.setCurrentShots(3);
		
		assertEquals(0.2266, HealthCalculator.calculateBAC(participant), .001);
	}

}
