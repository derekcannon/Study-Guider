package studyGuider;

import java.util.ArrayList;

public interface QuestionViewInterface {
	
	/**
	 * Pops up a JFileChooser (with a .txt filter on) to select the location the text file you want to open.
	 * @return Directory location and file name and extension (e.g. "/users/Derek/Desktop/chemistry.txt")
	 */
	public String showFileChooser();
	
	/**
	 * Sets the components of the QuestionView equal to the values for the next question.
	 * @param question The question (e.g. "What day is it?")
	 * @param choices All the choices (e.g. ["Monday", "Tuesday", "Wednesday"])
	 * @param results The detailed information pertaining to which question one is on, how many questions are remaining,
	 * the percentage correct, etc.
	 */
	public void nextQuestion(String question, ArrayList<String> choices, String results);
	
	/**
	 * Sets invisible all of the choice JButtons that are not the correct answer or the hint.
	 * @param choices All the choices (e.g. ["Monday", "Tuesday", "Wednesday"])
	 * @param answer The correct answer, based on index in the choices ArrayList
	 * @param hint The remaining incorrect answer
	 */
	public void setHint(ArrayList<String> choices, int answer, int hint);

}
