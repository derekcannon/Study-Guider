package studyGuider;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class QuestionView extends JFrame implements QuestionViewInterface {

	private ArrayList<JButton> choices = new ArrayList<JButton>();
	private JPanel contentPane;
	private JTextArea txtQuestion;
	private JLabel lblDetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionView frame = new QuestionView(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuestionView(QuestionController controller) {
		setTitle("Study Guider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		txtQuestion = new JTextArea();
		txtQuestion.setFont(new Font("Georgia", Font.PLAIN, 18));
		txtQuestion.setEditable(false);
		txtQuestion.setText("No questions available.");
		GridBagConstraints gbc_txtQuestion = new GridBagConstraints();
		gbc_txtQuestion.gridwidth = 3;
		gbc_txtQuestion.weighty = 3.0;
		gbc_txtQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_txtQuestion.fill = GridBagConstraints.BOTH;
		gbc_txtQuestion.gridx = 0;
		gbc_txtQuestion.gridy = 0;
		contentPane.add(txtQuestion, gbc_txtQuestion);
		
		JButton btnChoice0 = new JButton("...");
		btnChoice0.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnChoice0 = new GridBagConstraints();
		gbc_btnChoice0.gridwidth = 3;
		gbc_btnChoice0.weighty = 0.5;
		gbc_btnChoice0.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoice0.fill = GridBagConstraints.BOTH;
		gbc_btnChoice0.gridx = 0;
		gbc_btnChoice0.gridy = 1;
		contentPane.add(btnChoice0, gbc_btnChoice0);
		
		JButton btnChoice1 = new JButton("...");
		btnChoice1.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnChoice1 = new GridBagConstraints();
		gbc_btnChoice1.gridwidth = 3;
		gbc_btnChoice1.weighty = 0.5;
		gbc_btnChoice1.fill = GridBagConstraints.BOTH;
		gbc_btnChoice1.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoice1.gridx = 0;
		gbc_btnChoice1.gridy = 2;
		contentPane.add(btnChoice1, gbc_btnChoice1);
		
		JButton btnChoice2 = new JButton("...");
		btnChoice2.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnChoice2 = new GridBagConstraints();
		gbc_btnChoice2.gridwidth = 3;
		gbc_btnChoice2.weighty = 0.5;
		gbc_btnChoice2.fill = GridBagConstraints.BOTH;
		gbc_btnChoice2.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoice2.gridx = 0;
		gbc_btnChoice2.gridy = 3;
		contentPane.add(btnChoice2, gbc_btnChoice2);
		
		JButton btnChoice3 = new JButton("...");
		btnChoice3.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnChoice3 = new GridBagConstraints();
		gbc_btnChoice3.gridwidth = 3;
		gbc_btnChoice3.weighty = 0.5;
		gbc_btnChoice3.insets = new Insets(0, 0, 5, 5);
		gbc_btnChoice3.fill = GridBagConstraints.BOTH;
		gbc_btnChoice3.gridx = 0;
		gbc_btnChoice3.gridy = 4;
		contentPane.add(btnChoice3, gbc_btnChoice3);
		
		lblDetails = new JLabel("0/0");
		lblDetails.setFont(new Font("Georgia", Font.PLAIN, 16));
		GridBagConstraints gbc_lblDetails = new GridBagConstraints();
		gbc_lblDetails.gridwidth = 3;
		gbc_lblDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblDetails.gridx = 0;
		gbc_lblDetails.gridy = 5;
		contentPane.add(lblDetails, gbc_lblDetails);
		
		JButton btnNewButton_1 = new JButton("Correct");
		btnNewButton_1.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.weightx = 1.0;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 6;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JButton btnIncorrect = new JButton("Incorrect");
		btnIncorrect.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnIncorrect = new GridBagConstraints();
		gbc_btnIncorrect.weightx = 1.0;
		gbc_btnIncorrect.insets = new Insets(0, 0, 0, 5);
		gbc_btnIncorrect.fill = GridBagConstraints.BOTH;
		gbc_btnIncorrect.gridx = 1;
		gbc_btnIncorrect.gridy = 6;
		contentPane.add(btnIncorrect, gbc_btnIncorrect);
		
		JButton btnNewButton = new JButton("Hint");
		btnNewButton.setFont(new Font("Georgia", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.weightx = 1.0;
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
	}

	@Override
	public String showFileChooser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextQuestion(String question, ArrayList<String> choices,
			String results) {
		this.txtQuestion.setText(question);
		this.lblDetails.setText(results);
		
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
		
		this.txtQuestion.requestFocusInWindow();
		this.txtQuestion.setCaretPosition(0);
	}

	@Override
	public void setHint(ArrayList<String> choices, int answer, int hint) {
		for(int i=0; i < choices.size(); i++)
		{
			if(!(i==answer || i== hint))
			{
				this.choices.get(i).setVisible(false);
			}
		}
	}
}
