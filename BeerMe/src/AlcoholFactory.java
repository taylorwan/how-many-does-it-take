public class AlcoholFactory
{
	//Enum for identifying alcohol, used for adding alcohols 
	//inside action listeners, other methods use string
	//matching because this is a little easier to use
	public enum ALCOHOL_TYPE{BEER, WINE, SHOT, COCKTAIL};
	
	
	//Alcohol specific constants --> calories
	final static int BEER_CALORIES = 120; 
	final static int WINE_CALORIES = 150; 
	final static int SHOT_CALORIES = 96; 
	final static int COCKTAIL_CALORIES = 200; 
	
	//Ounces
	final static double BEER_OUNCES = 12;
	final static double WINE_OUNCES = 5;
	final static double SHOT_OUNCES = 1.5;
	final static double COCKTAIL_OUNCES = 8;
	
	//Content
	final static double BEER_CONTENT = .05;
	final static double WINE_CONTENT = .12;
	final static double SHOT_CONTENT = .40;
	final static double COCKTAIL_CONTENT = .07;
	
	
	//Retrieve the calories given some alcohol
	public static int getCalories(String whichAlcohol)
	{
		switch(whichAlcohol)
		{
		case "Beer":
			return BEER_CALORIES;
		case "Wine":
			return WINE_CALORIES;
		case "Shot":
			return SHOT_CALORIES;
		case "Cocktail":
			return COCKTAIL_CALORIES;
		}
		
		return -1;
	}
	
	//Get ounces given some alcohol
	public static double getOuncesPerDrink(String whichAlcohol)
	{
		switch(whichAlcohol)
		{
		case "Beer":
			return BEER_OUNCES;
		case "Wine":
			return WINE_OUNCES;
		case "Shot":
			return SHOT_OUNCES;
		case "Cocktail":
			return COCKTAIL_OUNCES;
		}
		
		return -1;
	}
	
	//Get the content given some alcohol
	public static double getAlcoholContent(String whichAlcohol)
	{
		switch(whichAlcohol)
		{
		case "Beer":
			return BEER_CONTENT;
		case "Wine":
			return WINE_CONTENT;
		case "Shot":
			return SHOT_CONTENT;
		case "Cocktail":
			return COCKTAIL_CONTENT;
		}
		
		return -1;
		
	}
}