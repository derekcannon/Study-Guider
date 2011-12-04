package studyGuider;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UpdateView extends JFrame {
	
	JLabel status = new JLabel("Please wait...", JLabel.CENTER);
	JButton okButton = new JButton("OK");
	
	public UpdateView()
	{
		this.setSize(400, 150);
		this.setTitle("Check for Updates");
		this.setLayout(new BorderLayout());
		
		this.add(status, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		okButton.addActionListener(new ActionListener() // Action for click on About > Check for Updates
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	//setVisible(false);
        		dispose();
            }
        });
		
		buttonPanel.add(okButton);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		//okButton.setEnabled(false);
		okButton.setVisible(false);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public void setStatus(String status)
	{
		this.status.setText(status);
	}
	
	public void showButton()
	{
		okButton.setVisible(true);
		//okButton.setEnabled(true);
	}
	
	
	public static void main(String[] args)
	{
		new UpdateView();
	}
}
