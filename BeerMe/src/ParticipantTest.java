import static org.junit.Assert.*;

import org.junit.Test;


public class ParticipantTest {

	@Test
	public void constructorTest() 
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		
		assertEquals(person.getName(), "Bob");
		assertEquals(person.getWeight(), 150);
		assertEquals(person.getGender(), Participant.GENDER.FEMALE);
		assertEquals(person.getHoursDrinking(), 3.0, .1);
	}
	
	@Test
	public void setNameTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setWeight(200);
		
		assertEquals(person.getWeight(), 200, 0.1);
		
	}
	
	@Test
	public void setHoursDrinkingTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setHoursDrinking(2);
		
		assertEquals(person.getHoursDrinking(), 2, 0.001);
		
	}
	
	@Test
	public void setGenderTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setGender(Participant.GENDER.MALE);
		
		assertEquals(person.getGender(), Participant.GENDER.MALE);
		
	}
	
	@Test
	public void setBeersTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentBeers(20);
		
		assertEquals(person.getCurrentBeers(), 20, 0.1);
		
	}
	
	@Test
	public void setWineTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentWine(16);
		
		assertEquals(person.getCurrentWine(), 16, 0.1);
		
	}
	
	@Test
	public void setShotTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentShots(10);
		
		assertEquals(person.getCurrentShots(), 10, 0.1);
		
	}
	
	@Test
	public void setCocktailTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentCocktails(8);
		
		assertEquals(person.getCurrentCocktails(), 8, 0.1);
		
	}
	
	@Test
	public void setCaloriesTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentCalories(800);
		
		assertEquals(person.getCurrentCalories(), 800, 0.1);
		
	}
	
	@Test
	public void setBACTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentBAC(.08);
		
		assertEquals(person.getCurrentBAC(), .08, 0.1);
		
	}
	
	@Test
	public void totalDrinksTest()
	{
		Participant person = new Participant("Bob", 150, Participant.GENDER.FEMALE, 3);
		person.setCurrentBeers(2);
		person.setCurrentWine(1);
		person.setCurrentShots(3);
		person.setCurrentCocktails(4);
		
		assertEquals(person.getTotalDrinks(), 10, 0.1);
		
	}
	
}
