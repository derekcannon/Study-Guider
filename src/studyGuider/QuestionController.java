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
 * 			* Study Guider is now free and open source. Find it on GitHub at https://github.com/derekcannon/Study-Guider
 * 			* Some minor GUI tweaks:
 * 					* Changed font face to Georgia
 * 					* Various font size to changes
 * 			* TODO: Implement a prettier GUI
 * 
 *  Bug Fixes:
 * 			* Nothing yet.
 */
public class QuestionController {
	
	private ArrayList<QuestionAndAnswer> list;
	private QuestionViewInterface view;
	private String questionFile;
	private int currentQuestion = -1;
	private int correctAnswers = 0;
	private int incorrectAnswers = 0;
	
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
			e.printStackTrace();
		}
		
	}
	
	public void sendResults(String answer)
	{
		if(list.get(currentQuestion).answerIsCorrect(answer))
		{
			correctAnswers++;
			JOptionPane.showMessageDialog(null, "Correct! " + list.get(currentQuestion).correctAnswer(), "Your Answer", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
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
	}
	
	public static void main(String[] args) {
		new QuestionController();
	}

}