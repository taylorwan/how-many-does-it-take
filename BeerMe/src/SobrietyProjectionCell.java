
//Simple container class that holds
//hours and BAC, used for compiling
//data for simulation
public class SobrietyProjectionCell
{
	double numberOfHours;
	double thisBAC;
	
	//Creates a cell that holds hours and BAC
	public SobrietyProjectionCell(double passedNumberOfHours, double passedBAC)
	{
		numberOfHours = passedNumberOfHours;
		thisBAC = passedBAC;
	}
	
	//Get hours
	public double getNumberOfHours()
	{
		return numberOfHours;
	}
	
	//Get BAC
	public double getBAC()
	{
		return thisBAC;
	}
}