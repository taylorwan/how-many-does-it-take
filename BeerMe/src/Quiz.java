import java.util.ArrayList;
 
 
public class Quiz {
 
	//Questions contained
    private ArrayList<Question> questions = new ArrayList<>();
    
    //Holds quesion number and correct
    private int numQuestions, numCorrect;
 
    //If done
    final static String DONE = "Congrats! You're done.";
     
    //Default constructor
    public Quiz()
    {
        createQuestions();
        numQuestions = questions.size();
        numCorrect = 0;
    }
 
    //Get the size
    public int size() 
    {
        return numQuestions;
    }
    
    //Get number correct
    public int getCorrect() 
    {
        return numCorrect;
    }
     
    //Increment correct
    public void incrementCorrect()
    {
        numCorrect++;
    }
    
    //Get the question
    public Question get( int i ) 
    {
        return questions.get(i);
    }
    
    //Sets the number correct
    public void setNumCorrect(int i)
    {
    	numCorrect = 0;
    }
 
    //Holds data of all the questions!!
    public void createQuestions()
    {
        String prompt, correct, rationale;
        ArrayList<String> answerChoices = new ArrayList<>();
        Question question;
         
        //Question 1
        prompt = "What is the legal alcoholic limit while driving in DC?";
        answerChoices.add(".04");
        answerChoices.add(".06");
        answerChoices.add(".08");
        answerChoices.add(".10");
        correct = ".08";
         
        rationale = "The legal limit in most states in the United States is .08";
         
        question = new Question(prompt, answerChoices, correct, rationale);
        questions.add(question);
        answerChoices.clear();
         
         
        //Question 2
        prompt = "What is the standard amount of drinks to stay under the legal limit in a 3 hour period?";
        answerChoices.add("2");
        answerChoices.add("3");
        answerChoices.add("4");
        answerChoices.add("5");
        correct = "3";
         
        rationale = "The standard rule for drinking and driving is one drink per hour combined with water consumption";
                 
        question = new Question(prompt, answerChoices, correct, rationale);
        questions.add(question);        
        answerChoices.clear();
         
        //Question 3
        prompt = "At what BAC level should one seek medical attention?";
        answerChoices.add(".10 or above");
        answerChoices.add(".12 or above");
        answerChoices.add(".16 or above");
        answerChoices.add(".20 or above");
        correct = ".16 or above";
         
        rationale = "At a BAC level of .16, vital organs can begin to shut down due to poisoning";
                 
        question = new Question(prompt, answerChoices, correct, rationale);
        questions.add(question);
        answerChoices.clear();
         
        //Question 5
        prompt = "Which organs are severely affected by alcohol consumption?";
        answerChoices.add("Kidney");
        answerChoices.add("Heart");
        answerChoices.add("Liver");
        answerChoices.add("All of the Above");
        correct = "All of the Above";
        rationale = "The 3 organs above are severely affected by binge drinking, but the entire \n"
                    + "body is affected greatly by excess alcohol consumption";
         
        question = new Question(prompt, answerChoices, correct, rationale);
        questions.add(question);
        answerChoices.clear();
         
         
        //Question 7
        prompt = "The amount of excess calories associated with a night of drinking can total: ";
        answerChoices.add("1500");
        answerChoices.add("2500");
        answerChoices.add("3500");
        answerChoices.add("4500");
        correct = "4500";
         
        rationale = "The calories from drinking itself, eating late at night and the need for \n"
                    + "more food in the following 24 hours can lead to 4500 excess calories being consumed";
                 
        question = new Question(prompt, answerChoices, correct, rationale);
        questions.add(question);
        answerChoices.clear();
         
    }
}