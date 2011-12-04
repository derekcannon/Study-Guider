package studyGuider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionAndAnswerParser {
	
	/**
	 * Parses a .txt file, finding questions and their respective answers, and returning
	 * an ArrayList of ArrayLists, where the internal ArrayList holds a single question and
	 * several responses, and the external ArrayList holds ALL the questions and responses.
	 * 
	 * @param f The .txt file that contains formatted questions, where numbers are questions,
	 * and alphabetic sub-bullets are responses.
	 * 
	 * @return An ArrayList of every question and response contained in another ArrayList of
	 * all the questions.
	 * 
	 * @throws FileNotFoundException
	 */
	public static ArrayList<QuestionAndAnswer> parseNumberLetters(File f) throws FileNotFoundException
	{
		Scanner scan = new Scanner(f);
		String line = ""; // Holds the scanner's .nextLine() String
		ArrayList<QuestionAndAnswer> toReturn = new ArrayList<QuestionAndAnswer>();
		boolean reparseLine = false; // Determines if .nextLine() should run. (e.g. If a line does not match the choices,
									 // it might be a question. So when the loop reruns, do not call .nextLine() because
									 // the current line still needs to be evaluated to ensure it is not a question.
		
		while(scan.hasNext()) // While the file has more lines to be read, go through each of them
		{
			if(reparseLine == false) // If re-parsing of the current line isn't needed:
			{
				line = scan.nextLine().trim(); // Scanning the next line
				  							   // (example input - "1. Yes or No?" or "A. Yes" or "B. ~No")
			}
			else // Getting the next line has been skipped, so 
			{
				reparseLine = false; // The line is now being re-parsed, so make sure that the program does not
									 // re-parse forever, by setting 'reparseLine' to false now.
			}
			
			String question; // Create a 'question' String to hold a new question
			ArrayList<String> choices = new ArrayList<String>(); // Create a 'choices' ArrayList to hold all the choices
			
			
			if(line.matches("^\\d+\\..*")) // If the current line is a question
			{
				line = line.replaceFirst("^\\d+.", "").trim(); // remove the numbering
				question = line; // set 'question' to the numberless question (e.g. "1. Hi?" to "Hi?")
				
				while(scan.hasNext()) // Gather the responses by
				{
					line = scan.nextLine().trim(); // getting the next line
					
					if(line.matches("^[a-z|A-Z].*")) // and if it's a choice
					{
						line = line.replaceFirst("^\\w.", "").trim(); // take out the lettering "a." "B." etc
						choices.add(line); // and add the choice to 'choices'
					}
					else // If it's not a choice
					{	
						reparseLine = true; // Ensure that this line is checked by Scanner to see if it could be a question
						break; // Go back to the main loop
					}
				}
				
				toReturn.add(new QuestionAndAnswer(question, choices)); // add a new QuestionAndAnswer to the toReturn array
				
			}
		}		
		
		return toReturn; // finally, return the collection of questions
	}
}

/*
 * Old, no good code! (WHO NEEDS IT?!)
while(scan.hasNext())
{
	String line = scan.nextLine().trim();
	
	// If a line starts with bulleted numbering, it's a question
	if(line.matches("^\\d+\\..*"))
	{
		// If tempChoices has elements in it, it is a collection of all the choices for the last question.
		// Add the choices to the last question first, then we set current question to the next question
		if(tempChoices.size() > 0)
			temp.get(currentQuestion).setChoices(tempChoices);
		tempChoices = new ArrayList<String>(); // Reset the choices array for every new question
		
		currentQuestion++;
		
		line = line.replaceFirst("^\\d+.", "").trim();
		temp.add(new QuestionAndAnswer(line)); // Create a new ArrayList<String> place-holder for a question
		//temp.get(currentQuestion).add(line); // Add the question itself the first element of the new list
	}
	// Of a line starts with alphabetic numbering, it's a response 
	else if(line.matches("^\\w\\..*"))
	{
		//temp.get(currentQuestion).addChoice(line);
		line = line.replaceFirst("^\\w.", "").trim();
		tempChoices.add(line);
	}
	
}*/
