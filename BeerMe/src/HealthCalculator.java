import java.util.ArrayList;

public class HealthCalculator 
{
	
	//Formula specific constants -> calculating BAC
	final static double COMBO_CONST = 5.14;
	final static double MALE_VALUE = .73;
	final static double FEMALE_VALUE = .66;
	final static double ALCOHOL_METABOLIZATION = .015;

	
	public static double calculateBAC(Participant thisParticipant)
	{
		double bloodAlcoholContent = 0.00;
		double liquidOunces = calculateLiquidOunces(thisParticipant);
		
		//Blood alcohol content for a male
		if(thisParticipant.getGender() == Participant.GENDER.MALE)
		{
			bloodAlcoholContent = ( (liquidOunces * COMBO_CONST) / (thisParticipant.getWeight() * MALE_VALUE) ) 
								  - (ALCOHOL_METABOLIZATION * thisParticipant.getHoursDrinking());
		}
		else
		{
			bloodAlcoholContent = ( (liquidOunces * COMBO_CONST) / (thisParticipant.getWeight() * FEMALE_VALUE) ) 
								  - (ALCOHOL_METABOLIZATION * thisParticipant.getHoursDrinking()); 
		}
		
		//Alcohol metabolization has exceeded drunkeness
		if(bloodAlcoholContent < 0)
		{
			bloodAlcoholContent = 0;
		}
		
		return bloodAlcoholContent;
	}
	
	//Returns the liquid ounces of alcohol consumed
	public static double calculateLiquidOunces(Participant thisParticipant)
	{
		double ouncesBeer = (thisParticipant.getCurrentBeers() * AlcoholFactory.getOuncesPerDrink("Beer")) 
							* AlcoholFactory.getAlcoholContent("Beer");
		
		double ouncesWine = (thisParticipant.getCurrentWine() * AlcoholFactory.getOuncesPerDrink("Wine")) 
				* AlcoholFactory.getAlcoholContent("Wine");
		
		double ouncesShot = (thisParticipant.getCurrentShots() * AlcoholFactory.getOuncesPerDrink("Shot")) 
				* AlcoholFactory.getAlcoholContent("Shot");
		
		double ouncesCocktail = (thisParticipant.getCurrentCocktails() * AlcoholFactory.getOuncesPerDrink("Cocktail")) 
				* AlcoholFactory.getAlcoholContent("Cocktail");
		
		return (ouncesBeer + ouncesWine + ouncesCocktail + ouncesShot);
		
	}
	
	//Returns 
	public static int caluclateCalories(Participant thisParticipant)
	{
		
		int currentBeer = thisParticipant.getCurrentBeers();
		int currentWhine = thisParticipant.getCurrentWine();
		int currentShots = thisParticipant.getCurrentShots();
		int currentCocktails = thisParticipant.getCurrentCocktails();
		
		return ( (currentBeer * AlcoholFactory.getCalories("Beer")) + 
				(currentWhine * AlcoholFactory.getCalories("Wine")) + 
				 (currentShots * AlcoholFactory.getCalories("Shot"))) + 
				 (currentCocktails * AlcoholFactory.getCalories("Cocktail"));
	}
	
	//Takes in participant and desired BAC, returns hours needed until that
	//particular BAC is reached
	public static double calculateHoursNeeded(Participant thisParticipant, double desiredBAC)
	{
		final double amountToAdd = .5;
		double originalHours = thisParticipant.getHoursDrinking();
		double currentBAC = thisParticipant.getCurrentBAC();
		
		//Recalculate BAC till it is does not evaluate
		//as greater than the desired
		while(currentBAC > desiredBAC)
		{
			thisParticipant.setHoursDrinking(thisParticipant.getHoursDrinking() + amountToAdd);
			currentBAC = calculateBAC(thisParticipant);
		}
		
		double hoursToGo = thisParticipant.getHoursDrinking() - originalHours;
		thisParticipant.setHoursDrinking(originalHours);
		
		return (hoursToGo);
	}
	
	public static ArrayList<SobrietyProjectionCell> calculateHoursWithBACs(Participant thisParticipant, double desiredBAC)
	{
		
		//Create list of cells
		ArrayList<SobrietyProjectionCell> projectionCells = new ArrayList<SobrietyProjectionCell>();
		
		final double amountToAdd = 1;
		double originalHours = thisParticipant.getHoursDrinking();
		double currentBAC = thisParticipant.getCurrentBAC();
		
		//Recalculate BAC till it is does not evaluate
		//as greater than the desired
		while(currentBAC > desiredBAC)
		{
			
			thisParticipant.setHoursDrinking(thisParticipant.getHoursDrinking() + amountToAdd);
			currentBAC = calculateBAC(thisParticipant);
			
			//Add values for hours to cell
			SobrietyProjectionCell hoursAndBAC = new SobrietyProjectionCell(thisParticipant.getHoursDrinking() - originalHours, currentBAC);
			
			//Add cell to list
			projectionCells.add(hoursAndBAC);
		}
		
		
		thisParticipant.setHoursDrinking(originalHours);
		return projectionCells;
	}
	
}