
public interface Activity
{

	//All objects being created will be created using this function
	public void create();
	
	//All GUI components should be created in this function,
	//this is a necessary function for navigation as it will be
	//called every time an activity is activated
	public void initGUI();
	
	//Initialized the GUI and sets the content to the current activity
	public void activate();
	
	//Empties the content pane, should be called prior to
	//activating another activity
	public void deactivate();
	
	//Important for every function is called to update and paint all components in
	//a panel
	public void begin() throws InterruptedException;
}
