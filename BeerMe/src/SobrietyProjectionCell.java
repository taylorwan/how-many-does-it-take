public class SobrietyProjectionCell
{
	double numberOfHours;
	double thisBAC;
	
	public SobrietyProjectionCell(double passedNumberOfHours, double passedBAC)
	{
		numberOfHours = passedNumberOfHours;
		thisBAC = passedBAC;
	}
	
	public double getNumberOfHours()
	{
		return numberOfHours;
	}
	
	public double getBAC()
	{
		return thisBAC;
	}
}