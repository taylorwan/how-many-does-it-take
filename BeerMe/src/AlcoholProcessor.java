public interface AlcoholProcessor
{
	final static int BEER_CALORIES = 120; 
	final static int WINE_CALORIES = 150; 
	final static int SHOT_CALORIES = 96; 
	final static int COCKTAIL_CALORIES = 200; 
	
	final static double BEER_OUNCES = 12;
	final static double WINE_OUNCES = 5;
	final static double SHOT_OUNCES = 1.5;
	final static double COCKTAIL_OUNCES = 8;
	
	final static double BEER_CONTENT = .05;
	final static double WINE_CONTENT = .12;
	final static double SHOT_CONTENT = .40;
	final static double COCKTAIL_CONTENT = .07;
	
	public enum ALCOHOL_TYPE {BEER, WINE, SHOT, COCKTAIL};
	
	public int getCalories(String whichAlcohol);
	
	public double getOuncesPerDrink(String whichAlcohol);
	
	public double getAlcoholContent(String whichAlcohol);

}
