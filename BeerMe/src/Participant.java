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
	
	
	//Constructor takes in name, weight, gender and hours expected drinking
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
	
	//Sets weight
	public void setWeight(int passedWeight)
	{
		weight = passedWeight;
	}
	
	//Sets name
	public void setName(String passedName)
	{
		name = passedName;
	}
	
	//Sets gender
	public void setGender(GENDER passedGender)
	{
		gender = passedGender;
	}
	
	//Returns weight
	public int getWeight()
	{
		return weight;
	}
	
	//Returns name
	public String getName()
	{
		return name;
	}
	
	//Returns gender
	public GENDER getGender()
	{
		return gender;
	}
	
	//Returns drinks
	public int getTotalDrinks()
	{
		return (currentBeers + currentShots + currentCocktails + currentWine);
	}
	
	//Returns expected hours drinking
	public double getHoursDrinking()
	{
		return hoursDrinking;
	}
	
	//Sets the hours drinking
	public void setHoursDrinking(double passedHoursDrinking)
	{
		hoursDrinking = passedHoursDrinking;
	}
	
	//Returns beers drank
	public int getCurrentBeers()
	{
		return currentBeers;
	}
	
	//sets beers drank
	public void setCurrentBeers(int numberOfBeers)
	{
		currentBeers = numberOfBeers;
	}
	
	//Gets wine drank
	public int getCurrentWine()
	{
		return currentWine;
	}
	
	//Sets wine drank
	public void setCurrentWine(int numberOfWines)
	{
		currentWine = numberOfWines;
	}
	
	//Gets current shots
	public int getCurrentShots()
	{
		return currentShots;
	}
	
	//Sets current shots
	public void setCurrentShots(int numberOfShots)
	{
		currentShots = numberOfShots;
	}
	
	//Gets current cocktails
	public int getCurrentCocktails()
	{
		return currentCocktails;
	}
	
	//Sets current cocktails
	public void setCurrentCocktails(int numberOfCocktails)
	{
		currentCocktails = numberOfCocktails;
	}
	
	//Gets current total of calories consumed
	public int getCurrentCalories()
	{
		return currentCalories;
	}
	
	//Sets calories consumed
	public void setCurrentCalories(int passedCalories)
	{
		currentCalories = passedCalories;
	}
	
	//Gets current BAC
	public double getCurrentBAC()
	{
		return currentBAC;
	}
	
	//Sets BAC
	public void setCurrentBAC(double passedBAC)
	{
		currentBAC = passedBAC;
	}
	
}