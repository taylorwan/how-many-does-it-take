import java.awt.Graphics2D;


public interface Activity
{

	public void create();
	
	public void initGUI();
	
	public void activate();
	
	public void deactivate();
	
	public void begin() throws InterruptedException;
}
