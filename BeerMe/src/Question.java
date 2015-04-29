import java.util.ArrayList;
 
 
public class Question 
{
 
	//Private members to hold questions, anwers, etc
    private String question;
    private ArrayList<String> answerChoices;
    private String correctAnswer;
    private String rationale;
     
    //Create a question given prompt choices correct and why
    public Question(String prompt, ArrayList<String> choices, String correct, String reasoning)
    {
        question = prompt;
        answerChoices = new ArrayList<String>(choices);
         
        correctAnswer = correct;
        rationale = reasoning;
    }
 
    //Returns the text
    public String getText() 
    {
        return question;
    }
     
    //Get the choice
    public String getChoice( int i ) 
    {
        return answerChoices.get(i);
    }
     
    //Get the size
    public int size() 
    {
        return answerChoices.size();
    }
     
    //Check if answer is correct
    public boolean isCorrect( String answer ) 
    {
        return answer.equals(correctAnswer);
    }
     
    //Get the rationale
    public String getRationale() 
    {
        return rationale;
    }
     
    //Turn into string
    public String toString() 
    {
        String result = "";
        for (int i = 0; i < size(); i++) 
        {
            result += getChoice(i);
        }
        return result;
    }
}