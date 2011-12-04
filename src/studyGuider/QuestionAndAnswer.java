package studyGuider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionAndAnswer {
	
	private String question;
	private ArrayList<String> choices;
	private int answerNum; // Numerical answer value (e.g. 1)
	private String answerChar; // Letter answer value (e.g. "A")
	private int hintNum; // The numerical value of the (incorrect) hint answer. -1 means there is no hint
	
	public QuestionAndAnswer(String question, ArrayList<String> choices)
	{
		this.question = question;
		this.choices = choices;
		answerChar = null;
		hintNum = -1;
		
		shuffleChoices(); // Shuffle choices around
		setAnswer(); 	  // Get the correct answer 
		setHint();		  // Set a false choice of the hint
		
		System.out.println(question);
		System.out.println("Answer: " + answerNum);
		System.out.println("Hint: " + answerNum + " or " + hintNum);
		System.out.println();
	}
	
	
	
	private void shuffleChoices()
	{
		Collections.shuffle(this.choices); // Shuffle choices for randomized choices
	}
	
	private void setHint()
	{
		if(choices.size() >= 4) // Ensure that there are at least 4 choices. Less than 4 for 50/50 is too easy!
		{
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			
			for(int i=0; i < choices.size(); i++) // For each element in 'choices'
			{
				indexes.add(i); // Add the index of that element to 'indexes'
			}
			
			indexes.remove(answerNum); // Remove the actual answer from the indexes list.
									   // Example: If indexes is [0,1,2,3] and the answer is 1, 1 should be removed
									   //          so the choices left are all incorrect choices. From there, a random
									   //          choice is picked to be 'wrong' answer in the 50/50 hint option
			
			Random randGenerator = new Random(); // Create a random generator
			
			hintNum = indexes.get(randGenerator.nextInt(indexes.size())); // Get a random value from 'indexes' to be the
																		  // incorrect choice in the hint
		}
		else // or if choices are less than 4, we don't want to give a hint
		{
			hintNum = -1; // Set hintNum to -1 (aka 'No hint')
		}
		
	}
	
	private void setAnswer()
	{
		for(int i=0; i < choices.size(); i++)
		{
			//System.out.println("for i = " + i);
			//System.out.println("\tchoices.get(i) = " + choices.get(i));
			String temp = choices.get(i);
			if(choices.get(i).contains("~"))
			{
				temp = choices.get(i).replaceFirst("~", "");
				
				answerNum = i;
				answerChar = numToLetter(answerNum);
				//System.out.println("\t~ found in " + choices.get(i) + "\n answerNum is now " + answerNum + "\nanswerChar is now " + answerChar);
			}
			
			temp = numToLetter(i) + ". " + temp;
			choices.set(i, temp);
			
		}
	}
	
	private String numToLetter(int num)
	{
		return (String.valueOf((char)((num) + 65))).toLowerCase();
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String correctAnswer()
	{
		return choices.get(answerNum);
		//return answerText;
	}
	
	public String getChoice(int number)
	{
		return choices.get(number);
	}
	
	public ArrayList<String> getChoices()
	{	
		return choices;
	}
	
	public int totalChoices()
	{
		return choices.size();
	}
	
	public boolean answerIsCorrect(String s)
	{
		return s.toLowerCase().equals(answerChar);
	}
	
	public int getHint()
	{
		return hintNum;
	}
	
	public int getAnswer()
	{
		return answerNum;
	}

}

/*
 * Removed
 * public void addChoice(String choice)
{
	if(choice.contains("~"))
	{
		//choice = choice.replaceAll("~", "");
		//answerNum = choices.size();
		answerText = choice;
		//answer = (String.valueOf((char)((answerNum) + 65))).toLowerCase();
	}
	
	choices.add(choice);
}*/
