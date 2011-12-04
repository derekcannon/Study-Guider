package studyGuider;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class OldQuestionView extends JFrame implements QuestionViewInterface {
	
	private JTextArea question = new JTextArea("No question currently available!");
	private ArrayList<JButton> choices = new ArrayList<JButton>();
	private JButton hintBtn = new JButton("Hint");
	private QuestionController controller;
	private JLabel results = new JLabel("...", JLabel.CENTER);
	
	public OldQuestionView(QuestionController controller)
	{
		this.controller = controller;
		
		this.setSize(500, 400);
		this.setTitle("Study Guider");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setEditable(false);
		question.requestFocusInWindow();
		question.setCaretPosition(0);
		
		JScrollPane questionScroll = new JScrollPane(question);
		
		this.add(questionScroll, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
		
		for(int i=0; i < 4; i++)
		{
			JButton btn = new JButton("...");
			btn.setActionCommand((String.valueOf((char)(i + 65))).toLowerCase()); // e.g. 0 => A, 1 => B
			btn.addActionListener(new ButtonListener());
			choices.add(btn);
			
			btn.setPreferredSize(new Dimension(500, 50));
			
			buttonPanel.add(choices.get(i));
		}
		
		// Score
		JPanel bottomMenu = new JPanel(new BorderLayout());
		hintBtn.addActionListener(new HintListener());
		
		bottomMenu.add(results,BorderLayout.CENTER);
		bottomMenu.add(hintBtn, BorderLayout.EAST);
		buttonPanel.add(bottomMenu);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		this.createFileMenu();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void nextQuestion(String question, ArrayList<String> choices, String results)
	{
		this.question.setText(question);
		this.results.setText(results);
		
		for(int i=0; i <= 3; i++)
		{
			this.choices.get(i).setText("");
//			this.choices.get(i).setEnabled(false);
			this.choices.get(i).setVisible(false);
		}
		
		for(int i=0; i < choices.size(); i++)
		{
			this.choices.get(i).setText("<html>" + choices.get(i) + "</html>");
//			this.choices.get(i).setEnabled(true);
			this.choices.get(i).setVisible(true);
		}
		
		this.question.requestFocusInWindow();
		this.question.setCaretPosition(0);
	}
	
	public void setHint(ArrayList<String> choices, int answer, int hint)
	{
		for(int i=0; i < choices.size(); i++)
		{
			if(!(i==answer || i== hint))
			{
				//this.choices.get(i).setText("<html><span style='color:blue;'>" + choices.get(i) + "</span></html>");
				this.choices.get(i).setVisible(false);
			}
			else
			{
				//this.choices.get(i).setText("<html>" + choices.get(i) + "</html>");
			}
			
			//this.choices.get(i).setVisible(true);
		}
	}
	
	public String showFileChooser()
	{
		JFrame frame = new JFrame();

		//Create a file chooser
		String filename = System.getProperty("user.dir");
		JFileChooser fc = new JFileChooser(new File(filename));

		FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
		fc.addChoosableFileFilter(filter);

		// Show open dialog; this method does not return until the dialog is closed
		fc.showOpenDialog(frame);
		File selFile = fc.getSelectedFile();
		
		if(selFile == null)
		{
			System.exit(0); // Quit the application if user doesn't select a file
		}
		
		return selFile.toString();
	}
	
	
	// New in version 3.0
	private void createFileMenu()
	{   
        JMenuBar menuBar = new JMenuBar(); // Create a menubar
        menuBar.add(Box.createHorizontalGlue());
        
        setJMenuBar(menuBar); // Add the menubar to the frame
        
        JMenu aboutMenu = new JMenu("About"); // create "About" sub-menu
        menuBar.add(aboutMenu); // add "About" sub-menu to menu
        
        JMenuItem updateAction = new JMenuItem("Check for Updates"); // create new "Check for Updates" item
        JMenuItem aboutAction = new JMenuItem("Study Guider"); // create new "Study Guider" item

        aboutMenu.add(updateAction); // add "Check for Updates" to About
        aboutMenu.addSeparator(); // add a separator to About
        aboutMenu.add(aboutAction); // add "Study Guider" to About

        updateAction.addActionListener(new ActionListener() // Action for click on About > Check for Updates
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	//JOptionPane.showMessageDialog(null, "Checking...", "Checking for Updates", JOptionPane.NO_OPTION);
            	new UpdateController();
            }
        });
        aboutAction.addActionListener(new ActionListener() // Action for clicking on About > Study Guider
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	JOptionPane.showMessageDialog(null, "Study Guider v3.0\nBy Derek Cannon\nhttp://www.derekcannon.com", "About Study Guider", JOptionPane.INFORMATION_MESSAGE);
            }
        });
	}
	
	private class ButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			controller.sendResults(arg0.getActionCommand());
		}
		
	}
	
	private class HintListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//controller.sendResults(arg0.getActionCommand());
			controller.sendHint();
		}
		
	}
	
}
