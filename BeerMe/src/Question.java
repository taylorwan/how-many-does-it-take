import java.util.ArrayList;


public class Question 
{

	private String question;
	private ArrayList<String> answerChoices;
	private String correctAnswer;
	private String rationale;
	
	public Question(String prompt, ArrayList<String> choices, String correct, String reasoning)
	{
		question = prompt;
		answerChoices = choices;
		correctAnswer = correct;
		rationale = reasoning;
	}

	public String getQuestion() {
		return question;
	}
	public String getChoice( int i ) {
		return answerChoices.get(i);
	}
	public int getSize() {
		return answerChoices.size();
	}
	public String isCorrect( String answer ) {
		return answer.equals(correctAnswer);
	}
	public String getRationale() {
		return rationale;
	}
}
