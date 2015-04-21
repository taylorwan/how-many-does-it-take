
public class HealthCalculator 
{
	//Formula specific constants -> calculating BAC
	final static double AVG_ALCOHOL_CONTENT = .06;
	final static double GRAVITY_OF_BLOOD = 1.055;
	final static double MALE_VALUE = .68;
	final static double FEMALE_VALUE = .55;
	final static double ALCOHOL_METABOLIZATION = .015;
	
	//Formula specific constants -> calculating calories
	final static int BEER_CALORIES = 120; 
	final static int WHINE_CALORIES = 150; 
	final static int SHOT_CALORIES = 96; 
	final static int COCKTAIL_CALORIES = 200; 
	
	public static double calculateBAC(Participant thisParticipant)
	{
		double bloodAlcoholContent = 0.00;
		
		//Blood alcohol content for a male
		if(thisParticipant.getGender() == Participant.GENDER.MALE)
		{
			bloodAlcoholContent = ( thisParticipant.getTotalDrinks() * AVG_ALCOHOL_CONTENT * 100 * 
								  (GRAVITY_OF_BLOOD / thisParticipant.getWeight()) * MALE_VALUE ) - 
								  (ALCOHOL_METABOLIZATION * thisParticipant.getHoursDrinking()); 
		}
		else
		{
			bloodAlcoholContent = ( thisParticipant.getTotalDrinks() * AVG_ALCOHOL_CONTENT * 100 * 
					  (GRAVITY_OF_BLOOD / thisParticipant.getWeight()) * FEMALE_VALUE ) - 
					  (ALCOHOL_METABOLIZATION * thisParticipant.getHoursDrinking()); 
		}
		
		return bloodAlcoholContent;
	}
	
	//Returns 
	public static double caluclateCalories(Participant thisParticipant)
	{
		
		int currentBeer = thisParticipant.getCurrentBeers();
		int currentWhine = thisParticipant.getCurrentWhine();
		int currentShots = thisParticipant.getCurrentShots();
		int currentCocktails = thisParticipant.getCurrentCocktails();
		
		return (currentBeer * BEER_CALORIES + currentWhine * WHINE_CALORIES + currentShots * 
				SHOT_CALORIES + currentCocktails * COCKTAIL_CALORIES);
	}
	
}
