
public interface AlcoholProcessor
{
	final static int BEER_CALORIES = 120; 
	final static int WINE_CALORIES = 150; 
	final static int SHOT_CALORIES = 96; 
	final static int COCKTAIL_CALORIES = 200; 
	
	final static int BEER_OUNCES = 12;
	final static int WINE_OUNCES = 5;
	final static int SHOT_OUNCES = 1;
	final static int COCKTAIL_OUNCES = 12;
	
	public enum ALCOHOL_TYPE {BEER, WINE, SHOT, COCKTAIL};
	
	public int getCalories(ALCOHOL_TYPE whichAlcohol);
	
	public int getOuncesPerDrink(ALCOHOL_TYPE whichAlcohol);

}
