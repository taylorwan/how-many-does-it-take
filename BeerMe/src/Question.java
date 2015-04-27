import java.util.ArrayList;


public class Question 
{

	String question;
	ArrayList<String> answerChoices;
	String correctAnswer;
	String rationale;
	
	public Question(String prompt, ArrayList<String> choices, String correct, String reasoning)
	{
		question = prompt;
		answerChoices = choices;
		correctAnswer = correct;
		rationale = reasoning;
	}
}
