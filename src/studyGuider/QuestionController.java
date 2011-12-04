package studyGuider;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * Study Guider
 * By Derek Cannon
 * 
 *  ======= Version 4.0 =======
 * 
 * 	New Features:
 * 			* TODO: Add Flash-card-like system for questions without answers
 *  
 *  Coming Soon:
 *  		* Nothing yet.
 * 
 *  Improvements:
 * 			* Nothing yet.
 * 
 *  Bug Fixes:
 * 			* Some minor GUI tweaks:
 * 				* Changed font face to Georgia
 * 				* Various font size to changes
 */
public class QuestionController {
	
	private ArrayList<QuestionAndAnswer> list;
	private QuestionViewInterface view;
	private String questionFile;
	private int currentQuestion = -1;
	private int correctAnswers = 0;
	private int incorrectAnswers = 0;
	//private ArrayList<String> answers;
	
	public QuestionController()
	{	
		//view = new QuestionView(this);
		view = new OldQuestionView(this); // TODO: Replace OldQuestionView with QuestionView soon
		
		questionFile = view.showFileChooser(); // Rest of code waits on file to be chosen

		// Create ArrayList<ArrayList<String>> of questions, and shuffle them.
		try
		{
			list = QuestionAndAnswerParser.parseNumberLetters(new File(questionFile));
			System.out.println("This line reached!");
			Collections.shuffle(list);
			
			nextQuestion();
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendResults(String answer)
	{
		//System.out.println("your attempt answer is " + answer);
		if(list.get(currentQuestion).answerIsCorrect(answer))
		{
			//System.out.println("Correct!");
			correctAnswers++;
			JOptionPane.showMessageDialog(null, "Correct! " + list.get(currentQuestion).correctAnswer(), "Your Answer", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			//System.out.println("Incorrect. The answer is: " + list.get(currentQuestion).correctAnswer());
			incorrectAnswers++;
			JOptionPane.showMessageDialog(null, "Incorrect. The answer is: " + list.get(currentQuestion).correctAnswer(), "Your Answer", JOptionPane.ERROR_MESSAGE);
		}
		
		nextQuestion();
	}
	
	public void nextQuestion()
	{
		float f = ((float)correctAnswers)/(correctAnswers+incorrectAnswers);
		f *= 100.0;
		String results = "Correct: " + correctAnswers + ", Incorrect: " + incorrectAnswers + " (" + Math.round(f) +  "%) | " + "Remaining: " + (list.size()-(correctAnswers+incorrectAnswers))  + " / " + list.size();
		
		if(currentQuestion < (list.size() - 1))
		{
			currentQuestion++;
			view.nextQuestion((currentQuestion+1) + ". " + list.get(currentQuestion).getQuestion(), list.get(currentQuestion).getChoices(), results);
		}
		else
		{
			List<String> temp = new ArrayList<String>();
			Collections.addAll(temp,"...","...","...","...");
			
			view.nextQuestion("No more questions.", (ArrayList<String>)temp, results);
		}
	}
	
	public void sendHint()
	{
		if(list.get(currentQuestion).getHint() != -1)
		{
			view.setHint(list.get(currentQuestion).getChoices(), 
					list.get(currentQuestion).getAnswer(), list.get(currentQuestion).getHint());
		}
		// view.setHint(list.get(currentQuestion.getHint()));
	}
	
	public static void main(String[] args) {
		new QuestionController();
	}

}

/*
 *  ======= Version 3.0 =======
 * 
 * 	New Features:
 *  		* Hints
 *  		* A simple update system to check for/download the latest version of Study Guider
 *  
 *  Coming Soon:
 *  		* Add Flash-card-like system for questions without answers
 * 
 *  Improvements:
 * 			* File selection window filters out everything but .txt files
 * 			* Added "About" menu to see application information and check for updates 
 * 
 *  Bug Fixes:
 * 			* Some minor GUI tweaks:
 * 				* Excess buttons of choices with fewer than four questions now hide themselves.		
 * 
 * ======= Version 2.1 =======
 * 
 * Bug fixes:
 * 		* Fixed bug where, if there were lines in the .TXT file that weren't questions or choices, the parser wouldn't parse correctly.
 * 			
 * ======= Version 2.0 =======
 * 
 * 	New Features:
 * 		* Choice randomization
 * 		* Lots of code restructuring for easier future improvements
 * 
 * 	Coming soon: 
 * 		* Hints
 * 		* "Review Missed Questions" option
 * 		* Auto-updates?
 * 
 * 	Bug Fixes:
 * 		* Questions with less than 4 responses are now correctly displayed
 * 		* No longer crashes at last question
 * 		* Some minor GUI tweaks:
 * 				* Button focus clears after each new question
 * 				* Question area shows tops of the question
 * 				* Added 'total questions answered' to the bottom information panel
 * 				* Changed the question numbering to reflect how many questions were answered (old way showed the original numbering of the question from the .TXT file)
*/
