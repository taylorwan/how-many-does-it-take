

public class AlcoholFactory implements AlcoholProcessor
{
	
	public int getCalories(ALCOHOL_TYPE whichAlcohol)
	{
		switch(whichAlcohol)
		{
		case BEER:
			return BEER_CALORIES;
		case WINE:
			return WINE_CALORIES;
		case SHOT:
			return SHOT_CALORIES;
		case COCKTAIL:
			return COCKTAIL_CALORIES;
		}
		
		return -1;
	}
	
	public int getOuncesPerDrink(ALCOHOL_TYPE whichAlcohol)
	{
		switch(whichAlcohol)
		{
		case BEER:
			return BEER_OUNCES;
		case WINE:
			return WINE_OUNCES;
		case SHOT:
			return SHOT_OUNCES;
		case COCKTAIL:
			return COCKTAIL_OUNCES;
		}
		
		return -1;
	}
}
