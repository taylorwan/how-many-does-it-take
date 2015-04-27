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

	public String getText() {
		return question;
	}
	public String getChoice( int i ) {
		return answerChoices.get(i);
	}
	public int size() {
		return answerChoices.size();
	}
	public boolean isCorrect( String answer ) {
		return answer.equals(correctAnswer);
	}
	public String getRationale() {
		return rationale;
	}
	public String toString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result += getChoice(i);
		}
		return result;
	}
}
