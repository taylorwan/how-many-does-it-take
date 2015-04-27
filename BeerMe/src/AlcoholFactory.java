public class AlcoholFactory implements AlcoholProcessor
{
	
	public int getCalories(String whichAlcohol)
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
	
	public double getOuncesPerDrink(String whichAlcohol)
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
	
	public double getAlcoholContent(String whichAlcohol)
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