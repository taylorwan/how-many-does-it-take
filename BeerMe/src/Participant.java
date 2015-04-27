public class Participant 
{
	private String name;
	private int weight;
	
	public enum GENDER{MALE, FEMALE};
	private GENDER gender;
	
	//Calculated specific values
	private double currentBAC;
	private int currentCalories;
	
	//Amount of hours intended drinking
	private double hoursDrinking;
	
	//Holds values of all current beverages consumed
	private int currentBeers;
	private int currentShots;
	private int currentWine;
	private int currentCocktails;
	
	public Participant(final String passedName, final int passedWeight, final GENDER passedGender, double passedHours)
	{
		
		//Set participant specifics
		name = passedName;
		weight = passedWeight;
		gender = passedGender;
		hoursDrinking = passedHours;
		
		//Defaults
		currentBAC = 0.00;
		currentCalories = 0;
		
		//Instantiate all totals to 0
		currentBeers = currentShots = currentCocktails = currentWine = 0;
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
		return (currentBeers + currentShots + currentCocktails + currentWine);
	}
	
	public double getHoursDrinking()
	{
		return hoursDrinking;
	}
	
	public void setHoursDrinking(double passedHoursDrinking)
	{
		hoursDrinking = passedHoursDrinking;
	}
	
	public int getCurrentBeers()
	{
		return currentBeers;
	}
	
	public void setCurrentBeers(int numberOfBeers)
	{
		currentBeers = numberOfBeers;
	}
	
	public int getCurrentWine()
	{
		return currentWine;
	}
	
	public void setCurrentWine(int numberOfWines)
	{
		currentWine = numberOfWines;
	}
	
	public int getCurrentShots()
	{
		return currentShots;
	}
	
	public void setCurrentShots(int numberOfShots)
	{
		currentShots = numberOfShots;
	}
	
	public int getCurrentCocktails()
	{
		return currentCocktails;
	}
	
	public void setCurrentCocktails(int numberOfCocktails)
	{
		currentCocktails = numberOfCocktails;
	}
	
	public int getCurrentCalories()
	{
		return currentCalories;
	}
	
	public void setCurrentCalories(int passedCalories)
	{
		currentCalories = passedCalories;
	}
	
	public double getCurrentBAC()
	{
		return currentBAC;
	}
	
	public void setCurrentBAC(double passedBAC)
	{
		currentBAC = passedBAC;
	}
	
}