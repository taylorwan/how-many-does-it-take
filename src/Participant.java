
public class Participant 
{
	private String name;
	private int weight;
	
	public enum GENDER{MALE, FEMALE};
	private GENDER gender;
	
	private double currentBAC;
	private int currentCalories;
	private int totalDrinks;
	private int hoursDrinking;
	
	//Holds values of all current beverages consumed
	private int currentBeers;
	private int currentShots;
	private int currentWhine;
	private int currentCocktails;
	
	
	
	public Participant(final String passedName, final int passedWeight, final GENDER passedGender)
	{
		name = passedName;
		weight = passedWeight;
		gender = passedGender;
		
		//Instantiate all totals to 0
		currentBeers = currentShots = currentCocktails = currentWhine = 0;
	}
	
	public void setWeight(int passedWeight)
	{
		weight = passedWeight;
	}
	
	public void setName(String passedName)
	{
		name = passedName;
	}
	
	public void setGender(GENDER passedGender)
	{
		gender = passedGender;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public String getName()
	{
		return name;
	}
	
	public GENDER getGender()
	{
		return gender;
	}
	
	public int getTotalDrinks()
	{
		return (currentBeers + currentShots + currentCocktails + currentWhine);
	}
	
	public int getHoursDrinking()
	{
		return hoursDrinking;
	}
	
	public int getCurrentBeers()
	{
		return currentBeers;
	}
	
	public int getCurrentWhine()
	{
		return currentWhine;
	}
	
	public int getCurrentShots()
	{
		return currentShots;
	}
	
	public int getCurrentCocktails()
	{
		return currentCocktails;
	}
	
	
}
